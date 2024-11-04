package com.csplusplus;

import org.junit.jupiter.api.Test;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import javax.tools.StandardJavaFileManager;
import javax.tools.JavaFileObject;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ElevensTest {

    private static final String OUTPUT_DIRECTORY = "bin";

    private void compileSharedDependencies() throws IOException {
        String[] orderedSharedFiles = {
            "src/Activity1/Card.java",
            "src/Activity2/Deck.java",
            "src/Activity9/Board.java",
            "src/Activity10/CardGameGUI.java"
        };

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new IllegalStateException("Cannot find Java compiler. Ensure JDK is installed.");
        }

        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        for (String filePath : orderedSharedFiles) {
            List<File> sourceFile = List.of(new File(filePath));
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(sourceFile);

            List<String> options = Arrays.asList("-d", OUTPUT_DIRECTORY, "-classpath", OUTPUT_DIRECTORY);

            if (compiler.getTask(null, fileManager, null, options, null, compilationUnits).call() == false) {
                throw new IOException("Compilation failed for shared dependency: " + filePath);
            }
        }
        fileManager.close();
    }

    private void compileAllJavaFilesInFolder(String folderPath) throws IOException {
        File folder = new File(folderPath);
        File[] javaFiles = folder.listFiles((dir, name) -> name.endsWith(".java"));

        if (javaFiles == null || javaFiles.length == 0) {
            throw new IOException("No Java files found in folder: " + folderPath);
        }

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new IllegalStateException("Cannot find Java compiler. Ensure JDK is installed.");
        }

        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        for (File javaFile : javaFiles) {
            List<File> sourceFile = List.of(javaFile);
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(sourceFile);

            List<String> options = Arrays.asList("-d", OUTPUT_DIRECTORY, "-classpath", OUTPUT_DIRECTORY);

            if (compiler.getTask(null, fileManager, null, options, null, compilationUnits).call() == false) {
                throw new IOException("Compilation failed for: " + javaFile.getPath());
            }
        }
        fileManager.close();
    }

    private Class<?> loadClassFromActivity(String activityName, String className) throws Exception {
        String folderPath = "src/" + activityName + "/";

        compileSharedDependencies();

        compileAllJavaFilesInFolder(folderPath);

        File classFolder = new File(OUTPUT_DIRECTORY);
        URLClassLoader classLoader = new URLClassLoader(new URL[]{classFolder.toURI().toURL()});

        return classLoader.loadClass(className);
    }

    @Test
    public void testCardClass() throws Exception {
        Class<?> cardClass = loadClassFromActivity("Activity1", "Card");
        assertNotNull(cardClass, "Card class should be loaded");

        // Additional tests for Card class methods
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
        Class<?> deckClass = loadClassFromActivity("Activity2", "Deck");
        assertNotNull(deckClass, "Deck class should be loaded for Activity2");

        // Additional tests for Deck class methods
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
        Class<?> shufflerClass = loadClassFromActivity("Activity3", "Shuffler");
        assertNotNull(shufflerClass, "Shuffler class should be loaded");

        // Testing perfectShuffle method
        Method perfectShuffleMethod = shufflerClass.getMethod("perfectShuffle", int[].class);

        // Ensuring an even-length array for perfect shuffling
        int[] valuesEven = {1, 2, 3, 4}; // Use an even length
        perfectShuffleMethod.invoke(null, (Object) valuesEven); 
        assertEquals(4, valuesEven.length, "Array length should remain the same after perfect shuffle");

        // Testing selectionShuffle method
        Method selectionShuffleMethod = shufflerClass.getMethod("selectionShuffle", int[].class);

        int[] valuesSelection = {1, 2, 3, 4, 5};
        selectionShuffleMethod.invoke(null, (Object) valuesSelection); 
        assertEquals(5, valuesSelection.length, "Array length should remain the same after selection shuffle");
    }



    @Test
    public void testDeckClassActivity4() throws Exception {
        Class<?> deckClass = loadClassFromActivity("Activity4", "Deck");
        assertNotNull(deckClass, "Deck class should be loaded for Activity4");

        // Additional tests similar to Activity2's Deck to ensure consistency
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
        Class<?> elevensBoardClass = loadClassFromActivity("Activity9", "ElevensBoard");
        assertNotNull(elevensBoardClass, "ElevensBoard class should be loaded for Activity9");

        // Additional tests for ElevensBoard methods
        Object elevensBoard = elevensBoardClass.getDeclaredConstructor().newInstance();

        Method anotherPlayIsPossibleMethod = elevensBoardClass.getMethod("anotherPlayIsPossible");
        assertTrue((boolean) anotherPlayIsPossibleMethod.invoke(elevensBoard), "There should be possible plays initially");

//        Method isLegalMethod = elevensBoardClass.getMethod("isLegal", List.class);
//        List<Integer> indices = new ArrayList<>(Arrays.asList(0, 1));
//        assertTrue((boolean) isLegalMethod.invoke(elevensBoard, indices), "A pair summing to 11 or a valid trio should be legal");
    }

    @Test
    public void testThirteensBoardClassActivity10() throws Exception {
        Class<?> thirteensBoardClass = loadClassFromActivity("Activity10", "ThirteensBoard");
        assertNotNull(thirteensBoardClass, "ThirteensBoard class should be loaded for Activity10");

        // Additional tests for ThirteensBoard methods
        Object thirteensBoard = thirteensBoardClass.getDeclaredConstructor().newInstance();

        Method anotherPlayIsPossibleMethod = thirteensBoardClass.getMethod("anotherPlayIsPossible");
        assertTrue((boolean) anotherPlayIsPossibleMethod.invoke(thirteensBoard), "There should be possible plays initially");

//        Method isLegalMethod = thirteensBoardClass.getMethod("isLegal", List.class);
//        List<Integer> indices = new ArrayList<>(Arrays.asList(0, 1));
//        assertTrue((boolean) isLegalMethod.invoke(thirteensBoard, indices), "A valid play should be legal in ThirteensBoard");
    }
}
