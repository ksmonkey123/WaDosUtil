package ch.judos.generic.data.geometry;

import ch.judos.generic.data.rstorage.interfaces.RStorable2;

/**
 * @since 09.02.2015
 * @author Julian Schelker
 */
/**
 * @since 08.03.2015
 * @author Julian Schelker
 */
public class Angle implements RStorable2 {

	public static final Angle	A_0						= Angle.fromDegree(0);
	public static final Angle	A_90						= Angle.fromDegree(90);
	public static final Angle	A_180						= Angle.fromDegree(180);
	public static final Angle	A_270						= Angle.fromDegree(270);
	public static final Angle	A_360						= Angle.fromDegreeUncapped(360);

	public static final double	degreeToRadianFactor	= 2 * Math.PI / 360;
	public static final double	radianToDegreeFactor	= 360 / (2 * Math.PI);

	/**
	 * uses the opposite and hypotenuse to calculate the angle between adjacent
	 * and hypotenuse of the triangle
	 * 
	 * @param opposite
	 * @param hypotenuse
	 * @return
	 */
	public static Angle fromTriangleOH(double opposite, double hypotenuse) {
		return fromRadian(Math.asin(opposite / hypotenuse));
	}

	public static Angle fromRadian(double radian) {
		if (Double.isInfinite(radian) || Double.isNaN(radian))
			return null;
		Angle a = new Angle(radian);
		a.norm();
		return a;
	}

	public static Angle fromDegree(double degree) {
		return fromRadian(degree * degreeToRadianFactor);
	}

	public static Angle fromDegreeUncapped(double degree) {
		return fromRadianUncapped(degree * degreeToRadianFactor);
	}

	public static Angle fromRadianUncapped(double radian) {
		Angle a = new Angle(radian);
		return a;
	}

	/**
	 * used for RStorage
	 */
	@SuppressWarnings("unused")
	private Angle() {
		this.radian = 0;
	}

	protected double	radian;

	protected Angle(double radian) {
		this.radian = radian;
	}

	public Angle sub(Angle angle) {
		return Angle.fromRadian(this.radian - angle.radian);
	}

	public Angle add(Angle angle) {
		return Angle.fromRadian(this.radian + angle.radian);
	}

	public Angle div(double divisor) {
		return Angle.fromRadian(this.radian / divisor);
	}

	public Angle mul(double factor) {
		return Angle.fromRadian(this.radian * factor);
	}

	protected void norm() {
		this.radian = this.radian % (2 * Math.PI);
		// in case the original value was negative
		if (this.radian < 0)
			this.radian += (2 * Math.PI);
	}

	/**
	 * @return value in [0, 2*PI]
	 */
	public double getRadian() {
		return this.radian;
	}

	/**
	 * @return value in [0,360]
	 */
	public double getDegree() {
		return this.radian * radianToDegreeFactor;
	}

	/**
	 * @return the direction vector of this angle with the length of 1
	 */
	public PointF getDirection() {
		return new PointF(Math.cos(this.radian), Math.sin(this.radian));
	}

	public Angle turnCounterClockwise(double angleInRadian) {
		this.radian -= angleInRadian;
		norm();
		return this;
	}

	public Angle turnClockwise(double angleInRadian) {
		this.radian += angleInRadian;
		norm();
		return this;
	}

	public Angle turnClockwise(Angle angle) {
		this.radian += angle.getRadian();
		norm();
		return this;
	}

	public Angle setIfHigherTo(double angle) {
		if (this.radian > angle)
			this.radian = angle;
		return this;
	}

	@Override
	public String toString() {
		return Math.round(getDegree()) + "°";
	}

	/**
	 * if the smaller angle that is passed, is actually bigger, then they are
	 * exchanged
	 * 
	 * @param smaller
	 * @param bigger
	 * @return true if this angle lies between the smaller and the bigger angle
	 */
	public boolean inInterval(Angle smaller, Angle bigger) {
		if (smaller.getRadian() > bigger.getRadian()) {
			Angle t = smaller;
			smaller = bigger;
			bigger = t;
		}
		return getRadian() >= smaller.getRadian() && getRadian() <= bigger.getRadian();
	}

	/**
	 * @param startAngle
	 * @param endAngle
	 * @return true if this angle lies in the clockwise segment starting at one
	 *         and ending at the second angle passed as parameter
	 */
	public boolean inIntervalUncapped(Angle startAngle, Angle endAngle) {
		if (startAngle.radian < endAngle.radian)
			return startAngle.radian <= this.radian && this.radian <= endAngle.radian;
		else
			return this.radian >= startAngle.radian || this.radian <= endAngle.radian;
	}

	public double getCos() {
		return Math.cos(this.getRadian());
	}

	public double getSin() {
		return Math.sin(this.getRadian());
	}

	public boolean almostEquals(Angle angle, Angle delta) {
		return inIntervalUncapped(angle.sub(delta), angle.add(delta));
	}

}
