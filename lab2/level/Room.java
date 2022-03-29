package lab2.level;

import java.awt.Color;
import java.util.ArrayList;


public class Room {
	Room nW = null, eW = null, sW = null, wW = null;
	int lx, ly, cx, cy;
	Color c;
	boolean fl = false, g = false;
	
	public Room(int dx, int dy, Color color) {
		lx = dx;
		ly = dy;
		int cx , cy;
		c = color;
		System.out.println(lx + " " +  ly + " " +  color);
	}


	public void connectNorthTo(Room r) {
		if(r.g) { 
			nW = r;
			System.out.println(this + " Den norra väggen " + nW);
		}
	}
	public void connectEastTo(Room r) {
		if(r.g) { 
			eW = r;
			System.out.println(this + "Den östra väggen " + eW);
		}
	}
	public void connectSouthTo(Room r) {
		if(r.g) { 
			sW = r;
			System.out.println(this + "Den södra väggen " + sW);			
		}
	}
	public void connectWestTo(Room r) {
		if(r.g) { 
			wW = r;
			System.out.println(this + "Den västra väggen " + wW);			
		}
	}
}