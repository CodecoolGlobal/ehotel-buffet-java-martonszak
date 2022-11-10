package com.codecool.ehotel.model;

import java.time.LocalTime;

public class BreakfastCycle {
    public LocalTime cycleStart;
    public LocalTime cycleEnd;
    public long cycleDuration;

    public BreakfastCycle(LocalTime cycleStart, long cycleDuration) {
        this.cycleStart = cycleStart;
        this.cycleEnd = cycleStart.plusMinutes(cycleDuration);
        this.cycleDuration = cycleDuration;
    }
}
