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
		
}
