package tests.ping;

import java.awt.event.WindowEvent;

public class WindowListener implements java.awt.event.WindowListener {
	
	private CheckInternet c;
	
	public WindowListener(CheckInternet checkInternet) {
		this.c = checkInternet;
	}
	
	@Override
	public void windowActivated(WindowEvent e) {
		
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
		this.c.close();
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		this.c.close();
	}
	
	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}
	
	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}
	
	@Override
	public void windowIconified(WindowEvent e) {
		
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		
	}
	
}
