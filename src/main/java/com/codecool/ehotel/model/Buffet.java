package com.codecool.ehotel.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public record Buffet(List<Meal> meals) {
    static List<Meal> filteredMeals = new ArrayList<>();

    public List<Meal> findPortions(MealType mealType) {
        for (Meal meal : meals) {
            if (meal.getMealType().equals(mealType)) {
                filteredMeals.add(meal);
            }
        }
        filteredMeals.sort(Comparator.comparing(Meal::getTimeStamp).reversed());
        return filteredMeals;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void addMeal(Meal meal) {
        meals.add(meal);
    }

}
