package com.codecool.ehotel.model;

import java.time.LocalTime;
import java.util.Objects;

public class Meal {
    private final MealType mealType;
    private int amount;
    private final LocalTime timeStamp;

    public Meal(MealType mealType, int amount, LocalTime timeStamp) {
        this.mealType = mealType;
        this.amount = amount;
        this.timeStamp = timeStamp;
    }

    public MealType getMealType() {
        return mealType;
    }

    public int getAmount() {
        return amount;
    }

    public LocalTime getTimeStamp() {
        return timeStamp;
    }

    public void decreaseAmount() {
        this.amount--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return amount == meal.amount && mealType == meal.mealType && Objects.equals(timeStamp, meal.timeStamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mealType, amount, timeStamp);
    }
}