package lab2.level;

import java.awt.Color;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class LevelGUI implements Observer {

	private Level lv;
	private Display d;
	Room nu;
	public void addObserver(Observer level) {
	}
	
	public LevelGUI(Level level, String name) {
		this.lv = level;
        level.addObserver(this); 
		JFrame frame = new JFrame(name);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		d = new Display(lv,1400,700,lv.level);
		
		frame.getContentPane().add(d);
		frame.pack();
		frame.setLocation(0,0);
		frame.setVisible(true);
		
	}
	
	
	public void update(Observable arg0, Object arg1) {
		d.repaint();
	}
	
	private class Display extends JPanel {
		ArrayList<Room> lt = new ArrayList<Room>();
		
		public Display(Level fp, int x, int y, ArrayList<Room> level) {
			lt = level;
			
			addKeyListener(new Listener());
			
			setBackground(Color.BLACK);
			setPreferredSize(new Dimension(x+20,y+20));
			setFocusable(true);
		}
		

	
		
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for(int i = 0; i < lt.size(); i++) {
				Room tp = lt.get(i);
				g.setColor(tp.c);
				g.fillRect(tp.cx , tp.cy, tp.lx, tp.ly);
				
				if(tp.fl == true) {
					nu = tp;
					g.setColor(Color.WHITE);
					g.fillRect(tp.cx+tp.lx/4, tp.cy+tp.ly/4, tp.lx/2, tp.ly/2);
				}
				if(tp.nW != null) {
					g.setColor(tp.nW.c);
					g.fillRect(tp.cx+tp.lx/2-((tp.lx/2)/2), tp.cy, tp.lx/2, tp.ly/15);
				}
				if(tp.eW != null) {
					g.setColor(tp.eW.c);
					g.fillRect(tp.cx+tp.lx, tp.cy+tp.ly/2-((tp.ly/2)/2), -tp.lx/15, tp.ly/2);
				}
				if(tp.sW != null) {
					g.setColor(tp.sW.c);
					g.fillRect(tp.cx+tp.lx/2-((tp.lx/2)/2), tp.cy+tp.ly, tp.lx/2, -tp.ly/15);
				}
				if(tp.wW != null) {
					g.setColor(tp.wW.c);
					g.fillRect(tp.cx, tp.cy+tp.ly/2-((tp.ly/2)/2), tp.lx/15, tp.ly/2);
				}
			}
		}
		

	 	private class Listener implements KeyListener {

	 		public void keyPressed(KeyEvent arg0) {
	 		}

	 		public void keyReleased(KeyEvent e) {
	 		    switch (e.getKeyCode()) {
	 		        case KeyEvent.VK_W:
	 		        	nu = lv.moveN(nu);
	 		            break;
	 		        case KeyEvent.VK_D:
	 		        	nu = lv.moveE(nu);
	 		            break;
	 		        case KeyEvent.VK_S:
	 		        	nu = lv.moveS(nu);
	 		            break;
	 		        case KeyEvent.VK_A:
	 		        	nu = lv.moveW(nu);
	 		        	break;
	 		        default:
	 		    }
	 		}

	 		public void keyTyped(KeyEvent event) {
	 		}
	 	}

	}
	
}