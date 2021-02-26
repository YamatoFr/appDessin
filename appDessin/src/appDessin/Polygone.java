package appDessin;
import java.util.LinkedList;

public class Polygone extends Figure{
	// attributes
	LinkedList<Point> liste;

	public Polygone(String n, String c, LinkedList<Point> l) {
		super(n, c);
		liste = l;
	}
	
	public Polygone(String n, String c, Point[] tab) {
		super(n, c);
		liste = new LinkedList<Point>();
		for(int i = 0; i < tab.length; i++) {
			liste.add(tab[i]);
		}
	}
	
	public Polygone(String n, String c) {
		super(n, c);
		liste = new LinkedList<Point>();
	}
	
	
	// Methods
	public String toString() {
		String tmp = "";
		for(int i = 0; i < liste.size(); i++) {
			tmp += liste.get(i).toString() + " - ";
		}
		tmp += "\n";
		return tmp;
	}
	
	@Override
	public void translater(int dx, int dy) {
		for(Point p : liste) {
			p.translater(dx, dy);
		}
		
	}
	@Override
	public Point getCentre() {
		// TODO Auto-generated method stub
		return null;
	}

}
