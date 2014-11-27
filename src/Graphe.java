import java.util.ArrayList;

/*On implémente ici la classe graphe.
 *Un graphe est défini par une liste de sommet et son caractère orienté ou non
 *La classe comporte plusieurs méthodes qui de récupérer des informations sur le graphe*/


public class Graphe {
	
	/*Attributs*/
	
	private ArrayList<Sommet> sommetsGraphe ;
	private Boolean oriente; /*oriente=1 si le graphe est orienté, 0 sinon*/

	/*Méthode*/
		
	public Graphe(Boolean o){
		/*constructeur*/
		sommetsGraphe = new ArrayList<Sommet> () ;
		oriente = o ;
	}
	
	public ArrayList<Sommet> getSommets(){
		/*Permet de récupérer la liste des sommets d'un graphe*/
		return sommetsGraphe;
	}
	
	public Boolean estOriente(){
		/*Permet de vérifier si un graphe est orienté*/
		return oriente;
	}
	
	public void addSommets(Sommet s){
		sommetsGraphe.add(s) ;		
	}
	
	public void removeSommet(Sommet s){
		/*Permet de supprimer un sommet d'un graphe
		 On passe en paramètre l'instance à supprimer*/
		/*On commence par chercher l'index du sommet s à supprimer de la liste*/
		Integer index=sommetsGraphe.indexOf(s);
		/*On supprime ensuite l'élément voulu de la liste des sommets*/
		sommetsGraphe.remove(index);
	}
	
	public void removeSuccesseur(Integer index){
		/*Permet de supprimer un sommet d'un graphe
		 On passe en paramètre l'index du sommet à supprimer*/
		sommetsGraphe.remove(index);
		sommetsGraphe.remove(index);
	}
	
	public String afficheSommetsGraphe(){
		/*Permet de récupérer la liste des sommets d'un graphe donné */
		String liste = new String();
		for (Integer i = 0; i < sommetsGraphe.size(); i++){
			liste = liste.concat(sommetsGraphe.get(i).getNom());
		}
		return liste ;
	}
	
	public ArrayList<Sommet> getPredecesseurs(Sommet s){
		/*Permet de récupérer la liste des prédecesseurs d'un sommet donné d'un graphe
		 *La méthode prend en argument le sommet s du graphe dont on cherche les prédecesseurs*/
		ArrayList<Sommet> listePredecesseurs = new ArrayList<Sommet>() ;
		for (Integer i1 = 0 ; i1 < sommetsGraphe.size(); i1++){
		/*On commence par parcourir chaque sommet du graphe*/
			for (Integer i2 = 0 ; i2 < sommetsGraphe.get(i1).getSuccesseurs().size(); i2++){
				/*On parcourt la liste des successeurs de chaque sommet du graphe.
				 *Si pour un sommet donné, le successeur est s alors on ajoute ce sommet à la liste des prédecesseurs */
				if (sommetsGraphe.get(i1).getSuccesseurs().get(i2).equals(s)) {
					listePredecesseurs.add(sommetsGraphe.get(i1));
				}
			}
		}
		return listePredecesseurs;

	}
		
	public String affichePredecesseurs(Sommet s){
		String p = new String();
		for (Integer i = 0 ; i < this.getPredecesseurs(s).size(); i++){
		p.concat(this.getPredecesseurs(s).get(i).getNom());
		}
		return p;
	}
		
		
}
