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
		
	public Graphe(){
		/*constructeur*/
		sommetsGraphe = new ArrayList<Sommet> () ;
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
		int index=this.sommetsGraphe.indexOf(s);
		/*On supprime ensuite l'élément voulu de la liste des sommets*/
		this.sommetsGraphe.remove(index);
	}
	
	public void removeSuccesseur(int index){
		/*Permet de supprimer un sommet d'un graphe
		 On passe en paramètre l'index du sommet à supprimer*/
		this.sommetsGraphe.remove(index);
		this.sommetsGraphe.remove(index);
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
		
		ListIterator<Sommet> iterG  = sommetsGraphe.listIterator();
		
		while(iterG.hasNext()){
			/*On commence par parcourir chaque sommet du graphe*/
			Sommet varS = iterG.next();
			System.out.println("Sommet du graphe étudié :"+varS.getNom());
			ListIterator<Sommet> iterVarS  = varS.getSuccesseurs().listIterator();

			while(iterVarS.hasNext()){
				Sommet varS2 = iterVarS.next();
				System.out.println("Sucesseur en cours d'étude de la liste des successeurs du sommet du graphe étudié :"+varS2.getNom());
				System.out.println("La liste des prédecesseurs est acutellement : interface nécessaire !!! ");
				/*On parcourt ensuite la liste des successeurs*/
				if(varS2.verifSuccesseurs(s)){
					listePredecesseurs.add(varS2);
					//System.out.println(varS2.verifSuccesseurs(s));
				}
			}
		}
		//System.out.println(listePredecesseurs.size());
		return listePredecesseurs;

	}
		
	public String affichePredecesseurs(Sommet s){
		String p = new String();
		ListIterator<Sommet> iter = this.getPredecesseurs(s).listIterator();
		
		while(iter.hasNext()){
			System.out.print(iter.next().getNom());
			//p.concat(iter.next().getNom());
		}
		return p;
	}
		
	public String afficherArrayList(ArrayList<Sommet> Liste){
		ListIterator<Sommet> iter = Liste.listIterator();
		String s = new String();
		while(iter.hasNext()){
			s+=iter.next().getNom();
		}
	return(s);
	}	
}
