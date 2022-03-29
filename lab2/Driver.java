package lab2;

import java.awt.Color;
import java.util.ArrayList;

import lab2.level.Level;
import lab2.level.Room;
import lab2.level.LevelGUI;


public class Driver {
	Level testLevel = new Level();
	
	public void run() {
		Room r1 = new Room(150, 100, Color.red);
		Room r2 = new Room(150, 150, Color.blue);
		Room r3 = new Room(300, 150, Color.green);
		Room r4 = new Room(50, 100, Color.pink);
		Room r5 = new Room(250, 150, Color.yellow); 
		Room r6 = new Room(250, 150, Color.cyan);
		
		/*Room r1 = new Room(200,100,Color.BLUE);
		Room r2 = new Room(100,60,Color.RED);
		Room r3 = new Room(70,30,Color.GREEN);
		Room r4 = new Room(170,60,Color.yellow);
		Room r5 = new Room(100,100,Color.white);
		Room r6 = new Room(40,200,Color.gray);
		Room r7 = new Room(270,160,Color.pink);
		Room r8 = new Room(120,70,Color.magenta);
		Room r9 = new Room(110,120,Color.orange);*/

		/*testLevel.place(r1,50,40);
		testLevel.place(r2,30,25);
		testLevel.place(r3,210,20);
		testLevel.place(r4,230,100);
		testLevel.place(r5,20,80);
		testLevel.place(r6,90,10);
		testLevel.place(r7,20,20);
		testLevel.place(r8,80,50);
		testLevel.place(r9,130,100);*/

		testLevel.place(r1, 20, 10);
		testLevel.place(r2, 100, 200);
		testLevel.place(r3, 600, 400);
		testLevel.place(r4, 300, 250);
		testLevel.place(r5, 500, 100);
		testLevel.place(r6, 0, 0);
		
		r1.connectNorthTo(r2);
		r1.connectEastTo(r5);
		r2.connectEastTo(r3);
		r2.connectSouthTo(r1);
		r2.connectNorthTo(r4);
		r3.connectWestTo(r2);
		r4.connectSouthTo(r2);
		r5.connectWestTo(r1);
	
		
		testLevel.firstLocation(r1);
		LevelGUI map = new LevelGUI(testLevel, "map");
	}
}
