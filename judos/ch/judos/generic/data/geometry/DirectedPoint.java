package ch.judos.generic.data.geometry;

import ch.judos.generic.data.rstorage.interfaces.RStorable2;

/**
 * a point in 2d[int] space that hints into a direction
 * 
 * @since 30.01.2015
 * @author Julian Schelker
 */
public class DirectedPoint implements RStorable2 {
	protected PointI	pos;
	protected Angle	angle;

	public DirectedPoint(int x, int y, Angle angle) {
		this.pos = new PointI(x, y);
		this.angle = angle;
	}

	public DirectedPoint(PointI point, Angle angle) {
		this.pos = point;
		this.angle = angle;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return this.pos.x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return this.pos.y;
	}

	/**
	 * @return the angle in RADIAN
	 */
	@Deprecated
	public double getAngle() {
		return angle.getRadian();
	}

	public Angle getAAngle() {
		return angle;
	}

	public PointI getPoint() {
		return this.pos;
	}

	public PointF getPointF() {
		return new PointF(this.pos);
	}

	@Override
	public String toString() {
		return this.pos + " " + this.angle;
	}

}
