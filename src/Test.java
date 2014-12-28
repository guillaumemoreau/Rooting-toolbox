public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*Instanciation des sommets*/
		Sommet s1 = new Sommet("s1");
		Sommet s2 = new Sommet("s2");		
		Sommet s3 = new Sommet("s3");
		Sommet s4 = new Sommet("s4");
		Sommet s5 = new Sommet("s5");
		Sommet s6 = new Sommet("s6");

		/*Définition des successeurs et capacités*/
		s1.addSuccesseur(s2, 3, 3);
		s1.addSuccesseur(s3, 6, 6);
		s1.addSuccesseur(s5, 3, 3);
		s2.addSuccesseur(s3, 3, 3);
		s2.addSuccesseur(s4, 6, 6);
		s3.addSuccesseur(s4, 1, 1);
		s4.addSuccesseur(s5, 6, 6);
		s4.addSuccesseur(s6, 1, 1);
		s5.addSuccesseur(s3, 2, 2);
		s5.addSuccesseur(s6, 5, 5);
		
		/*Définition du graphe*/
		Graphe g = new Graphe(true);
		g.addSommet(s1);
		g.addSommet(s2);
		g.addSommet(s3);
		g.addSommet(s4);
		g.addSommet(s5);
		g.addSommet(s6);

		/*Utilisation de la classe FormatGraphe pour convertir le graphe g en format dot lisible par GraphViz*/
		FormatGraphe b = new FormatGraphe(g);
		b.ecrireGraphe("test.dot");
		
		/*On applique l'algorithme de Moore-Dijkstra au graphe g*/
		Algo1_Moore_Dijkstra a = new Algo1_Moore_Dijkstra(g,s1);
		a.algo();
		
	}
}
		
