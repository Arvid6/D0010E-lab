package lab2.level;

import java.util.Observable;
import java.util.ArrayList;


public class Level extends Observable {
	ArrayList<Room> level = new ArrayList<Room>();
	boolean f = true;
	
	
	public boolean place(Room r, int cx, int cy)  {
		r.cx = cx;
		r.cy = cy;
		int lx = r.lx, ly = r.ly;
		if(f) {
			if(cx < 0 || cy < 0) {
				System.out.println("rum " + r + " var ej godkänt");
				return false;
			}
			for(int i = 0; i < level.size(); i++) {
				int tcx = level.get(i).cx, tlx = level.get(i).lx, tcy = level.get(i).cy, tly = level.get(i).ly;
				//Sarre algorithmen
				if((cx+lx >= tcx && cx <= (tlx + tcx)) && (cy + ly >= tcy && cy <= (tly + tcy))) {
					System.out.println("rum " + r + " var ej godkänt");
					return false;
					}
				}
			level.add(r);
			r.g = true;
			System.out.println("rum " + r + " var godkänt");
			return true;
		}
		else {
			return false;
		}
	}
	
	public void update(){
		setChanged();
		notifyObservers();
	}
	
	public void firstLocation(Room r) {
		f = false; 
		r.fl = true;
	}
	
	public Room moveN(Room r){
		if(r.nW != null) {
			r.fl = false;
			r.nW.fl = true;
			update();
			return r.nW;
		}
		else {
			return r;
		}
	}
	public Room moveE(Room r){
		if(r.eW != null) {
			r.fl = false;
			r.eW.fl = true;
			update();
			return r.eW;
		}
		else {
			return r;
		}
	}
	public Room moveS(Room r){
		if(r.sW != null) {
			r.fl = false;
			r.sW.fl = true;
			update();
			return r.sW;
		}
		else {
			return r;
		}
	}
	public Room moveW(Room r){
		if(r.wW != null) {
			r.fl = false;
			r.wW.fl = true;
			update();
			return r.wW;
		}
		else {
			return r;
		}
	}
	
	
}