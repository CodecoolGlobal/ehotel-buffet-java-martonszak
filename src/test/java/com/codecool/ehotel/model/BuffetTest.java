package com.codecool.ehotel.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BuffetTest {

    List<Meal> meals = new ArrayList<>();
    LocalTime tempTime;
    Buffet testBuffet;

    @BeforeEach
    void createData() {

        tempTime = LocalTime.parse("06:00");

        meals.addAll(List.of(
                new Meal(MealType.SCRAMBLED_EGGS, 1, tempTime),
                new Meal(MealType.SUNNY_SIDE_UP, 1, tempTime),
                new Meal(MealType.FRIED_SAUSAGE, 1, tempTime),
                new Meal(MealType.FRIED_BACON, 1, tempTime),
                new Meal(MealType.PANCAKE, 1, tempTime),
                new Meal(MealType.CROISSANT, 1, tempTime),
                new Meal(MealType.MASHED_POTATO, 1, tempTime),
                new Meal(MealType.MUFFIN, 1, tempTime),
                new Meal(MealType.BUN, 1, tempTime),
                new Meal(MealType.CEREAL, 1, tempTime),
                new Meal(MealType.MILK, 1, tempTime)
        ));

        testBuffet = new Buffet(meals);

    }

    @Test
    void findPortions() {

        LocalTime tempTime = LocalTime.parse("06:00");
        LocalTime tempTime2 = LocalTime.parse("07:00");
        LocalTime tempTime3 = LocalTime.parse("08:00");

        List<Meal> meals = List.of(
                new Meal(MealType.MILK, 1, tempTime2),
                new Meal(MealType.MILK, 1, tempTime3),
                new Meal(MealType.MILK, 1, tempTime),
                new Meal(MealType.CEREAL, 2, tempTime2)
        );

        List<Meal> sortedMealsTestList = List.of(
                new Meal(MealType.MILK, 1, tempTime3),
                new Meal(MealType.MILK, 1, tempTime2),
                new Meal(MealType.MILK, 1, tempTime)
        );

        Buffet testBuffet = new Buffet(meals);

        List<Meal> result = testBuffet.findPortions(MealType.MILK);

        assertEquals(sortedMealsTestList, result);

    }

    @Test
    void getMeals() {

        List<Meal> result = testBuffet.getMeals();
        assertEquals(meals, result);

    }

    @Test
    void addMeal() {

        assertEquals(11, testBuffet.getMeals().size());

        Meal testMeal = new Meal(MealType.MUFFIN, 1, tempTime);

        testBuffet.addMeal(testMeal);

        assertEquals(12, testBuffet.getMeals().size());
        assertEquals(testMeal, testBuffet.getMeals().get(11));

    }

    @Test
    void removeMeal() {

        assertEquals(11, testBuffet.getMeals().size());

        Meal testMeal = new Meal(MealType.MUFFIN, 1, tempTime);

        assertTrue(testBuffet.getMeals().contains(testMeal));

        testBuffet.removeMeal(testMeal);

        assertEquals(10, testBuffet.getMeals().size());
        assertFalse(testBuffet.getMeals().contains(testMeal));

    }

}