package appDessin;

/**
 * @author Theo FIGINI
 *
 */
public class Point extends Figure {
	// Attributes
	private int X;
	private int Y;
	
	// Constructor
	public Point(String nom, String couleur, int x, int y) {
		super(nom, couleur);
		this.X = x;
		this.Y = y;
	}
	
	public Point(int x, int y) {
		this("", "", x, y);
	}
	
	public Point() {
		this("", "", 0, 0);
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
	
	/** Afficher le point */
	public void afficher() {
		super.afficher();
	}
	
	/** Distance par rapport a un autre point */
	public double distance(Point p) {
		double resultat = Math.sqrt(Math.pow(p.getX() - getX(), 2) + Math.pow(p.getY() - getY(), 2));
		return resultat;
	}

	public boolean equals(Object P) {
		Point p1 = (Point)P;
		return X==p1.X && Y==p1.Y;
	}
	
	public Point clone() {
		return new Point(nom, couleur, X, Y);
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

}