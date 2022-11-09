package com.codecool.ehotel.service.breakfast;

import com.codecool.ehotel.model.*;
import com.codecool.ehotel.service.buffet.BuffetModifier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BreakfastManager {

    public List<Guest> dailyGuests;
    public Buffet buffet;
    public Map<BreakfastCycle, List<Guest>> breakfastCycleMap;
    public List<BreakfastCycle> breakfastCycleList;
    private int businessGuests, touristGuests, kidGuests;
    private BuffetModifier buffetModifier;

    public int happyGuests = 0;

    public int unHappyGuests = 0;

    public BreakfastManager(List<Guest> dailyGuests, Buffet buffet, Map<BreakfastCycle, List<Guest>> breakfastCycleMap, List<BreakfastCycle> breakfastCycleList, BuffetModifier buffetModifier) {
        this.dailyGuests = dailyGuests;
        this.buffet = buffet;
        this.breakfastCycleMap = breakfastCycleMap;
        this.breakfastCycleList = breakfastCycleList;
        this.businessGuests = Collections.frequency(dailyGuests, GuestType.BUSINESS);
        this.touristGuests = Collections.frequency(dailyGuests, GuestType.TOURIST);
        this.kidGuests = Collections.frequency(dailyGuests, GuestType.KID);
        this.buffetModifier = buffetModifier;
    }


    public void serve(BreakfastCycle breakfastCycle) {
        buffetModifier.refill(buffet, getOptimalPortions());
        boolean guestIsHappy;

        for (Guest guest : breakfastCycleMap.get(breakfastCycle)) {
            guestIsHappy = false;
            //List<MealType> mealPreferences = guest.guestType().getMealPreferences();
            //mealPreferences.retainAll(buffet.getMeals().stream().map(Meal::getMealType).toList());
            for (MealType mealType : guest.guestType().getMealPreferences()) {

                if (!guestIsHappy && buffet.getMeals().stream().map(Meal::getMealType).toList().contains(mealType)) {
                    buffetModifier.consumeFreshest(buffet, mealType);
                    happyGuests++;
                    guestIsHappy = true;
                }
            }
            if (!guestIsHappy) {
                unHappyGuests++;
            }
        }

        for (Meal meal : buffet.getMeals()) {
            buffetModifier.collectWaste(meal.getMealType().getDurability(), breakfastCycle.cycleEnd());
        }
    }

    public List<Meal> getOptimalPortions() {

        return new ArrayList<>();
    }

}
