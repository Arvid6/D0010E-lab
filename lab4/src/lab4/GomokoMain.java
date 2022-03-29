 package lab4;
 
import lab4.data.GameGrid;
import lab4.client.GomokuClient;
import lab4.data.GomokuGameState;
import lab4.gui.GomokuGUI;

public class GomokoMain {
	static int port;
	public static void main(String[] args) {
		if(args.length == 0) {
			port = 4998;
		} 
		else {
			port = Integer.parseInt(args[0]);
		}
		
		GomokuClient client = new GomokuClient(port);
		GomokuGameState state = new GomokuGameState(client);
		GomokuGUI gui = new GomokuGUI(state, client);
		
		GomokuClient client2 = new GomokuClient(5000);
		GomokuGameState state2 = new GomokuGameState(client2);
		GomokuGUI gui2 = new GomokuGUI(state2, client2);
	}

}
