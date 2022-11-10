package com.codecool.ehotel.service.ui;

import com.codecool.ehotel.model.Buffet;
import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.Meal;

import java.util.List;

public class Display {
    public static void listBuffet(Buffet buffet) {
        for (Meal meal : buffet.meals()) {
            System.out.println(meal.getMealType().toString() + " " + meal.getAmount() + " portions, made: " + meal.getTimeStamp().toString());
        }
    }

    public static void listGuests(List<Guest> guests) {
        for (Guest guest : guests) {
            System.out.println(guest.name() + " " + guest.guestType() + " " + guest.checkIn() + " " + guest.checkOut());
        }
    }
}
