package com.codecool.ehotel;

import com.codecool.ehotel.model.BreakfastCycle;
import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.service.breakfast.BreakfastManager;
import com.codecool.ehotel.service.buffet.BuffetModifier;
import com.codecool.ehotel.service.guest.GuestProvider;
import com.codecool.ehotel.service.ui.Display;
import com.codecool.ehotel.service.ui.Input;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EHotelBuffetApplication {

    public static void main(String[] args) {

        // Initialize services
        LocalDate actualDate = LocalDate.parse("2022-12-31"), seasonStart = LocalDate.parse("2022-12-28"), seasonEnd = LocalDate.parse("2023-01-03");
        Input input = new Input();
        Buffet buffet = new Buffet(new ArrayList<>());
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
        BuffetModifier buffetModifier = new BuffetModifier(breakfastCycleList, buffet);
        GuestProvider guestProvider = new GuestProvider();


        // Program start with questions for user input

        Display.printLogo();
        Display.printText("Please choose which statistics you want to see, DETAILED od SIMPLE!");
        Display.printText("Type in DETAILED or SIMPLE below.");
        String answer = input.getTextInput();
        Display.printText("Please type in the number of guests you want to make the simulation with:");
        int answerGuestsNumber = input.getNumberInput();
        List<Guest> allGuests = guestProvider.generateGuests(answerGuestsNumber, seasonStart, seasonEnd);
        List<Guest> dailyGuests = guestProvider.getGuestListOnActualDate(allGuests, actualDate);
        Map<BreakfastCycle, List<Guest>> breakfastCycleMap = buffetModifier.generateGuestsInBreakfastCycles(dailyGuests);
        BreakfastManager breakfastManager = new BreakfastManager(dailyGuests, breakfastCycleMap, breakfastCycleList, buffetModifier);

        if (answer.equalsIgnoreCase("simple")) {
            for (BreakfastCycle breakfastCycle : buffetModifier.breakfastCycles) {
                breakfastManager.serve(breakfastCycle);
            }
            Display.listSimpleStatistics(buffetModifier.getRefillCost(), breakfastManager.getHappyGuests(), breakfastManager.getUnHappyGuests(), buffetModifier.getWastedFood());
        } else if (answer.equalsIgnoreCase("detailed")) {
            for (BreakfastCycle breakfastCycle : buffetModifier.breakfastCycles) {
                System.out.println(breakfastCycle.cycleStart.toString() + "-" + breakfastCycle.cycleEnd.toString());
                Display.listGuests(breakfastCycleMap.get(breakfastCycle).stream().toList());
                breakfastManager.serve(breakfastCycle);
                System.out.println("Buffet at " + breakfastCycle.cycleEnd + ":");
                Display.listSimpleStatistics(buffetModifier.getRefillCost(), breakfastManager.getHappyGuests(), breakfastManager.getUnHappyGuests(), buffetModifier.getWastedFood());

            }
        }
    }
}
