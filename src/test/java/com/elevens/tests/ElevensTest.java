package com.elevens.tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ElevensTest {

    // Setup for capturing console output
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testCardClass() throws Exception {
        Class<?> cardClass = Class.forName("Activity1.Card1");
        Object card = cardClass.getDeclaredConstructor(String.class, String.class, int.class)
                .newInstance("ace", "spades", 1);

        Method rankMethod = cardClass.getMethod("rank");
        assertEquals("ace", rankMethod.invoke(card), "Rank should be ace");

        Method suitMethod = cardClass.getMethod("suit");
        assertEquals("spades", suitMethod.invoke(card), "Suit should be spades");

        Method pointValueMethod = cardClass.getMethod("pointValue");
        assertEquals(1, pointValueMethod.invoke(card), "Point value should be 1");

        Method matchesMethod = cardClass.getMethod("matches", cardClass);
        assertTrue((boolean) matchesMethod.invoke(card, card), "Card should match itself");
    }

    @Test
    public void testDeckClassActivity2() throws Exception {
        Class<?> deckClass = Class.forName("Activity2.Deck2");
        String[] ranks = {"ace", "2", "3"};
        String[] suits = {"hearts", "spades"};
        int[] pointValues = {1, 2, 3};
        Object deck = deckClass.getDeclaredConstructor(String[].class, String[].class, int[].class)
                .newInstance(ranks, suits, pointValues);

        Method sizeMethod = deckClass.getMethod("size");
        assertEquals(6, sizeMethod.invoke(deck), "Deck size should be 6");

        Method dealMethod = deckClass.getMethod("deal");
        dealMethod.invoke(deck);
        assertEquals(5, sizeMethod.invoke(deck), "Deck size should be 5 after one deal");

        Method shuffleMethod = deckClass.getMethod("shuffle");
        shuffleMethod.invoke(deck);
        assertEquals(5, sizeMethod.invoke(deck), "Deck size should remain 5 after shuffle");
    }

    @Test
    public void testShufflerClass() throws Exception {
        Class<?> shufflerClass = Class.forName("Activity3.Shuffler3");
        
        // Testing perfectShuffle method
        Method perfectShuffleMethod = shufflerClass.getMethod("perfectShuffle", int[].class);
        
        // Initial array for shuffling
        int[] originalValues = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] shuffledValues = originalValues.clone();
        
        // Perform perfect shuffle
        perfectShuffleMethod.invoke(null, (Object) shuffledValues);

        // Check if perfect shuffle interleaves the two halves correctly
        int mid = originalValues.length / 2;
        for (int i = 0; i < mid; i++) {
            assertEquals(originalValues[i], shuffledValues[i * 2], "Perfect shuffle should place cards from first half in even positions");
            assertEquals(originalValues[i + mid], shuffledValues[i * 2 + 1], "Perfect shuffle should place cards from second half in odd positions");
        }

        // Ensuring array length remains the same
        assertEquals(originalValues.length, shuffledValues.length, "Array length should remain the same after perfect shuffle");

        // Testing selectionShuffle method
        Method selectionShuffleMethod = shufflerClass.getMethod("selectionShuffle", int[].class);

        int[] selectionShuffleValues = originalValues.clone();
        selectionShuffleMethod.invoke(null, (Object) selectionShuffleValues);

        // Check that the order of cards has changed after selection shuffle
        assertNotEquals(Arrays.toString(originalValues), Arrays.toString(selectionShuffleValues), "Cards order should change after selection shuffle");
    }


    @Test
    public void testDeckClassActivity4() throws Exception {
        Class<?> deckClass = Class.forName("Activity4.Deck4");
        String[] ranks = {"jack", "queen", "king"};
        String[] suits = {"diamonds", "clubs"};
        int[] pointValues = {10, 10, 10};
        Object deck = deckClass.getDeclaredConstructor(String[].class, String[].class, int[].class)
                .newInstance(ranks, suits, pointValues);

        Method sizeMethod = deckClass.getMethod("size");
        assertEquals(6, sizeMethod.invoke(deck), "Deck size should be 6");

        Method isEmptyMethod = deckClass.getMethod("isEmpty");
        assertFalse((boolean) isEmptyMethod.invoke(deck), "Deck should not be empty initially");

        Method dealMethod = deckClass.getMethod("deal");
        dealMethod.invoke(deck);
        assertEquals(5, sizeMethod.invoke(deck), "Deck size should be 5 after one deal");
    }

    @Test
    public void testElevensBoardClassActivity9() throws Exception {
        Class<?> elevensBoardClass = Class.forName("Activity9.ElevensBoard9");
        Object elevensBoard = elevensBoardClass.getDeclaredConstructor().newInstance();

        Method anotherPlayIsPossibleMethod = elevensBoardClass.getMethod("anotherPlayIsPossible");
        boolean playPossible = false;
        for (int i = 0; i < 100; i++) {
        	elevensBoard = elevensBoardClass.getDeclaredConstructor().newInstance();
            if ((boolean) anotherPlayIsPossibleMethod.invoke(elevensBoard)) {
                playPossible = true;
                break;
            }
        }
        assertTrue(playPossible, "There should be possible plays at least once in 100 runs for Activity 9.");

        Method isLegalMethod = elevensBoardClass.getMethod("isLegal", List.class);
        boolean validPlay = false;
        List<List<Integer>> testIndices = Arrays.asList(
                Arrays.asList(0, 1), Arrays.asList(0, 2), Arrays.asList(1, 2),
                Arrays.asList(0, 1, 2), Arrays.asList(0, 1, 3)
        );

        for (int i = 0; i < 100; i++) {
        	elevensBoard = elevensBoardClass.getDeclaredConstructor().newInstance();
            for (List<Integer> indices : testIndices) {
                if ((boolean) isLegalMethod.invoke(elevensBoard, indices)) {
                    validPlay = true;
                    break;
                }
            }
            if (validPlay) break;
        }
        assertTrue(validPlay, "A pair summing to 11 or a valid trio should be legal at least once in 100 runs for Activity 9.");
    }

    @Test
    public void testThirteensBoardClassActivity10() throws Exception {
        Class<?> thirteensBoardClass = Class.forName("Activity10.ThirteensBoard10");
        Object thirteensBoard = thirteensBoardClass.getDeclaredConstructor().newInstance();

        Method anotherPlayIsPossibleMethod = thirteensBoardClass.getMethod("anotherPlayIsPossible");
        boolean playPossible = false;
        for (int i = 0; i < 100; i++) {
        	thirteensBoard = thirteensBoardClass.getDeclaredConstructor().newInstance();
            if ((boolean) anotherPlayIsPossibleMethod.invoke(thirteensBoard)) {
                playPossible = true;
                break;
            }
        }
        assertTrue(playPossible, "There should be possible plays at least once in 100 runs for Activity 10.");

        Method isLegalMethod = thirteensBoardClass.getMethod("isLegal", List.class);
        boolean validPlay = false;
        List<List<Integer>> testIndices = Arrays.asList(
                Arrays.asList(0, 1), Arrays.asList(0, 2), Arrays.asList(1, 2),
                Arrays.asList(0, 1, 2), Arrays.asList(0, 1, 3)
        );

        for (int i = 0; i < 100; i++) {
        	thirteensBoard = thirteensBoardClass.getDeclaredConstructor().newInstance();
            for (List<Integer> indices : testIndices) {
                if ((boolean) isLegalMethod.invoke(thirteensBoard, indices)) {
                    validPlay = true;
                    break;
                }
            }
            if (validPlay) break;
        }
        assertTrue(validPlay, "A valid play should be legal at least once in 100 runs for Activity 10.");
    }

    @Test
    public void testElevensSimulationClassActivity11() throws Exception {
        Class<?> elevensSimulationClass = Class.forName("Activity11.ElevensSimulation11");

        // Set up the environment to capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            // Call the main method to run the simulation
            Method mainMethod = elevensSimulationClass.getMethod("main", String[].class);
            mainMethod.invoke(null, (Object) new String[0]);

            // Capture the output to check for signs of successful play logic
            String output = outputStream.toString();

            // Check if the debug output reflects plays occurring
            assertTrue(output.contains("Games won:"), "Simulation should print the number of games won.");
            assertTrue(output.contains("Percent won:"), "Simulation should print the percentage of games won.");
            
            // Ensure that the win percentage is non-zero, which should only happen if play logic is correct
            assertFalse(output.contains("Percent won: 0.0%"), 
                "Percent won should be greater than zero if playIfPossible and helper methods are implemented correctly.");

        } finally {
            // Reset standard output back to the console
            System.setOut(originalOut);
        }
    }
}
