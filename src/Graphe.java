import java.util.ArrayList;
import java.util.ListIterator;

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
		oriente = o;
	}
	
	public Graphe(){
		/*constructeur*/
		sommetsGraphe = new ArrayList<Sommet> () ;
	}
	
	public boolean isOriente(){
		return oriente;
	}

	public ArrayList<Sommet> getSommets(){
		/*Permet de récupérer la liste des sommets d'un graphe*/
		return sommetsGraphe;
	}
	
	public Sommet getSommet(String nomSommet){
		/*Permet de récupérer un sommet d'un graphe selon son nom*/
		Sommet recherche = null;
		ListIterator<Sommet> iter = this.sommetsGraphe.listIterator();
		
		while (iter.hasNext() && recherche == null){
			Sommet varS = iter.next();
			if (varS.getNom().equals(nomSommet)){
				recherche = varS ;
			}
		}
		return recherche;
	}
	
	public Sommet getSommet(int i){
		/*Permet de récupérer un sommet d'indice donné dans le graphe*/
			return this.getSommets().get(i);
		}
	
	public int getNbSommets(){
		/*Permet de récupérer le nombre de sommets du graphe*/
			return this.getSommets().size();

	}
	
	public String[] getListesommets(){
		String[] liste = new String[sommetsGraphe.size()];
		for (int i = 0; i < sommetsGraphe.size(); i++) {
			liste[i] = sommetsGraphe.get(i).getNom();
		}
		return liste;
	}
	
	public void addSommet(Sommet s){
		sommetsGraphe.add(s) ;		
	}
	
	public void removeSommet(Sommet s){
		/*Permet de supprimer un sommet d'un graphe
		 On passe en paramètre l'instance à supprimer*/
		/*On commence par chercher l'index du sommet s à supprimer de la liste*/
		int index=this.sommetsGraphe.indexOf(s);
		/*On supprime ensuite l'élément voulu de la liste des sommets*/
		this.sommetsGraphe.remove(index);
	}
	
	public void removeSommet(int index){
		/*Permet de supprimer un sommet d'un graphe
		 On passe en paramètre l'index du sommet à supprimer*/
		this.sommetsGraphe.remove(index);
		this.sommetsGraphe.remove(index);
	}
	
	public String afficheSommetsGraphe(){
		/*Permet de récupérer la liste des sommets d'un graphe donné */
		String liste = new String();
		ListIterator<Sommet> iter = this.sommetsGraphe.listIterator();
		while(iter.hasNext()){
			String var = iter.next().getNom()+";";
			liste+=var;
		}
		return liste+"\n";
	}
	
	public ArrayList<Sommet> getPredecesseurs(Sommet s){
				
		//La méthode prend en argument le sommet s du graphe dont on cherche les prédecesseurs*/
		ArrayList<Sommet> listePredecesseurs = new ArrayList<Sommet>() ;
		ListIterator<Sommet> iterG  = this.sommetsGraphe.listIterator();
		
		while(iterG.hasNext()){
			/*On commence par parcourir chaque sommet du graphe*/
			Sommet sommetEtudie = iterG.next();
			if (sommetEtudie.verifSuccesseurs(s)){
				listePredecesseurs.add(sommetEtudie);
			}
		}
		return listePredecesseurs;
	}
		
	public String affichePredecesseurs(Sommet s){
		//Permet d'afficher la liste des prédécesseurs récupérée grâce à la méthode getPredecesseurs(Sommet s)
		//On commence par récupérer la liste des prédécesseurs :
		ArrayList<Sommet> listePredecesseurs = this.getPredecesseurs(s);
		String liste = new String();
		ListIterator<Sommet> iter = listePredecesseurs.listIterator();
		
		while(iter.hasNext()){
			String var = iter.next().getNom()+";";
			liste+=var;
		}
		return liste+"\n";
	}

	public int indexOf(Sommet s) {
		return this.getSommets().indexOf(s);
	}
	
}
