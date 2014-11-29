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
		s1.addSuccesseur(s2, 2);
		s1.addSuccesseur(s3, 3);
		s1.addSuccesseur(s4, 4);
		s1.addSuccesseur(s5, 5);
		s3.addSuccesseur(s4, 2);
		s3.addSuccesseur(s5, 4);
		s4.addSuccesseur(s6, 3);
		s4.addSuccesseur(s5, 1);
		s5.addSuccesseur(s6, 2);
		
		/*Définition du graphe*/
		Graphe g = new Graphe();
		g.addSommets(s1);
		g.addSommets(s2);
		g.addSommets(s3);
		g.addSommets(s4);
		g.addSommets(s5);


		

		
	
		
		/*Tests sur les méthodes de la classe Sommet*/
		
		/*System.out.println("La commande s1.getNom() donne : "+s1.getNom());
		System.out.println("La commande s1.afficherSuccesseurs() donne : "+s1.afficherSuccesseurs());
		System.out.println("La commande s1.afficherCapacites() donne : "+s1.afficherCapacites());
		//s1.removeSuccesseur(0);
		System.out.println("La commande s1.afficherSuccesseurs() donne : "+s1.afficherSuccesseurs());
		System.out.println("La commande s1.afficherCapacites() donne : "+s1.afficherCapacites());
		System.out.println();	
		System.out.println("capacite s1-s5 = "+s1.getCapacites(s5));
		System.out.println("changement de la capacite s1-s5");
		//s1.setCapacites(s5, 10);
		System.out.println("capacite s1-s5 = "+s1.getCapacites(s5));
		System.out.println();*/	
		//System.out.print(g.afficherArrayList(s1.getSuccesseurs()));
		System.out.println(/*"La commande s5.getPredecesseurs() donne :"*/g.affichePredecesseurs(s5));
		//System.out.println("La commande s5.afficherSuccesseurs() donne : "+s3.afficherSuccesseurs());

		
	}
}
		
