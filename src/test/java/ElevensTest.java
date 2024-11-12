

import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import static org.junit.jupiter.api.Assertions.*;

public class ElevensTest {

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

        // Initial array for perfect shuffling
        int[] valuesEven = {1, 2, 3, 4};
        int[] originalValuesEven = valuesEven.clone();
        perfectShuffleMethod.invoke(null, (Object) valuesEven);

        // Check that the array length remains the same
        assertEquals(originalValuesEven.length, valuesEven.length, "Array length should remain the same after perfect shuffle");

        // Ensure the order has changed (since it's a perfect shuffle, we expect the cards to interleave)
        assertFalse(Arrays.equals(originalValuesEven, valuesEven), "Order should change after perfect shuffle");
        
        // Reset array and shuffle again to see if it produces the same interleaved result
        int[] valuesEvenSecondShuffle = originalValuesEven.clone();
        perfectShuffleMethod.invoke(null, (Object) valuesEvenSecondShuffle);
        assertArrayEquals(valuesEven, valuesEvenSecondShuffle, "Perfect shuffle should produce consistent interleaved results");

        // Testing selectionShuffle method
        Method selectionShuffleMethod = shufflerClass.getMethod("selectionShuffle", int[].class);

        int[] valuesSelection = {1, 2, 3, 4, 5};
        int[] originalValuesSelection = valuesSelection.clone();
        selectionShuffleMethod.invoke(null, (Object) valuesSelection);

        // Check that the array length remains the same
        assertEquals(originalValuesSelection.length, valuesSelection.length, "Array length should remain the same after selection shuffle");

        // Ensure the order has changed after selection shuffle
        assertFalse(Arrays.equals(originalValuesSelection, valuesSelection), "Order should change after selection shuffle");

        // Check that the shuffled result has the same elements as the original
        Arrays.sort(valuesSelection);
        Arrays.sort(originalValuesSelection);
        assertArrayEquals(originalValuesSelection, valuesSelection, "Shuffled array should contain the same elements as the original");
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
        Method anotherPlayIsPossibleMethod = elevensBoardClass.getMethod("anotherPlayIsPossible");
        Method isLegalMethod = elevensBoardClass.getMethod("isLegal", List.class);

        boolean playPossible = false;
        boolean validPlay = false;

        for (int i = 0; i < 100; i++) {
            Object elevensBoard = elevensBoardClass.getDeclaredConstructor().newInstance();

            // Check "anotherPlayIsPossible"
            if ((boolean) anotherPlayIsPossibleMethod.invoke(elevensBoard)) {
                playPossible = true;
            }

            // Check "isLegal" with various indices
            List<List<Integer>> testIndices = Arrays.asList(
                Arrays.asList(0, 1), Arrays.asList(0, 2), Arrays.asList(1, 2),
                Arrays.asList(0, 1, 2), Arrays.asList(0, 1, 3)
            );

            for (List<Integer> indices : testIndices) {
                if ((boolean) isLegalMethod.invoke(elevensBoard, indices)) {
                    validPlay = true;
                    break;
                }
            }

            if (playPossible && validPlay) {
                break; // Satisfied if at least one instance passes both conditions
            }
        }

        assertTrue(playPossible, "There should be possible plays at least once in 100 runs for Activity 9.");
        assertTrue(validPlay, "A pair summing to 11 or a valid trio should be legal at least once in 100 runs for Activity 9.");
    }

    @Test
    public void testThirteensBoardClassActivity10() throws Exception {
        Class<?> thirteensBoardClass = Class.forName("Activity10.ThirteensBoard10");
        Method anotherPlayIsPossibleMethod = thirteensBoardClass.getMethod("anotherPlayIsPossible");
        Method isLegalMethod = thirteensBoardClass.getMethod("isLegal", List.class);

        boolean playPossible = false;
        boolean validPlay = false;

        for (int i = 0; i < 100; i++) {
            Object thirteensBoard = thirteensBoardClass.getDeclaredConstructor().newInstance();

            // Check "anotherPlayIsPossible"
            if ((boolean) anotherPlayIsPossibleMethod.invoke(thirteensBoard)) {
                playPossible = true;
            }

            // Check "isLegal" with various indices
            List<List<Integer>> testIndices = Arrays.asList(
                Arrays.asList(0, 1), Arrays.asList(0, 2), Arrays.asList(1, 2),
                Arrays.asList(0, 1, 2), Arrays.asList(0, 1, 3)
            );

            for (List<Integer> indices : testIndices) {
                if ((boolean) isLegalMethod.invoke(thirteensBoard, indices)) {
                    validPlay = true;
                    break;
                }
            }

            if (playPossible && validPlay) {
                break; // Satisfied if at least one instance passes both conditions
            }
        }

        assertTrue(playPossible, "There should be possible plays at least once in 100 runs for Activity 10.");
        assertTrue(validPlay, "A valid play should be legal at least once in 100 runs for Activity 10.");
    }

    @Test
    public void testElevensSimulationClassActivity11() throws Exception {
        Class<?> elevensSimulationClass = Class.forName("Activity11.ElevensSimulation11");

        // Capture the standard output
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        boolean wonAtLeastOnce = false;
        int maxAttempts = 100;

        for (int i = 0; i < maxAttempts; i++) {
            // Clear the output stream for each simulation
            outputStreamCaptor.reset();

            // Run the main method to simulate a game
            Method mainMethod = elevensSimulationClass.getMethod("main", String[].class);
            mainMethod.invoke(null, (Object) new String[0]);

            // Check the captured output for "Games won: " line
            String output = outputStreamCaptor.toString();
            if (output.contains("Games won:    1")) {
                wonAtLeastOnce = true;
                break;
            }
        }

        // Reset the standard output to its original state
        System.setOut(originalOut);

        assertTrue(wonAtLeastOnce, "At least one game should result in a win across 100 attempts.");
    }


}
