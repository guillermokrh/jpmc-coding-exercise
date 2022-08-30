package com.jpmc.theater;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class LocalDateProviderTests {
    @Test
    void makeSureCurrentDate() {
        LocalDate provider = LocalDateProvider.singleton().currentDate();
        LocalDate localDate = LocalDate.now();
        assertEquals(provider, localDate);
    }
}
