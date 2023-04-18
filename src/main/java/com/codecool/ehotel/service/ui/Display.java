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

    public static void listSimpleStatistics(int totalCost, int happyGuests, int unHappyGuests, int wastedFood) {
        System.out.println("Happy guests: " + happyGuests);
        System.out.println("Unhappy guests: " + unHappyGuests);
        System.out.println("Wasted foods cost: " + wastedFood);
        System.out.println("Total cost of the days breakfast meals: " + totalCost);
    }

    public static void printText(String text) {
        System.out.println(text + "\n");
    }

    public static void printLogo() {
        System.out.println("""                                                         
                    _H_              _H_               _H_                  o88o.
                  .=|_|===========v==|_|============v==|_|===========.    (8%8898),
                 /                |                 |                 \\ ,(8888%8688)
                /_________________|_________________|__________________(898%88688HJW)
                |=|_|_|_|  =|_|_|=|X|)^^^(|X|=|/ \\|=||_|_|_|=| ||_|_|=|`(86888%8%9b)
                |=|_|_|_|== |_|_|=|X|\\___/|X|=||_||=||_____|=|_||_|_|=|___(88%%8888)
                |=_________= ,-. =|""\"""\"""\""=""\"""=|=_________== == =|_______\\//`'
                |=|__|__|_| //O\\\\=|X|""\"""|X|=//"\\\\=|=|_|_|_|_| .---.=|.=====.||
                |=|__|__|_|=|| ||=|X|_____|X|=|| ||=|=|_______|=||"||=||=====|||
                |___d%8b____||_||_|=_________=||_||_|__d8%o%8b_=|j_j|=|j==o==j|\\---
                                                        
                ███████╗    ██╗  ██╗ ██████╗ ████████╗███████╗██╗         ███████╗██╗███╗   ███╗██╗   ██╗██╗      █████╗ ████████╗ ██████╗ ██████╗\s
                ██╔════╝    ██║  ██║██╔═══██╗╚══██╔══╝██╔════╝██║         ██╔════╝██║████╗ ████║██║   ██║██║     ██╔══██╗╚══██╔══╝██╔═══██╗██╔══██╗
                █████╗█████╗███████║██║   ██║   ██║   █████╗  ██║         ███████╗██║██╔████╔██║██║   ██║██║     ███████║   ██║   ██║   ██║██████╔╝
                ██╔══╝╚════╝██╔══██║██║   ██║   ██║   ██╔══╝  ██║         ╚════██║██║██║╚██╔╝██║██║   ██║██║     ██╔══██║   ██║   ██║   ██║██╔══██╗
                ███████╗    ██║  ██║╚██████╔╝   ██║   ███████╗███████╗    ███████║██║██║ ╚═╝ ██║╚██████╔╝███████╗██║  ██║   ██║   ╚██████╔╝██║  ██║
                ╚══════╝    ╚═╝  ╚═╝ ╚═════╝    ╚═╝   ╚══════╝╚══════╝    ╚══════╝╚═╝╚═╝     ╚═╝ ╚═════╝ ╚══════╝╚═╝  ╚═╝   ╚═╝    ╚═════╝ ╚═╝  ╚═╝
                                                                                                                                                  \s
                ██╗   ██╗     ██████╗    ██╗                                                                                                      \s
                ██║   ██║    ██╔═████╗  ███║                                                                                                      \s
                ██║   ██║    ██║██╔██║  ╚██║                                                                                                      \s
                ╚██╗ ██╔╝    ████╔╝██║   ██║                                                                                                      \s
                 ╚████╔╝     ╚██████╔╝██╗██║                                                                                                      \s
                  ╚═══╝       ╚═════╝ ╚═╝╚═╝                                                                                                       \s
                                                                                                                                                  \s
                """);
    }

}
