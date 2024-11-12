package Activity11;
/**
 * This is a class that plays the GUI version of the Elevens game.
 * See accompanying documents for a description of how Elevens is played.
 */
public class ElevensGUIRunner11 {

	/**
	 * Plays the GUI version of Elevens.
	 * @param args is not used.
	 */
	public static void main(String[] args) {
		Board11 board = new ElevensBoard11();
		CardGameGUI11 gui = new CardGameGUI11(board);
		gui.displayGame();
	}
}
