package appDessin;

public class Segment extends Figure{
	// Attributes
	private Point P1;
	private Point P2;
	private double Longueur;
	
	// Constructor
	public Segment(String nom, String couleur, int p1x, int p1y, int p2x, int p2y, double longueur) {
		super(nom, couleur);
		this.P1 = new Point("", "", p1x, p2y);
		this.P2 = new Point("", "", p2x, p2y);
		this.setLongueur(Math.sqrt( (Math.pow( ( P1.getX() - P2.getX() ), 2) ) + (Math.pow( ( P1.getY() + P2.getY() ), 2) ) ));
	}
	
	
	// Getters/Setters
	/**
	 * @return longueur
	 */
	public double getLongueur() {
		return Longueur;
	}

	/**
	 * @param longueur the Longueur to set
	 */
	public void setLongueur(double longueur) {
		Longueur = longueur;
	}
	
	// methods
	public String toString() {
		return "Segment: [ " + nom + ", " + couleur + ", " +
				"P1 = ( " + P1.getX() + ", " + P1.getY() + "), " +
				"P2 = ( " + P2.getX() + ", " + P2.getY() + ") ]";
		
	}
	
	public boolean equals(Object S) {
		Segment s = (Segment)S;
		return (P1.equals(s.P1) && P2.equals(s.P2))
				||
				(P1.equals(s.P2) && P2.equals(s.P1));
	}
	
	public Segment clone() {
		return new Segment(nom, couleur, P1, P2, Longueur);
	}

	// Inherited methods
	@Override
	public void translater(int dx, int dy) {
		P1.translater(dx, dy);
		P2.translater(dx, dy);
	}

	@Override
	public Point getCentre() {
		Point Centre = new Point("", "", ( ( P1.getX() + P2.getX() )/2), ( ( P1.getY() + P2.getY() )/2 ) );
		return Centre;
	}
	

}
