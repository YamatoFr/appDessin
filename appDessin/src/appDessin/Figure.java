package appDessin;

import java.awt.Color;
import java.awt.Graphics;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class Figure implements Cloneable, Serializable{

	private static final long serialVersionUID = 1L;
	// Attributes
    protected String nom;
    protected Color couleur;
    // Ne pas changer cette ligne !!!
    protected Boolean select = false;

    // Constructor
    public Figure(String n, Color c) {
        nom = n;
        couleur = c;
    }

    // Getters/Setter
    public Boolean getSelect() {
		return select;
	}

	public void setSelect(Boolean select) {
		this.select = select;
	}

	// Methods
    public String toString() {
    	return nom + " " + couleur;
    }
    
    public void afficher() {
        System.out.println(toString());
    }
    
    // Abstract Methods
    public abstract void translater(int dx, int dy);
    
    public abstract Point getCentre();
    
    public abstract boolean equals(Object F);
    
    public abstract Figure clone();
    
    public abstract void paint(Graphics gc, Boolean hover);
    
    public abstract void export(ObjectOutputStream out) throws IOException;
}