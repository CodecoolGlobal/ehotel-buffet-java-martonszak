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
         /*removeIF
        for (Meal element : meals) {
            if (element.getMealType().equals(meal.getMealType())
                    && element.getAmount() == meal.getAmount()
                    && element.getTimeStamp().equals(meal.getTimeStamp())) {
                meals.remove(element);
            }
        }*/

        meals.removeIf(element -> element.equals(meal));

    }

}
