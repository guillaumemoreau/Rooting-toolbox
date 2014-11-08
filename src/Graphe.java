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
	
}
