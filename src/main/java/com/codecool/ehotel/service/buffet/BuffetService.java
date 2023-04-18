package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealType;

import java.time.LocalTime;
import java.util.List;

public interface BuffetService {
    Buffet refill(List<Meal> meals);

    boolean consumeFreshest(MealType mealType);

    int collectWaste(LocalTime breakfastCycleEnd);
}
