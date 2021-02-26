package appDessin;

/**
 * @author Theo FIGINI
 *
 */
public class Test {

  /** Afficher le nom puis le point p
    * @param nom	le nom a afficher
    * @param p	le point a afficher
    */
  public static void afficher(String nom, Point p)
  {
	System.out.print(nom + " = ");
	p.afficher();
	System.out.println();
  }
  /** Methode principale */
  public static void main(String args[])
  {
	testPoint();
	}

  public static void testPoint() {


	// Creer et afficher un point p1
	Point p1 = new Point("A", "rouge", 3, 4);
	afficher("p1", p1);

	// Creer et afficher un point p2
	Point p2 = new Point("B", "bleu", 0, 0);	// On declare des poignees et on cree
					// des objets quand on veut.
	afficher("p2", p2);

	// Afficher la distance entre p1 et p2
	double d = p1.distance(p2);
	System.out.println("Distance de p1 a p2 : " + d);

	// Translater p1
	System.out.println("> p1.translater(6, -2);");
	p1.translater(6, -2);

	// Afficher la distance entre p1 et p2
	System.out.println("Distance de p1 a p2 : " + p1.distance(p2));

	// Changer l'abscisse de p1 et afficher p1
	System.out.println("> p1.setX(0);");
	p1.setX(0);
	afficher("p1", p1);

	// Changer l'ordonnee de p1 et afficher p1
	System.out.println("> p1.setY(10);");
	p1.setY(10);
	afficher("p1", p1);
						// Decrire l'etat de la memoire
	// Prendre une nouvelle poignee sur p1
	System.out.println("> Point p3 = p1;");
	Point p3 = p1;

	// Deplacer p3
	System.out.println("> p3.translater(100, 100);");
	p3.translater(100, 100);

	// Afficher p1
	afficher("p1", p1);			// Qu'est ce qui est affiche ?

						// Decrire l'etat de la memoire
	// Affectations entre poignees
	System.out.println("> p1 = new Point(0, 0);");
	p1 = new Point(null, null, 0, 0);
	System.out.println("> p1 = p2 = p3;");
	p1 = p2 = p3;
						// Decrire l'etat de la memoire
	afficher("p1", p1);
	afficher("p2", p2);
	afficher("p3", p3);
						// Combien a-t-on de points ?
  }
}
