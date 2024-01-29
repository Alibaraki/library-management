package com.baraki.librarymanagement.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PatronTest {

    private Patron patron;

    @BeforeEach
    void setUp() {
        patron = new Patron();
        patron.setId(1L);
        patron.setName("John Doe");
        patron.setContactInformation("1234567890");
    }

    @Test
    void testGetterAndSetter() {
        assertEquals(1L, patron.getId());
        assertEquals("John Doe", patron.getName());
        assertEquals("1234567890", patron.getContactInformation());
    }

    @Test
    void testEquals() {
        Patron anotherPatron = new Patron();
        anotherPatron.setId(1L);

        assertTrue(patron.equals(anotherPatron) && anotherPatron.equals(patron));
        assertEquals(patron.hashCode(), anotherPatron.hashCode());
    }

    @Test
    void testNotEquals() {
        Patron anotherPatron = new Patron();
        anotherPatron.setId(2L);

        assertFalse(patron.equals(anotherPatron));
        assertNotEquals(patron.hashCode(), anotherPatron.hashCode());
    }

    @Test
    void testToString() {
        String expectedString = "Patron{id=1, name='John Doe', contactInformation='1234567890'}";
        assertEquals(expectedString, patron.toString());
    }
}