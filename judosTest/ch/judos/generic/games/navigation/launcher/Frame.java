package ch.judos.generic.games.navigation.launcher;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * nasty class to implement graphics around the nice main
 * 
 * @created 10.10.2011
 * @author Julian Schelker
 */
public class Frame extends JFrame implements KeyListener {

	public static final int		FPS					= 60;

	private static final long	serialVersionUID	= -5151041547543472432L;
	private Timer				timer;

	public Frame(Main m) {
		super("Walking test");
		Dimension size = new Dimension(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JPanel content = new JPanelX(m);
		content.setPreferredSize(size);
		add(content);

		// setSize(size);
		// setPreferredSize(size);
		// setMinimumSize(size);
		// setUndecorated(true);
		pack();
		setResizable(false);
		setLocationByPlatform(true);
		setVisible(true);
		// validate();
		// createBufferStrategy(2);
		// this.buf = getBufferStrategy();

		TimerTask t = new TimerTask() {
			@Override
			public void run() {
				content.repaint();
			}
		};
		this.timer = new Timer();
		int delay = 1000 / FPS;
		timer.scheduleAtFixedRate(t, delay, delay);

		addKeyListener(this);
		addKeyListener(m);
		this.addMouseListener(m);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE) {
			this.timer.cancel();
			this.dispose();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	private static class JPanelX extends JPanel {

		private static final long	serialVersionUID	= -4579397306400357694L;
		private Main				m;

		public JPanelX(Main m) {
			super(true);
			this.m = m;
		}

		@Override
		public void paint(Graphics g) {
			g.clearRect(0, 0, 800, 600);
			this.m.paint(g);
		}
	}

	// @Override
	// public void paint(Graphics g) {
	// if (this.buf != null) {
	// g = this.buf.getDrawGraphics();
	// g.clearRect(0, 0, 800, 600);
	// this.main.paint(g);
	// this.buf.show();
	// }
	// }

}
