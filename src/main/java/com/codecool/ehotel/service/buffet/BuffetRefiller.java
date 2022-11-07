package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;

import java.util.List;

public class BuffetRefiller implements BuffetService {
    @Override
    public Buffet refill(Buffet buffet, List<Meal> meals) {
        for (Meal meal : meals) {
            buffet.addMeal(meal);
        }
        return buffet;
    }
}

