package com.codecool.ehotel;

import com.codecool.ehotel.model.BreakfastCycle;
import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.service.breakfast.BreakfastManager;
import com.codecool.ehotel.service.buffet.BuffetModifier;
import com.codecool.ehotel.service.guest.GuestProvider;
import com.codecool.ehotel.service.ui.Display;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EHotelBuffetApplication {

    public static void main(String[] args) {

        // Initialize services
        GuestProvider guestProvider = new GuestProvider();
        List<BreakfastCycle> breakfastCycleList = List.of(
                new BreakfastCycle(LocalTime.parse("06:00"), 30),
                new BreakfastCycle(LocalTime.parse("06:30"), 30),
                new BreakfastCycle(LocalTime.parse("07:00"), 30),
                new BreakfastCycle(LocalTime.parse("07:30"), 30),
                new BreakfastCycle(LocalTime.parse("08:00"), 30),
                new BreakfastCycle(LocalTime.parse("08:30"), 30),
                new BreakfastCycle(LocalTime.parse("09:00"), 30),
                new BreakfastCycle(LocalTime.parse("09:30"), 30)
        );
        LocalDate actualDate = LocalDate.parse("2022-12-31");
        Buffet buffet = new Buffet(new ArrayList<>());
        BuffetModifier buffetModifier = new BuffetModifier(breakfastCycleList, buffet);
        // Generate guests for the season
        List<Guest> guests = new ArrayList<>();
        int guestNumber = 200;
        LocalDate seasonStart = LocalDate.parse("2022-12-28");
        LocalDate seasonEnd = LocalDate.parse("2023-01-03");
        for (int i = 0; i < guestNumber; i++) {
            guests.add(guestProvider.generateRandomGuest(seasonStart, seasonEnd));
        }

        List<Guest> dailyGuests = guestProvider.getGuestListOnActualDate(guests, actualDate);
        Map<BreakfastCycle, List<Guest>> breakfastCycleMap = buffetModifier.generateGuestsInBreakfastCycles(dailyGuests);
        // Run breakfast buffet
        BreakfastManager breakfastManager = new BreakfastManager(dailyGuests, breakfastCycleMap, breakfastCycleList, buffetModifier);
        for (BreakfastCycle breakfastCycle : buffetModifier.breakfastCycles) {
            System.out.println(breakfastCycle.cycleStart.toString() + "-" + breakfastCycle.cycleEnd.toString());
            Display.listGuests(breakfastCycleMap.get(breakfastCycle).stream().toList());
            breakfastManager.serve(breakfastCycle);
            System.out.println("Buffet at " + breakfastCycle.cycleEnd + ":");
            Display.listBuffet(buffet);
            System.out.println("Happy guests: " + breakfastManager.happyGuests);
            System.out.println("Unhappy guests: " + breakfastManager.unHappyGuests);
            System.out.println("Wasted food: " + buffetModifier.wastedFood);
        }
    }
}
