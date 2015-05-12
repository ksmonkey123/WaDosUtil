package ch.judos.generic.data.geometry;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Point2D;

/**
 * @created 24.04.2012
 * @author Julian Schelker
 */
public class RotatedRect {
	
	private int h;
	private int w;
	private double y;
	private double x;
	private double theta;
	private double r;
	private double alpha;
	private double innerR;
	
	public RotatedRect(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.theta = 0;
		init();
	}
	
	public void moveAbsolute(double x, double y) {
		this.x += x;
		this.y += y;
	}
	
	public void moveRelative(double x, double y) {
		this.x += y * Math.cos(this.theta) + x * Math.sin(this.theta + Math.PI);
		this.y += y * Math.sin(this.theta) - x * Math.cos(this.theta + Math.PI);
	}
	
	private void init() {
		this.r = Math.hypot(this.w / 2, this.h / 2);
		this.alpha = Math.atan2(this.h / 2, this.w / 2);
		this.innerR = Math.min(this.w / 2, this.h / 2);
	}
	
	private double[] getPointsAngles() {
		double pi = Math.PI;
		return new double[] { this.theta + this.alpha, this.theta - this.alpha,
			this.theta + pi + this.alpha, this.theta + pi - this.alpha };
	}
	
	private int[] getXPoints() {
		int[] x = new int[4];
		double[] a = getPointsAngles();
		for (int i = 0; i < 4; i++)
			x[i] = (int) (this.x + this.r * Math.cos(a[i]));
		return x;
	}
	
	private int[] getYPoints() {
		int[] y = new int[4];
		double[] a = getPointsAngles();
		for (int i = 0; i < 4; i++)
			y[i] = (int) (this.y + this.r * Math.sin(a[i]));
		return y;
	}
	
	private Point2D getPoint(double theta) {
		return new Point2D.Double(this.x + this.r * Math.cos(theta), this.y
			+ this.r * Math.sin(theta));
	}
	
	private Polygon getPoly() {
		return new Polygon(getXPoints(), getYPoints(), 4);
	}
	
	/**
	 * takes between 500ns and 2qs
	 * 
	 * @param r
	 * @return whether this intersects with r
	 */
	public boolean intersects(RotatedRect r) {
		double d = distanceTo(r);
		if (d >= this.r + r.r)
			return false;
		if (d <= this.innerR + r.innerR)
			return true;
		if (this.containsPointsOf(r))
			return true;
		if (r.containsPointsOf(this))
			return true;
		return false;
	}
	
	public boolean containsPointsOf(RotatedRect r) {
		Polygon p = this.getPoly();
		double[] angles = r.getPointsAngles();
		for (int i = 0; i < 4; i++) {
			Point2D vertex = r.getPoint(angles[i]);
			if (p.contains(vertex))
				return true;
		}
		return false;
	}
	
	public double distanceTo(RotatedRect r) {
		return Math.hypot(r.x - this.x, r.y - this.y);
	}
	
	public void draw(Graphics2D g) {
		g.drawPolygon(getPoly());
	}
	
	public void fill(Graphics2D g) {
		g.fillPolygon(getPoly());
	}
	
	public void rotate(double d) {
		this.theta += d;
	}
	
	public void setPosition(Point location) {
		if (location == null)
			return;
		this.x = location.x;
		this.y = location.y;
	}
	
}
