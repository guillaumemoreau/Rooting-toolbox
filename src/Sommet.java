import java.util.ArrayList;
/*
 * On implémente ici la classe Sommet. Ses instances sont utilisés dans la classe Graphe.
 * Un sommet est défini par trois attributs : un nom, une liste de successeurs listSuccesseurs et une liste de capacité listCapacite.
 * La liste des capacités listCapacite est la liste des capacités associés à chaque arc reliant le sommet considéré et ses successeurs définis dans la liste listSuccesseurs
 * La liste des capacités est rangés dans le même ordre que la liste des successeurs
 */

public class Sommet {

	/*Attributs*/
	
	private String nom ;
	private ArrayList<Sommet> listSuccesseurs ;
	private ArrayList<Integer> listCapacite ;
	
	/*Methodes*/
	
	public Sommet(String nom){ 
		/*Constructeur*/
		this.nom = nom;
		listSuccesseurs = new ArrayList<Sommet>();
		listCapacite = new ArrayList<Integer>();
	}
		
	public void addSuccesseur(Sommet s){
		/*Permet d'ajouter un successeur à un sommet donné
		  Par défaut la capacité par défaut associé à l'arc ainsi formé est 1*/
		listSuccesseurs.add(s);
		listCapacite.add(1);
	}
	
	public void addSuccesseur(Sommet s, Integer c){
		/*Permet d'ajouter un successeur à un sommet donné et de définir la valeur de la capacité associé à l'ar ainsi formé*/
		listSuccesseurs.add(s);
		listCapacite.add(c);
	}
	
	public void removeSuccesseur(Sommet s){
		/*Permet de supprimer un sommet de la liste des successeur d'un sommet donné
		 On passe en paramètre l'instance à supprimer*/
		/*On commence par chercher l'index du sommet s à supprimer de la liste*/
		Integer index=listSuccesseurs.indexOf(s);
		/*On supprime ensuite l'élément voulu de la liste des successeurs et sa capacité associé dans la liste des capacités*/
		listSuccesseurs.remove(index);
		listCapacite.remove(index);
	}
	
	public void removeSuccesseur(Integer index){
		/*Permet de supprimer un sommet de la liste des successeur d'un sommet donné
		 On passe en paramètre l'index du sommet à supprimer*/
		listSuccesseurs.remove(index);
		listCapacite.remove(index);
	}
	
	public String getNom(){
		/*Permet de récupérer le nom d'un sommet*/
		return nom; /*faut il utiliser "this.nom" ?*/
	}
		
	public ArrayList<Sommet> getSuccesseurs(){
		/*Permet de récupérer la liste des successeurs d'un sommet donné*/
		return listSuccesseurs;
	}
	
	public ArrayList<Integer> getCapacites(){
		/*Permet de récupérer la liste des successeurs d'un sommet donné*/
		return listCapacite;
	}
	
	public Integer getCapacites(Integer index){
		/*Permet de récupérer la capacité associé à un sommet d'index donné*/
		return listCapacite.get(index);
	}
	
	public Boolean verifSuccesseurs(String nom){
		/*Permet de vérifier l'existence d'un successeur donné*/
		Boolean bool = false ;
			for (Integer i1 = 0 ; i1 < listSuccesseurs.size() && bool == false; i1++){
				if (listSuccesseurs.get(i1).nom == nom) {
				/*question : faudrait-il utiliser un getNom() même si on est bien dans la classe Sommet dont "nom" est un attribut ? */
				/*faut-il utiliser equals() plutôt que == ?*/
				bool = true ;
				}
			}
		return bool ;
	}	
	
	public Integer getCapacites(Sommet s){
		/*Permet de récupérer la capacité associée à un sommet donné, on vérifie d'abort que ce sommet est bien un successeur*/
		if(this.verifSuccesseurs(s.nom)){
			Integer index=listSuccesseurs.indexOf(s);
			return listCapacite.get(index);
		}
		else{
			System.out.println("Le sommet passé en argument n'est pas un successeur, pas de capacité associée") ;
			return 0 ;
		}
	}	
	
	public void setCapacites(Sommet s, Integer c){
		/*Permet de passer la capacité associée à un sommet donné à une valeur "c", on vérifie d'abort que ce sommet est bien un successeur*/
		if(this.verifSuccesseurs(s.nom)){
			Integer index=listSuccesseurs.indexOf(s);
			listCapacite.set(index, c);
		}
		else{
			System.out.println("Le sommet passé en argument n'est pas un successeur, pas de capacité associée") ;
		}
		
	}
	
}
