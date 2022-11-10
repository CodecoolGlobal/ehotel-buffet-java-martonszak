package com.codecool.ehotel.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public record Buffet(List<Meal> meals) {
    public List<Meal> findPortions(MealType mealType) {
        List<Meal> filteredMeals = new ArrayList<>();
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

    public void removeMeal(Meal meal) {

        meal.decreaseAmount();
        if (meal.getAmount() < 1) {
            meals.removeIf(element -> element.equals(meal));
        }
    }

    public void trashMeal(Meal meal) {
        meals.removeIf(element -> element.equals(meal));
    }
}
