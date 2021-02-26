package appDessin;

public abstract class Figure implements Cloneable{
    // Attributes
    protected String nom;
    protected String couleur;

    // Constructor
    public Figure(String n, String c) {
        nom = n;
        couleur = c;
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
    
    //public abstract boolean equals(Figure P);
}