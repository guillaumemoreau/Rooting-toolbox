import java.util.ArrayList;
import java.util.ListIterator;

/*Cet algorithme est basé sur une hypothèse restrictive : la fonction de coût est positive.
 *Etant donné un sommet-source s, il permet de trouver un plus court chemin de s vers tout autre sommet
 */

public class Algo1_Moore_Dijkstra {

	//Attributs
	
	/*graphe sur lequel on va appliquer l'attribut*/
	private Graphe g;
	/*sommet-source de l'algorithme*/
	private Sommet s;
	/*liste des meilleurs prédecesseurs pour chaque sommet du graphe, le prédecesseur d'indice i est le meilleur prédecesseur (pour venir
	 *du sommet-source) pour le sommet du graphe g ayant pour indice i. C'est l'équivalent de A dans le cours.*/
	private ArrayList<Sommet> pred;
	/*liste des coûts les plus faibles pour venir du sommet-source pour chaque sommet du graphe, le coût d'indice i est le coût minimal (pour venir
	 * du sommet-source) pour le sommet du graphe g ayant pour indice i. C'est l'équivalent de Pi dans le cours.*/
	private ArrayList<Integer> pi;
	/*liste des sommets à traiter. C'est l'équivalent de T dans le cours*/
	private ArrayList<Sommet> t;

	//Constructeur
	
	public Algo1_Moore_Dijkstra(Graphe g , Sommet s){
		this.g=g;
		this.s=s;		
		this.pi = new ArrayList<Integer>(this.g.getNbSommets());
		
		//initialisation des prédecesseurs
		this.pred = new ArrayList<Sommet>(this.g.getNbSommets());
		this.t = new ArrayList<Sommet>();
		
	}
	
	

	
}
