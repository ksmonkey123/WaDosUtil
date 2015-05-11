package ch.judos.generic.games.navigation.launcher;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import ch.judos.generic.data.geometry.PointF;
import ch.judos.generic.games.navigation.model.FlyUnit;
import ch.judos.generic.games.navigation.model.Map;
import ch.judos.generic.games.navigation.model.SimpleUnit;
import ch.judos.generic.games.navigation.model.SpaceUnit;
import ch.judos.generic.games.navigation.model.Unit;
import ch.judos.generic.games.pathsearch.SimpleWayPoint;
import ch.judos.generic.games.pathsearch.WayPoint;
import ch.judos.generic.games.unitCoordination.MovementListener;

/**
 * @created 10.10.2011
 * @author Julian Schelker
 * @version 1.0
 * @dependsOn
 */
public class Main extends KeyAdapter implements MouseListener, MovementListener {
	public static void main(String[] args) {
		new Main();
	}

	private Map						map;
	private int						selected;
	private HashMap<Unit, Integer>	unitmode;
	private ArrayList<Unit>			units;

	public Main() {
		this.map = new Map(15, 10);
		this.units = new ArrayList<Unit>();
		this.unitmode = new HashMap<Unit, Integer>();
		SimpleUnit u = new SimpleUnit(this.map, 0, 0);
		this.units.add(u);
		u.commandTo(new SimpleWayPoint(0, 0));
		u.addMovementListener(this);
		this.unitmode.put(u, 1);
		SpaceUnit u2 = new SpaceUnit(this.map, 7, 9);
		u2.commandTo(new SimpleWayPoint(7, 0));
		this.units.add(u2);
		u2.addMovementListener(this);
		this.unitmode.put(u2, 1);
		FlyUnit u3 = new FlyUnit(this.map, 4, 0);
		u3.commandTo(new SimpleWayPoint(4, 0));
		this.units.add(u3);
		u3.addMovementListener(this);
		this.unitmode.put(u3, 1);
		this.selected = 0;
		new Frame(this);
	}

	@Override
	public void cantReachTarget(Object u) {
		nextTarget((Unit) u);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_1) {
			this.selected = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_2) {
			this.selected = 1;
		}
		if (e.getKeyCode() == KeyEvent.VK_3) {
			this.selected = 2;
		}
		if (e.getKeyCode() == KeyEvent.VK_4) {
			this.selected = 3;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Unit u = this.units.get(this.selected);
			u.pauseOrContinue();
		}
		if (e.getKeyCode() == KeyEvent.VK_R) {
			Unit u = this.units.get(this.selected);
			this.unitmode.put(u, 1);
			Random r = new Random();
			u.commandTo(new SimpleWayPoint(r.nextInt(this.map.getWidth()), r
				.nextInt(this.map.getHeight())));
		}
		if (e.getKeyCode() == KeyEvent.VK_M) {
			Unit u = this.units.get(this.selected);
			this.unitmode.put(u, 2);
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			Unit u = this.units.get(this.selected);
			u.stopAndRemoveTarget();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		Unit u = this.units.get(this.selected);
		WayPoint f = this.map.getFieldFromPoint(new PointF(arg0.getX() - 2,
			arg0.getY() - 30));
		if (this.map.isInsideMap(f)) {
			u.commandTo(f);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	private void nextTarget(Unit u) {
		Random r = new Random();
		int mode = this.unitmode.get(u);
		if (mode == 1) {
			WayPoint t = new SimpleWayPoint(r.nextInt(this.map.getWidth()), r
				.nextInt(this.map.getHeight()));
			u.commandTo(t);
		}
	}

	// gets called from frame
	/**
	 * @param g
	 */
	public void paint(Graphics g) {
		this.map.paint(g);
		for (Unit u : this.units) {
			u.paint(g);
		}
		g.setColor(Color.black);
		g.drawString("Use 1,2 to select units", 50, 550);
		g.drawString("Use R to set random walking mode", 50, 565);
		g.drawString("Use M to set manual walking mode", 50, 580);
		g.drawString("Use S to stop unit", 325, 565);
	}

	@Override
	public void routeChange(Object u) {
	}

	@Override
	public void targetReached(Object u) {
		nextTarget((Unit) u);
	}
}
