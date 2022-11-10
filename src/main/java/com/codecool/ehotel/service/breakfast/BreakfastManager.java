package com.codecool.ehotel.service.breakfast;

import com.codecool.ehotel.model.*;
import com.codecool.ehotel.service.buffet.BuffetModifier;

import java.time.LocalTime;
import java.util.*;

public class BreakfastManager {

    public List<Guest> dailyGuests;
    public Map<BreakfastCycle, List<Guest>> breakfastCycleMap;
    public List<BreakfastCycle> breakfastCycleList;
    private final BuffetModifier buffetModifier;

    public int happyGuests = 0;

    public int unHappyGuests = 0;

    public BreakfastManager(List<Guest> dailyGuests, Map<BreakfastCycle, List<Guest>> breakfastCycleMap, List<BreakfastCycle> breakfastCycleList, BuffetModifier buffetModifier) {
        this.dailyGuests = dailyGuests;
        this.breakfastCycleMap = breakfastCycleMap;
        this.breakfastCycleList = breakfastCycleList;
        this.buffetModifier = buffetModifier;
    }


    public void serve(BreakfastCycle breakfastCycle) {
        buffetModifier.refill(getOptimalPortions(breakfastCycleMap.get(breakfastCycle), breakfastCycle));
        boolean guestIsHappy = false;

        for (Guest guest : breakfastCycleMap.get(breakfastCycle)) {

            guestIsHappy = false;
            for (MealType mealType : guest.guestType().getMealPreferences()) {

                if (!guestIsHappy && buffetModifier.buffet.getMeals().stream().map(Meal::getMealType).toList().contains(mealType)) {
                    guestIsHappy = buffetModifier.consumeFreshest(mealType);
                    happyGuests++;
                }
            }
            if (!guestIsHappy) {
                unHappyGuests++;
            }
        }

        buffetModifier.collectWaste(breakfastCycle.cycleEnd);
    }

    public int getIdealAmount() {
        double numberOfGuests = dailyGuests.size();
        return (int) Math.round(numberOfGuests/40);
    }

    public List<Meal> getOptimalPortions(List<Guest> guestsInActualBreakfastCycle, BreakfastCycle breakfastCycle) {
        Set<MealType> likedMealTypes = new HashSet<>();
        if (breakfastCycle.cycleStart.equals(LocalTime.parse("06:00"))) {
            for (Guest guest : dailyGuests) {
                likedMealTypes.addAll(guest.guestType().getMealPreferences());
            }
        } else {
            for (Guest guest : guestsInActualBreakfastCycle) {
                likedMealTypes.addAll(guest.guestType().getMealPreferences());
            }
        }
        List<Meal> result = new ArrayList<>();
        for (MealType mealType : likedMealTypes) {
            if (!buffetModifier.buffet.meals().stream().map(Meal::getMealType).toList().contains(mealType)) {
                result.add(new Meal(mealType, getIdealAmount(), breakfastCycle.cycleStart));
            }
        }
        return result;
    }

    public int getHappyGuests() {
        return happyGuests;
    }

    public int getUnHappyGuests() {
        return unHappyGuests;
    }
}
