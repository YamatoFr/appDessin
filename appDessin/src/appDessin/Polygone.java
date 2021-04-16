package appDessin;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.io.IOException;
import java.io.ObjectOutputStream;

import java.util.LinkedList;

public class Polygone extends Figure{
	// attributes
	private static final long serialVersionUID = 1L;
	private LinkedList<Point> liste;

	// Constructor
	public Polygone(String nom, Color couleur, LinkedList<Point> l) {
		super(nom, couleur);
		this.liste = l;
	}
	
	public Polygone(String nom, Color couleur, Point[] tab) {
		super(nom, couleur);
		liste = new LinkedList<Point>();
		
		for(int i = 0; i < tab.length; i++) {
			this.liste.add(tab[i]);
		}
	}
	
	public Polygone(Color couleur, LinkedList<Point> points) {
		super("Polygone", couleur);
		this.liste = points;
	}
	
	public Polygone(Polygone Po) {
		super(Po.nom, Po.couleur);
		this.liste = Po.liste;
	}
	
	// Getters/Setters
	public LinkedList<Point> getListe() {
		return liste;
	}

	public void setListe(LinkedList<Point> liste) {
		this.liste = liste;
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
	
	// Inherited methods
	@Override
	public void translater(int dx, int dy) {
		for(Point p : liste) {
			p.translater(dx, dy);
		}
		
	}
	
	@Override
	public boolean equals(Object Po) {
		if (!(Po instanceof Polygone)) {
			return false;
		}
        Polygone po2 = (Polygone)Po;
		int s1 = liste.size();
		int s2 = po2.getListe().size();

		if (s1 != s2)
			return false;

		for (int i = 0; i < liste.size(); i++)
			if (!liste.get(i).equals(po2.getListe().get(i)))
				return false;

		return true;
	}
	
	public boolean equals2(Object Po) {
		Polygone p2 = (Polygone) Po;
		
		if(p2.liste.size() != liste.size()) {
			return false;
		}
		
		int i = 0;
		int j = 0;
		j = p2.liste.indexOf(liste.get(i));
		if(j == -1) {
			return false;
		}
		
		int nbCorrespondance = 1;
		j = j+1;
		for(i = 0; i < liste.size(); i++, j++) {
			if(liste.get(i).equals(p2.liste.get(j%liste.size()))) {
				nbCorrespondance++;
			}
		}
		if(nbCorrespondance == liste.size()) {
			return true;
		}
		
		nbCorrespondance = 1;
		j = p2.liste.indexOf(liste.get(i));
		for(i = 1;i < liste.size(); i++, j--) {
			if(liste.get(i).equals(p2.liste.get(j%liste.size()))) {
				nbCorrespondance ++;
			}
		}
		if(nbCorrespondance == liste.size()) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public Polygone clone() {
		return new Polygone(this);
	}

	@Override
	public Point getCentre() {
		int x = 0;
		int y = 0;
		for(Point p : liste) {
			x += p.getX();
			y += p.getY();
		}
		x = x/liste.size();
		y = y/liste.size();
		
		return new Point(nom, couleur, x, y);
	}

	@Override
	public void paint(Graphics gc, Boolean hover) {
		gc.setColor(couleur);
		Graphics2D g2 = (Graphics2D)gc;
		g2.setStroke(new BasicStroke((hover || select) ? 3 : 1));
		int x[]= new int[50], y[]= new int[50], i = 0;

		for (Point point : liste) {
			point.paint(g2, false);
			x[i] = point.getX();
			y[i] = point.getY();
			i++;
		}
		
		gc.drawPolygon(x, y, liste.size());
	}

	@Override
	public void export(ObjectOutputStream out) throws IOException {
		out.writeObject(this);
		System.out.println(toString() + " exporté");
	}

}
