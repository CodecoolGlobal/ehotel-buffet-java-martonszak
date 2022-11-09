package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;

import java.time.LocalTime;
import java.util.List;

public class BuffetModifier implements BuffetService {
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

