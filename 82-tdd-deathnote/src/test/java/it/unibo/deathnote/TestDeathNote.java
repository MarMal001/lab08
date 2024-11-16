package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.*;

class TestDeathNote {
    
    private DeathNote deathnote;
    
    @BeforeEach
    void crateDeathNote() {
        deathnote = new DeathNoteImplementation();
    }

    @Test
    void checkInvalidRules() {
        try {
            deathnote.getRule(0);
            deathnote.getRule(-5);
            deathnote.getRule(DeathNote.RULES.size() + 1);
            Assertions.fail();
        } catch (final IllegalArgumentException exception){
            System.out.println("The rule searched is non-existent");
        }
    }

    @Test
    void checkNullOrEmptyRules() {
        for (final String rule : DeathNote.RULES) {
            assertNotNull(rule);
            assertFalse(rule.isEmpty());
        }
    }

    @Test
    void checkCorretWritingName() {
        assertFalse(deathnote.isNameWritten("L"));
        deathnote.writeName("L");
        assertTrue(deathnote.isNameWritten("L"));
        assertFalse(deathnote.isNameWritten("Near"));
        assertFalse(deathnote.isNameWritten(""));
    }

    @Test
    void checkCauseDeathInTime() throws InterruptedException {
        try {
            deathnote.writeDeathCause("Sugar overdose");
        } catch (final IllegalStateException exception) {
            System.out.println("You have to write the name of the victim before");
        }
        deathnote.writeName("Misa Amane");
        assertEquals("heart attack", deathnote.getDeathCause("Misa Amane"));
        deathnote.writeDeathCause("karting accident");
        assertEquals("karting accident", deathnote.getDeathCause("Misa Amane"));
        Thread.sleep(100);
        assertFalse(deathnote.writeDeathCause("Crying over Light"));
    }

    @Test 
    void checkDetailDeathInTime() throws InterruptedException {
        try {
            deathnote.writeDetails("The victim ran for too long");
        } catch (final IllegalStateException exception) {
            System.out.println("You have to write the name of the victim before");
        }
        deathnote.writeName("Light Yagami");
        assertEquals("", deathnote.getDeathDetails("Light Yagami"));
        deathnote.writeDetails("The victim ran for too long");
        assertEquals("The victim ran for too long", deathnote.getDeathDetails("Light Yagami"));
        deathnote.writeName("Mihael Keehl");
        Thread.sleep(6100);
        assertFalse(deathnote.writeDetails("Emotional Damage"));
    }
}