package com.codecool.ehotel.service.guest;

import com.codecool.ehotel.model.Guest;
import com.codecool.ehotel.model.GuestType;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import static java.time.temporal.ChronoUnit.DAYS;

public class GuestProvider implements GuestService {

    private final List<String> firstNames = List.of("Thomas", "Martin", "Robert", "Feodora", "Rick", "Sequoia", "Mary", "Jennifer", "Karen", "Sarah");

    private final List<String> lastnames = List.of("Smith", "Wader", "Miller", "Wild", "White", "Hill", "Perez", "Quagmire", "Griffin", "Sandler");

    private String generateRandomGuestName(List<String> firstNames, List<String> lastnames) {
        Random random = new Random();
        String firstName = firstNames.get(random.nextInt(firstNames.size()));
        String lastName = lastnames.get(random.nextInt(lastnames.size()));
        String randomName = firstName + " " + lastName;
        return randomName;
    }

    private LocalDate randomCheckInOrOutDate(LocalDate seasonStart, LocalDate seasonEnd) {
        long seasonStartEpochDay = seasonStart.toEpochDay();
        long seasonEndEpochDay = seasonEnd.toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(seasonStartEpochDay, seasonEndEpochDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    public List<Guest> getGuestListOnActualDate(List<Guest> guests, LocalDate actualDate) {
        return guests.stream().filter(guest -> !actualDate.isAfter(guest.checkOut())
                && !actualDate.isBefore(guest.checkIn())
                && !actualDate.equals(guest.checkIn())).toList(); // when you check in you can't eat breakfast
    }


    @Override
    public Guest generateRandomGuest(LocalDate seasonStart, LocalDate seasonEnd) {
        long daysBetween = DAYS.between(seasonStart, seasonEnd);
        LocalDate randomCheckInDate = randomCheckInOrOutDate(seasonStart, seasonEnd);
        LocalDate randomMaxCheckOutDate = randomCheckInDate.plusDays(daysBetween >= 7 ? 7 : daysBetween);
        LocalDate randomCheckOutDate = randomCheckInOrOutDate(randomCheckInDate.plusDays(1), randomMaxCheckOutDate);
        Guest newRandomGuest = new Guest(generateRandomGuestName(firstNames, lastnames), GuestType.randomGuestType(), randomCheckInDate, randomCheckOutDate);
        return newRandomGuest;
    }

    @Override
    public Set<Guest> getGuestsForDay(List<Guest> guests, LocalDate date) {
        Set<Guest> guestsOnGivenDate = new HashSet<>();
        for (Guest guest : guests) {
            if (!date.isBefore(guest.checkIn()) && !date.isAfter(guest.checkOut())) {
                guestsOnGivenDate.add(guest);
            }
        }
        return guestsOnGivenDate;
    }
}
