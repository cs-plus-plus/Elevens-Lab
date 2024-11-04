# Elevens Solitaire Game Project

## Introduction
This project involves creating and implementing various aspects of a solitaire game called Elevens. Through a series of activities, you will design classes, explore object-oriented principles, implement code, and build an interactive graphical user interface (GUI) to play the game. Each activity focuses on different components of the game, including deck and card handling, shuffling, inheritance, and testing using Java assertions.

## Activities and Objectives

### Activity 1 | Design and Create a Card Class
**Objective:** Complete the `Card` class to represent individual cards. Implement a constructor and methods for accessing card attributes (rank, suit, and point value), testing equality, and converting card data to a string format.
- **Exercises:** Define instance variables, create a constructor, and implement accessor methods, an equality checker, and `toString`.

### Activity 2 | Initial Design of a Deck Class
**Objective:** Develop the `Deck` class, which represents a collection of cards. Implement methods for deck operations, such as checking if it’s empty, determining the size, dealing cards, and initializing the deck with a specific configuration.
- **Exercises:** Complete the constructor, `isEmpty`, `size`, and `deal` methods. Test using `DeckTester.java`.

### Activity 3 | Shuffling the Cards in a Deck
**Objective:** Implement shuffling techniques in the `Shuffler` class. Study and implement the **perfect shuffle** and **efficient selection shuffle** methods, and analyze their effectiveness.
- **Exercises:** Write methods for both shuffle techniques in `Shuffler.java`.

### Activity 4 | Adding a Shuffle Method to the Deck Class
**Objective:** Add an efficient selection shuffle method directly to the `Deck` class and test it with a standard deck of 52 cards.
- **Exercises:** Implement the shuffle method in `Deck.java` and extend tests in `DeckTester.java`.

### Activity 5 | Testing with Assertions
**Objective:** Use Java assertions to systematically test the `Card` class. Assertions ensure that methods behave as expected, increasing code reliability.
- **Exercises:** Use `CardTester.java` to test methods, including rank, suit, point value, and equality. Enable assertions with the `-ea` flag.

### Activity 6 | Playing Elevens
**Objective:** Familiarize yourself with the rules of Elevens by playing the game via the GUI (`Elevens.jar`). This will help you understand the rules for further development and testing.
- **Questions:** Analyze different game states and possible strategies.

### Activity 7 | ElevensBoard Class Design
**Objective:** Design the `ElevensBoard` class, identifying private instance variables and writing an algorithm for actions in Elevens. Explore the helper methods used for game mechanics.
- **Questions:** Describe the required instance variables, helper methods, and their interactions in gameplay.

### Activity 8 | Using an Abstract Board Class
**Objective:** Use inheritance to reuse code common to Elevens and related games, such as Thirteens and Tens. Implement an abstract `Board` class for shared behavior and state.
- **Questions:** Discuss similarities between the games, understand initialization, and examine abstract methods.

### Activity 9 | Implementing the Elevens Board
**Objective:** Complete `ElevensBoard.java`, implementing methods to manage valid plays, check if another play is possible, and identify special combinations (like pairs summing to 11).
- **Exercises:** Implement `isLegal`, `anotherPlayIsPossible`, and helper methods `containsPairSum11` and `containsJQK`.

### Activity 10 | ThirteensBoard
**Objective:** Adapt the `ElevensBoard` code to create a `ThirteensBoard` class, implementing a similar solitaire game with different rules.
- **Exercises:** Copy `ElevensBoard.java` to `ThirteensBoard.java` and adjust rules. Update `ElevensGUIRunner.java` to create a `ThirteensGUIRunner.java`.

### Activity 11 | Simulation of Elevens
**Objective:** Use simulation to analyze win rates and game outcomes over multiple runs. Implement simulation-specific methods in `ElevensBoard`.
- **Exercises:** Modify `ElevensBoard` with methods like `playIfPossible` to automate gameplay, and use `ElevensSimulation` to run and analyze multiple games.

## Unit Tests Summary

| Unit Test Name                  | Description                                                                                  | Points |
|---------------------------------|----------------------------------------------------------------------------------------------|--------|
| `testCardClass`                 | Tests `Card` class methods for rank, suit, point value, equality, and string representation. | 15     |
| `testDeckClassActivity2`        | Verifies `Deck` class methods for creating a deck, dealing, checking size, and empty state.  | 20     |
| `testShufflerClass`             | Validates both perfect and selection shuffle methods in the `Shuffler` class.                | 20     |
| `testDeckClassActivity4`        | Ensures `Deck` class shuffle method functions correctly, maintaining deck size and contents. | 15     |
| `testElevensBoardClassActivity9`| Tests `ElevensBoard` methods to check possible plays and legal moves in the Elevens game.    | 15     |
| `testThirteensBoardClassActivity10` | Verifies `ThirteensBoard` functionality with specific rules for legal plays and board state. | 15     |

**Total Points:** 100

## Project Setup and Compilation
1. **Compile Shared Dependencies**: The project includes a `compileSharedDependencies` method in `ElevensTest` to ensure dependencies are compiled before individual tests.
2. **Organize Source Files**: All source files are organized under `src/Activity<Number>` folders, with main classes in `src/` and resources (like GUI files) in corresponding folders.
3. **Testing**: Use assertions throughout the code to verify functionality and ensure systematic and reliable testing.

## Contact
For questions or support, contact Kevin at [kevin@csplusplus.com](mailto:kevin@csplusplus.com).
