package Activity10;
/**
 * This is a class that plays the GUI version of the Elevens game.
 * See accompanying documents for a description of how Elevens is played.
 */
public class ElevensGUIRunner10 {

	/**
	 * Plays the GUI version of Elevens.
	 * @param args is not used.
	 */
	public static void main(String[] args) {
		Board10 board = new ElevensBoard10();
		CardGameGUI10 gui = new CardGameGUI10(board);
		gui.displayGame();
	}
}
