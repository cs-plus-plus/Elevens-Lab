package Activity11;
/**
 * This is a class that plays the GUI version of the Thirteens game.
 * See accompanying documents for a description of how Thirteens is played.
 */
public class ThirteensGUIRunner11 {

	/**
	 * Plays the GUI version of Thirteens.
	 * @param args is not used.
	 */
	public static void main(String[] args) {
		Board11 board = new ThirteensBoard11();
		CardGameGUI11 gui = new CardGameGUI11(board);
		gui.displayGame();
	}
}