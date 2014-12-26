public class Test {

	/**
	 * @param args
	 */
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
		s1.addSuccesseur(s2, 2, 2);
		s1.addSuccesseur(s3, 3, 4);
		s1.addSuccesseur(s4, 4, 4);
		s1.addSuccesseur(s5, 5, 10);
		s3.addSuccesseur(s4, 2, 4);
		s3.addSuccesseur(s5, 4, 6);
		s4.addSuccesseur(s6, 3, 3);
		s4.addSuccesseur(s5, 1, 1);
		s5.addSuccesseur(s6, 2, 7);
		
		/*Définition du graphe*/
		Graphe g = new Graphe(true);
		g.addSommet(s1);
		g.addSommet(s2);
		g.addSommet(s3);
		g.addSommet(s4);
		g.addSommet(s5);
		g.addSommet(s6);
	
		
		/*Tests sur les méthodes de la classe Sommet*/
		
		//System.out.println(s1.getNom());
		//System.out.println(s1.afficherSuccesseurs());
		/*System.out.println(s1.afficherCapacites());
		s1.removeSuccesseur(0);
		System.out.println(s1.afficherCapacites());*/
		/*System.out.println(s1.getCapacites(s5));
		s1.setCapacites(s5, 10);
		//System.out.println(+s1.getCapacites(s5));*/
		//System.out.println(s1.getNbSucc());
		//System.out.println(s3.getNbSucc());
		//System.out.println(s1.getSucc(2).getNom());



		
		/*Tests sur les méthodes de la classe Graphe*/

		//System.out.println(g.affichePredecesseurs(s5));//
		//System.out.println(g.getSommet("s4").getNom());
		//System.out.println(g.getSommet(0).getNom());
		//System.out.println(g.getNbSommets());
		//System.out.println(g.indexOf(s3));
		System.out.println(g.getListesommets());
		
		/*Tests sur les méthodes de la classe FormatGraphe*/
		FormatGraphe b = new FormatGraphe(g);
		b.ecrireGraphe("test");
		






		
	}
}
		
