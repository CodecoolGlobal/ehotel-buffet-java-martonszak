package com.codecool.ehotel.service.breakfast;

import com.codecool.ehotel.model.*;
import com.codecool.ehotel.service.buffet.BuffetModifier;

import java.util.*;

public class BreakfastManager {

    public List<Guest> dailyGuests;
    public Map<BreakfastCycle, List<Guest>> breakfastCycleMap;
    public List<BreakfastCycle> breakfastCycleList;
    private int businessGuests, touristGuests, kidGuests;
    private BuffetModifier buffetModifier;

    public int happyGuests = 0;

    public int unHappyGuests = 0;

    public BreakfastManager(List<Guest> dailyGuests, Map<BreakfastCycle, List<Guest>> breakfastCycleMap, List<BreakfastCycle> breakfastCycleList, BuffetModifier buffetModifier) {
        this.dailyGuests = dailyGuests;
        this.breakfastCycleMap = breakfastCycleMap;
        this.breakfastCycleList = breakfastCycleList;
        this.businessGuests = Collections.frequency(dailyGuests, GuestType.BUSINESS);
        this.touristGuests = Collections.frequency(dailyGuests, GuestType.TOURIST);
        this.kidGuests = Collections.frequency(dailyGuests, GuestType.KID);
        this.buffetModifier = buffetModifier;
    }


    public void serve(BreakfastCycle breakfastCycle) {
        buffetModifier.refill(getOptimalPortions(breakfastCycleMap.get(breakfastCycle), breakfastCycle));
        boolean guestIsHappy;

        for (Guest guest : breakfastCycleMap.get(breakfastCycle)) {
            guestIsHappy = false;
            //List<MealType> mealPreferences = guest.guestType().getMealPreferences();
            //mealPreferences.retainAll(buffet.getMeals().stream().map(Meal::getMealType).toList());
            for (MealType mealType : guest.guestType().getMealPreferences()) {

                if (!guestIsHappy && buffetModifier.buffet.getMeals().stream().map(Meal::getMealType).toList().contains(mealType)) {
                    buffetModifier.consumeFreshest(mealType);
                    happyGuests++;
                    guestIsHappy = true;
                }
            }
            if (!guestIsHappy) {
                unHappyGuests++;
            }
        }
        List<Meal> meals = buffetModifier.buffet.getMeals();
        for (Meal meal : meals) {
            buffetModifier.collectWaste(meal.getMealType().getDurability(), breakfastCycle.cycleEnd());
        }
    }

    public List<Meal> getOptimalPortions(List<Guest> guestsInActualBreakfastCycle, BreakfastCycle breakfastCycle) {
        Set<MealType> likedMealTypes = new HashSet<>();
        /*if (breakfastCycle.cycleStart().equals(LocalTime.parse("06:00"))) {
            for (Guest guest : dailyGuests) {
                likedMealTypes.addAll(guest.guestType().getMealPreferences());
            }
        }
        else {*/
            for (Guest guest : guestsInActualBreakfastCycle) {
                likedMealTypes.addAll(guest.guestType().getMealPreferences());
            }
        //}
        List<Meal> result = new ArrayList<>();
        for (MealType mealType : likedMealTypes) {
            result.add(new Meal(mealType, 2, breakfastCycle.cycleStart()));
        }
        return result;
    }

}
