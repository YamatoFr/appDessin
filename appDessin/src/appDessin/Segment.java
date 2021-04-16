package appDessin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class Segment extends Figure{
	/**
	 * 
	 */
	// Attributes
	private static final long serialVersionUID = 1L;
	private Point P1;
	private Point P2;
	
	// Constructor
	public Segment(String nom, Color couleur, Point P1, Point P2) {
		super(nom, couleur);
		this.P1 = P1;
		this.P2 = P2;
	}
	
	
	public Segment(Color couleur, Point P1, Point P2) {
		super(P1.nom + P2.nom, couleur);
		this.P1 = P1;
		this.P2 = P2;
	}
	
	public Segment(Segment S) {
		super(S.nom, S.couleur);
		this.P1 = S.getP1();
		this.P2 = S.getP2();
	}

	// Getters/Setters
	public Point getP1() {
		return P1;
	}

	public void setP1(Point p1) {
		this.P1 = p1;
	}

	public Point getP2() {
		return P2;
	}

	public void setP2(Point p2) {
		this.P2 = p2;
	}
	
	// methods
	public String toString() {
		return "Segment: [ " + nom + ", " + couleur + ", " +
				"P1 = ( " + P1.getX() + ", " + P1.getY() + "), " +
				"P2 = ( " + P2.getX() + ", " + P2.getY() + ") ]";
		
	}
	
	public double distance() {
		return P1.distance(P2);
	}

	// Inherited methods
	@Override
	public void translater(int dx, int dy) {
		P1.translater(dx, dy);
		P2.translater(dx, dy);
	}

	@Override
	public Point getCentre() {
		Point Centre = new Point("", Color.black, ( ( P1.getX() + P2.getX() )/2), ( ( P1.getY() + P2.getY() )/2 ) );
		return Centre;
	}
	
	@Override
	public boolean equals(Object S) {
		if (!(S instanceof Segment)) {
			return false;
		}
		Segment s = (Segment)S;
		return (P1.equals(s.P1) && P2.equals(s.P2))
				||
				(P1.equals(s.P2) && P2.equals(s.P1));
	}
	
	@Override
	public Figure clone() {
		return new Segment(this);
	}

	@Override
	public void paint(Graphics gc, Boolean hover) {
		gc.setColor(super.couleur);
		Graphics2D g2 = (Graphics2D)gc;
		P1.paint(g2, false);
		P2.paint(g2, false);
		g2.setStroke(new BasicStroke((hover || select) ? 3 : 1));
		g2.setColor(super.couleur);
		gc.drawLine(P1.getX(), P1.getY(), P2.getX(), P2.getY());
	}

	@Override
	public void export(ObjectOutputStream out) throws IOException {
		out.writeObject(this);
		System.out.println(toString() + " exporté");
	}
}
