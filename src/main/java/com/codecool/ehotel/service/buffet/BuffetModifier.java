package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.BreakfastCycle;
import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealType;

import java.time.LocalDate;
import java.util.List;

public class BuffetModifier implements BuffetService {

    LocalDate date;

    List<BreakfastCycle> breakfastCycles;

    public BuffetModifier(LocalDate date, List<BreakfastCycle> breakfastCycles) {
        this.date = date;
        this.breakfastCycles = breakfastCycles;
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
}

