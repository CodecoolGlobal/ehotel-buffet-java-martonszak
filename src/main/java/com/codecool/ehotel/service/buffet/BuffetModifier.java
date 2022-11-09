package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;

import java.time.LocalTime;
import java.util.List;

public class BuffetModifier implements BuffetService {

    Buffet buffet;

    public int wastedFood = 0;

    public BuffetModifier(Buffet buffet) {
        this.buffet = buffet;
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

        int discardedMealsCost = 0;

        LocalTime timeLimit = timeStamp.minusMinutes(90);
        if (mealDurability == MealDurability.MEDIUM) {
            timeLimit = timeStamp.minusMinutes(120);
        } else if (mealDurability == MealDurability.LONG) {
            timeLimit = timeStamp.minusMinutes(180);
        }

        for (Meal meal : buffet.getMeals()) {
            if (meal.getMealType().getDurability() == mealDurability && meal.getTimeStamp().isBefore(timeLimit)) {
                buffet.removeMeal(meal);
                discardedMealsCost += meal.getMealType().getCost();
            }
        }
        wastedFood += discardedMealsCost;
        return discardedMealsCost;
    }
}

