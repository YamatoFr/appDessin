/**
 * 
 */
package appDessin;

/**
 * @author Theo FIGINI
 *
 */
public class Cercle extends Figure{
	// attributes
	private Point Centre;
	private int Rayon;

	// constructors
	public Cercle(String nom, String couleur, Point c, int r) {
		super(nom, couleur);
		this.Centre = c;
		this.Rayon = r;
	}
	
	public Cercle(Point P1, Point P2) {
		super("", "");
		Centre = P1;
		Rayon = (int) P1.distance(P2);
	}
	
	// methods
	public String toString() {
		return "Cercle: ["+ nom + ", " + couleur + ", ("+
				Centre.getX() + "," + Centre.getY() + ")," +
				Rayon + "]";
	}
	
	public boolean equals(Object c2) {
		Cercle c = (Cercle)c2;
		return Centre.equals(c.Centre) && Rayon == c.Rayon;
	}

	// Inherited methods
	@Override
	public void translater(int dx, int dy) {
		Centre.translater(dx, dy);
	}

	@Override
	public Point getCentre() {
		return this.Centre;
	}
	
}
