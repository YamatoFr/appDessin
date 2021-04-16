
package appDessin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class Cercle extends Figure{
	// attributes
	private static final long serialVersionUID = 1L;
	private Point Centre;
	private int Rayon;

	// constructors
	public Cercle(String nom, Color couleur, Point c, int r) {
		super(nom, couleur);
		this.Centre = c;
		this.Rayon = r;
	}
	
	public Cercle(Color couleur, Point P1, Point P2) {
		super("Cercle", couleur);
		this.Centre = P1;
		this.Rayon = (int) Centre.distance(P2) * 2;
	}
	
	public Cercle(Cercle C) {
		super(C.nom, C.couleur);
		this.Centre = C.getCentre();
		this.Rayon = C.Rayon;
	}
	
	// Getters/Setters
	@Override
	public Point getCentre() {
		return this.Centre;
	}
	
	public void setCentre(Point centre) {
		this.Centre = centre;
	}
	
	public int getRayon() {
		return Rayon;
	}
	
	public void setRayon(int rayon) {
		this.Rayon = rayon;
	}
	
	// methods
	public String toString() {
		return "Cercle: ["+ nom + ", " + couleur + ", ("+
				Centre.getX() + "," + Centre.getY() + ")," +
				Rayon + "]";
	}
	
	// Inherited methods
	@Override
	public void translater(int dx, int dy) {
		Centre.translater(dx, dy);
	}
	
	@Override
	public boolean equals(Object C) {
		if (!(C instanceof Cercle)) {
			return false;
		}
		Cercle c = (Cercle) C;
		return Centre.equals(c.Centre) && Rayon == c.Rayon;
	}
	
	@Override
	public Cercle clone() {
		return new Cercle(this);
	}

	@Override
	public void paint(Graphics gc, Boolean hover) {
		Graphics2D g2 = (Graphics2D)gc;
		g2.setStroke(new BasicStroke((hover || select) ? 3 : 1));
		gc.setColor(super.couleur);
		Centre.paint(g2, false);
		gc.drawOval(Centre.getX() - Rayon/2, Centre.getY() - Rayon/2, Rayon, Rayon);
	}

	@Override
	public void export(ObjectOutputStream out) throws IOException {
		out.writeObject(this);
		System.out.println(toString() + " exporté");
	}
	
}
