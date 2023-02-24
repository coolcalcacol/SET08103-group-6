package com.napier.sem;

import com.napier.sem.database.DB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppIntegrationTest {
    static DB db;
    @BeforeAll
    static void init() {
        db = new DB("localhost", "33060", "world", "root", "group6Pass");

        db.connect();
    }

    @Test
    void basicTest() {
        assertEquals(1, 1);
    }
}
