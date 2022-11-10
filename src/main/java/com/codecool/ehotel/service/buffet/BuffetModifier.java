package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class BuffetModifier implements BuffetService {
    public Buffet buffet;
    public int wastedFood = 0;
    private final LocalDate date;
    public List<BreakfastCycle> breakfastCycles;

    public void listBuffet() {
        for (Meal meal : buffet.meals()) {
            System.out.println(meal.getMealType().toString() + " " + meal.getAmount() + " portions, made: " + meal.getTimeStamp().toString());
        }
    }

    public BuffetModifier(LocalDate date, List<BreakfastCycle> breakfastCycles, Buffet buffet) {
        this.date = date;
        this.breakfastCycles = breakfastCycles;
        this.buffet = buffet;
    }

    public Map<BreakfastCycle, List<Guest>> generateGuestsInBreakfastCycles(List<Guest> guestsOnActualDate) {
        int breakfastCyclesSize = breakfastCycles.size();
        Map<BreakfastCycle, List<Guest>> breakfastCycleSetMap = new HashMap<>(breakfastCyclesSize);
        for (BreakfastCycle breakfastCycle : breakfastCycles) {
            breakfastCycleSetMap.put(breakfastCycle, new ArrayList<>());
        }
        for (Guest guest : guestsOnActualDate) {
            Random random = new Random();
            BreakfastCycle randomBreakfastCycle = breakfastCycles.get(random.nextInt(breakfastCyclesSize));
            breakfastCycleSetMap.get(randomBreakfastCycle).add(guest);
        }
        return breakfastCycleSetMap;
    }

    @Override
    public Buffet refill(List<Meal> meals) {
        for (Meal meal : meals) {
            buffet.addMeal(meal);
        }
        return buffet;
    }

    @Override
    public boolean consumeFreshest(MealType mealType) {
        List<Meal> filteredMeals = buffet.findPortions(mealType);
        if (filteredMeals.size() > 0) {
            buffet.removeMeal(filteredMeals.get(0));
            return true;
        }
        return false;
    }

    @Override
    public int collectWaste(LocalTime breakfastCycleEnd) {

        int discardedMealsCost = 0;

        List<Meal> wastedMeals = new ArrayList<>();
        for (Meal meal : buffet.getMeals()) {
            LocalTime timeLimit = breakfastCycleEnd.minusMinutes(90);
            if (meal.getMealType().getDurability() == MealDurability.MEDIUM) {
                timeLimit = breakfastCycleEnd.minusMinutes(120);
            } else if (meal.getMealType().getDurability() == MealDurability.LONG) {
                timeLimit = breakfastCycleEnd.minusMinutes(180);
            }

            if (meal.getTimeStamp().isBefore(timeLimit) || meal.getTimeStamp().equals(timeLimit)) {
                wastedMeals.add(meal);
                discardedMealsCost += meal.getMealType().getCost();
            }
        }
        for (Meal meal : wastedMeals) {
            buffet.removeMeal(meal);
        }
        wastedFood += discardedMealsCost;
        return discardedMealsCost;
    }
}

