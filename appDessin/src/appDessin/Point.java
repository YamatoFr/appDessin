package appDessin;

import java.awt.Color;
import java.awt.Graphics;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class Point extends Figure {
	
	private static final long serialVersionUID = 1L;
	// Attributes
	private int X;
	private int Y;
	
	// Constructor
	public Point(String nom, Color couleur, int x, int y) {
		super(nom, couleur);
		this.X = x;
		this.Y = y;
	}
	
	public Point(Color couleur, int x, int y) {
		super("Point", couleur);
		this.X = x;
		this.Y = y;
	}
	
	public Point(Point P) {
		super(P.nom, P.couleur);
		this.X = P.getX();
		this.Y = P.getY();
	}
	
	// Getters/Setters
	public int getX() {
		return X;
	}
	
	public void setX(int x) {
		this.X = x;
	}
	
	public int getY() {
		return Y;
	}
	
	public void setY(int y) {
		this.Y = y;
	}
	
	// Methods
	
	public void afficher() {
		super.afficher();
	}
	
	public double distance(Point p) {
		return (int) Math.sqrt(Math.pow(p.getX() - getX(), 2) + Math.pow(p.getY() - getY(), 2));
	}
	
	
	// Inherited methods
	@Override
	public String toString() {
		return super.toString() + " [X="+ X +", Y="+ Y +"]";
	}
	
	@Override
	public void translater(int dx, int dy) {
		X += dx;
		Y += dy;
	}
	
	@Override
	public Point getCentre() {
		return this;
	}
	
	@Override
	public boolean equals(Object P) {
		if (!(P instanceof Point)) {
			return false;
		}
		Point p1 = (Point)P;
		return X==p1.X && Y==p1.Y;
	}
	
	@Override
	public Figure clone() {
		return new Point(this);
	}

	@Override
	public void paint(Graphics gc, Boolean hover) {
		int taille = (hover || select) ? 9 : 6;
		gc.setColor(super.couleur);
		gc.fillOval(this.X-3, this.Y-3, taille, taille);
	}

	@Override
	public void export(ObjectOutputStream out) throws IOException {
		out.writeObject(this);
		System.out.println(toString() + " exporté");
	}
}