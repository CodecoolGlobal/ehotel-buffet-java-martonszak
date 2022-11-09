package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;
import com.codecool.ehotel.model.MealDurability;
import com.codecool.ehotel.model.MealType;

import java.time.LocalTime;
import java.util.List;

public interface BuffetService {
    Buffet refill(Buffet buffet, List<Meal> meals);

    boolean consumeFreshest(Buffet buffet, MealType mealType);

    int collectWaste(MealDurability mealDurability, LocalTime timeStamp); //Freshness wtf? :D
}
