package com.codecool.ehotel;

import com.codecool.ehotel.model.BreakfastCycle;
import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.service.breakfast.BreakfastManager;
import com.codecool.ehotel.service.buffet.BuffetModifier;
import com.codecool.ehotel.service.guest.GuestProvider;

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
                new BreakfastCycle(LocalTime.parse("06:00"), LocalTime.parse("06:30")),
                new BreakfastCycle(LocalTime.parse("06:30"), LocalTime.parse("07:00")),
                new BreakfastCycle(LocalTime.parse("07:00"), LocalTime.parse("07:30")),
                new BreakfastCycle(LocalTime.parse("07:30"), LocalTime.parse("08:00")),
                new BreakfastCycle(LocalTime.parse("08:00"), LocalTime.parse("08:30")),
                new BreakfastCycle(LocalTime.parse("08:30"), LocalTime.parse("09:00")),
                new BreakfastCycle(LocalTime.parse("09:00"), LocalTime.parse("09:30")),
                new BreakfastCycle(LocalTime.parse("09:30"), LocalTime.parse("10:00"))
        );
        LocalDate actualDate = LocalDate.parse("2022-12-31");
        Buffet buffet = new Buffet(new ArrayList<>());
        BuffetModifier buffetModifier = new BuffetModifier(actualDate, breakfastCycleList, buffet);
        // Generate guests for the season
        List<Guest> guests = new ArrayList<>();
        int guestNumber = 50;
        LocalDate seasonStart = LocalDate.parse("2022-12-28");
        LocalDate seasonEnd = LocalDate.parse("2023-01-03");
        for (int i = 0; i < guestNumber; i++) {
            guests.add(guestProvider.generateRandomGuest(seasonStart, seasonEnd));
        }

        List<Guest> dailyGuests = guestProvider.getGuestListOnActualDate(guests, actualDate);
        Map<BreakfastCycle, List<Guest>> breakfastCycleMap = buffetModifier.generateGuestsInBreakfastCycles(dailyGuests);
        // Run breakfast buffet
        BreakfastManager breakfastManager = new BreakfastManager(dailyGuests, buffet, breakfastCycleMap, breakfastCycleList, buffetModifier);
        for (BreakfastCycle breakfastCycle : buffetModifier.breakfastCycles) {
            System.out.println(breakfastCycle.cycleStart().toString() + "-" + breakfastCycle.cycleEnd().toString());
            guestProvider.listGuests(breakfastCycleMap.get(breakfastCycle).stream().toList());
            buffetModifier.listBuffet();
            breakfastManager.serve(breakfastCycle);
            System.out.println("Happy guests: " + breakfastManager.happyGuests);
            System.out.println("Unhappy guests: " + breakfastManager.unHappyGuests);
            System.out.println("Wasted food: " + buffetModifier.wastedFood);
        }
    }
}
