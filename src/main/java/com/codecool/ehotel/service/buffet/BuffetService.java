package com.codecool.ehotel.service.buffet;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Meal;

import java.util.List;

public interface BuffetService {
    Buffet refill(Buffet buffet, List<Meal> meals);
}
