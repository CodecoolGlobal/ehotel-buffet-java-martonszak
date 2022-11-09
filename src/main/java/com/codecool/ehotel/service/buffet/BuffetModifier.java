package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class BuffetModifier implements BuffetService {

    public LocalDate date;

    public List<BreakfastCycle> breakfastCycles;

    public BuffetModifier(LocalDate date, List<BreakfastCycle> breakfastCycles) {
        this.date = date;
        this.breakfastCycles = breakfastCycles;
    }

    public Map<BreakfastCycle, List<Guest>> generateGuestsInBreakfastCycles (List<Guest> guestsOnActualDate) {
        int breakfastCyclesSize = breakfastCycles.size();
        Map<BreakfastCycle, List<Guest>> breakfastCycleSetMap = new HashMap<>(breakfastCyclesSize);
        for (BreakfastCycle breakfastCycle : breakfastCycles) {
            breakfastCycleSetMap.put(breakfastCycle, new ArrayList<>());
        }
        for (Guest guest : guestsOnActualDate) {
            Random random = new Random();
            breakfastCycleSetMap.get(breakfastCycles.get(random.nextInt(breakfastCyclesSize))).add(guest);
        }
        return breakfastCycleSetMap;
    }

    @Override
    public Buffet refill(Buffet buffet, List<Meal> meals) {
        for (Meal meal : meals) {
            buffet.addMeal(meal);
        }
        return buffet;
    }

    @Override
    public boolean consumeFreshest(Buffet buffet, MealType mealType) {
        List<Meal> filteredMeals = buffet.findPortions(mealType);
        if (filteredMeals.size() > 0) {
            buffet.removeMeal(filteredMeals.get(0));
            return true;
        }
        return false;
    }

    @Override
    public int collectWaste(MealDurability mealDurability, LocalTime timeStamp) {

        return 0; //The method needs to return the sum cost of the discarded meals (discarded units × unit cost).
    }
}

