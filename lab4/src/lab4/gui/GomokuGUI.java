package lab4.gui;
import lab4.client.*;
import lab4.gui.*;
import lab4.data.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import lab4.client.GomokuClient;
import lab4.data.GomokuGameState;

import javax.swing.*;

/*
 * The GUI class
 */

public class GomokuGUI implements Observer{

	private GomokuClient client;
	private GomokuGameState gamestate;
	private GamePanel gameGridPanel;
	private JButton connectButton;
	private JButton newGameButton;
	private JButton disconnectButton;
	private JLabel messageLabel;
	
	/**
	 * The constructor
	 * 
	 * @param g   The game state that the GUI will visualize
	 * @param c   The client that is responsible for the communication
	 */
	public GomokuGUI(GomokuGameState g, GomokuClient c){
		this.client = c;
		this.gamestate = g;
		client.addObserver(this);
		gamestate.addObserver(this);
		SpringLayout layout = new SpringLayout();
		
		//Creates JFrame
		JFrame frame = new JFrame("Gomoku");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(140, 140);
		
		//Setup JPanel
		gameGridPanel = new GamePanel(gamestate.getGameGrid());
		Container cont = frame.getContentPane();
		cont.setLayout(layout);
        Dimension d = new Dimension(gameGridPanel.getWidth() + 100, gameGridPanel.getHeight() + 100);
		cont.setPreferredSize(d);
		cont.setBackground(Color.LIGHT_GRAY);
		
		//Create components
		connectButton = new JButton("Connect");
		newGameButton = new JButton("New Game");
		disconnectButton = new JButton("Disconnect");
		messageLabel = new JLabel(gamestate.getMessageString());
		
		//Add components to the created panel
		cont.add(gameGridPanel);
		cont.add(connectButton);
		cont.add(newGameButton);
		cont.add(disconnectButton);
		cont.add(messageLabel);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, gameGridPanel, 0, SpringLayout.HORIZONTAL_CENTER, cont);
		layout.putConstraint(SpringLayout.NORTH, gameGridPanel, 10, SpringLayout.NORTH, cont);
		
		//Sets location of conncectButton to 20 px under GridPanel
		layout.putConstraint(SpringLayout.NORTH, connectButton, 20, SpringLayout.SOUTH, gameGridPanel);
		//Sets location of connect Button 15 px away from new game button
		layout.putConstraint(SpringLayout.EAST, connectButton, -15, SpringLayout.WEST, newGameButton);
		layout.putConstraint(SpringLayout.WEST, connectButton, 0, SpringLayout.WEST, gameGridPanel);
		
		//Sets location of disconnectButton 20px under GridPanel
		layout.putConstraint(SpringLayout.NORTH, disconnectButton, 20, SpringLayout.SOUTH, gameGridPanel);
		//Sets location of disconnectButton 15px away from newGameButton
		layout.putConstraint(SpringLayout.WEST, disconnectButton, 15, SpringLayout.EAST, newGameButton);
		layout.putConstraint(SpringLayout.EAST, disconnectButton, 0, SpringLayout.EAST, gameGridPanel);
		
		//Sets location of newgame center to Gridpanel
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, newGameButton, 0, SpringLayout.HORIZONTAL_CENTER, gameGridPanel);
		//Sets location of newgame 20px under gameGridPanel
		layout.putConstraint(SpringLayout.NORTH, newGameButton, 20, SpringLayout.SOUTH, gameGridPanel);
		
		//Sets messageLabel to be under grid
		layout.putConstraint(SpringLayout.NORTH, messageLabel, 0, SpringLayout.SOUTH, gameGridPanel);
		//Sets messageLabel to be centered 
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, messageLabel, 0, SpringLayout.HORIZONTAL_CENTER, gameGridPanel);
		
		frame.setContentPane(cont);
		frame.pack();
		frame.setVisible(true);
		
        gameGridPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //Calls the method getGridPosition, and sends in the x/y cordinate of the panel board that is printed.
                int[] square = gameGridPanel.getGridPosition(e.getX(),e.getY());
                gamestate.move(square[1],square[0]);

            }
        });

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConnectionWindow connectionWindow = new ConnectionWindow(client);
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamestate.newGame();
            }
        });

        disconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamestate.disconnect();
            }
        });

    }
	
	
	public void update(Observable arg0, Object arg1) {
		
		// Update the buttons if the connection status has changed
		if(arg0 == client){
			if(client.getConnectionStatus() == GomokuClient.UNCONNECTED){
				connectButton.setEnabled(true);
				newGameButton.setEnabled(false);
				disconnectButton.setEnabled(false);
			}else{
				connectButton.setEnabled(false);
				newGameButton.setEnabled(true);
				disconnectButton.setEnabled(true);
			}
		}
		
		// Update the status text if the gamestate has changed
		if(arg0 == gamestate){
			messageLabel.setText(gamestate.getMessageString());
		}
		
	}
	
}