package ch.judos.generic.games.unitCoordination;

import java.util.ArrayList;

import ch.judos.generic.data.geometry.PointF;
import ch.judos.generic.games.pathsearch.Obstacle;
import ch.judos.generic.games.pathsearch.WayPoint;

/**
 * represents a unit which may at least be movable
 * 
 * @since 27.02.2013
 * @author Julian Schelker
 * @version 1.0 / 27.02.2013
 */
public abstract class Unit extends Obstacle {
	/**
	 * id of event if unit reaches target
	 */
	public static final int					TARGET_REACHED		= 1;
	/**
	 * id of event if unit can't reach its target
	 */
	public static final int					CANT_REACH_TARGET	= 2;
	/**
	 * id of event if unit recalcs its path
	 */
	public static final int					ROUTE_RECALC		= 3;

	/**
	 * listeners interested in the movement of this unit
	 */
	protected ArrayList<MovementListener>	listener;

	/**
	 * creates the unit
	 */
	public Unit() {
		super();
		this.listener = new ArrayList<MovementListener>();
	}

	/**
	 * @return the maximum speed of this unit
	 */
	protected abstract float getSpeed();

	/**
	 * @return the current position of this unit
	 */
	protected abstract PointF getPosition();

	/**
	 * adds a listener to receive information about the movement of this unit
	 * 
	 * @param list
	 */
	public void addMovementListener(MovementListener list) {
		this.listener.add(list);
	}

	/**
	 * removes a listener to no longer receive information about the movement of
	 * this unit
	 * 
	 * @param list
	 */
	public void removeMovementListener(MovementListener list) {
		this.listener.remove(list);
	}

	/**
	 * tell all the listeners that some event has happened to this unit
	 * 
	 * @param event
	 */
	protected void notifyAllListeners(int event) {
		for (MovementListener l : this.listener) {
			switch (event) {
				case 1 :
					l.targetReached(this);
					break;
				case 2 :
					l.cantReachTarget(this);
					break;
				case 3 :
					l.routeChange(this);
					break;
			}
		}
	}

	/**
	 * commands the unit to some specific waypoint
	 * 
	 * @param p
	 */
	public abstract void commandTo(WayPoint p);

	/**
	 * stops the unit and removes all target that were previously set
	 */
	public abstract void stopAndRemoveTarget();

	/**
	 * update the unit, this includes possible movement and state changes of it<br>
	 * this method must be called from some controller, otherwise the movement
	 * is not going to work
	 */
	public abstract void update();
}
