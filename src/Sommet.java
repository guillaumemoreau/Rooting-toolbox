import java.util.ArrayList;
import java.util.ListIterator;
/*
 * On implémente ici la classe Sommet. Ses instances sont utilisés dans la classe Graphe.
 * Un sommet est défini par quatre attributs : un nom, une liste de successeurs listSuccesseurs, une liste de flux ListFlux une liste de capacité listCapacite.
 * La liste des flux listFlux est la liste des flux associés à chaque arc reliant le sommet considéré et ses successeurs définis dans la liste listSuccesseurs
 * La liste des capacités listCapacite est la liste des capacités associés à chaque arc reliant le sommet considéré et ses successeurs définis dans la liste listSuccesseurs
 * Les listes des flux et des capacités sont rangés dans le même ordre que la liste des successeurs
 */

public class Sommet {

	/*Attributs*/
	
	private String nom ;
	private ArrayList<Sommet> listSuccesseurs ;
	private ArrayList<Integer> listFlux ;
	private ArrayList<Integer> listCapacite ;
	
	/*Methodes*/
	
	public Sommet() {
		/*Constructeur*/
		this.nom = new String();
		this.listSuccesseurs = new ArrayList<Sommet>();
		this.listFlux = new ArrayList<Integer>();
		this.listCapacite = new ArrayList<Integer>();
	}
	
	public Sommet(String nom){ 
		/*Constructeur*/
		this.nom = nom;
		this.listSuccesseurs = new ArrayList<Sommet>();
		this.listFlux = new ArrayList<Integer>();
		this.listCapacite = new ArrayList<Integer>();
	}
		
	public String getNom(){
		/*Permet de récupérer le nom d'un sommet*/
		return this.nom; /*faut il utiliser "this.nom" ?*/
	}
		
	public ArrayList<Sommet> getSuccesseurs(){
		/*Permet de récupérer la liste des successeurs d'un sommet donné*/
		return this.listSuccesseurs;
	}
	
	public Sommet getSucc(int i){
		return this.getSuccesseurs().get(i);
	}
		
	public ArrayList<Integer> getFlux(){
		/*Permet de récupérer la liste des capacités des successeurs d'un sommet donné*/
		return this.listFlux;
	}
	
	public int getFlux(int index){
		/*Permet de récupérer la capacité associé à un sommet d'index donné*/
		return this.listFlux.get(index);
	}
	
	public int getFlux(Sommet s){
		/*Permet de récupérer la capacité associée à un sommet donné, on vérifie d'abort que ce sommet est bien un successeur*/
		if(this.verifSuccesseurs(s)){
			int index=this.listSuccesseurs.indexOf(s);
			return this.listFlux.get(index);
		}
		else{
			System.out.println("Le sommet passé en argument n'est pas un successeur, pas de flux associée") ;
			return 0 ;
		}
	}	
	
	public ArrayList<Integer> getCapacites(){
		/*Permet de récupérer la liste des capacités des successeurs d'un sommet donné*/
		return this.listCapacite;
	}
	
	public int getCapacites(int index){
		/*Permet de récupérer la capacité associé à un sommet d'index donné*/
		return this.listCapacite.get(index);
	}
	
	public int getCapacites(Sommet s){
		/*Permet de récupérer la capacité associée à un sommet donné, on vérifie d'abord que ce sommet est bien un successeur*/
		if(this.verifSuccesseurs(s)){
			int index=this.listSuccesseurs.indexOf(s);
			return this.listCapacite.get(index);
		}
		else{
			System.out.println("Le sommet passé en argument n'est pas un successeur, pas de capacité associée") ;
			return 0 ;
		}
	}	
	
	public int getNbSucc(){
		return this.listSuccesseurs.size();
	}
	
	public void addSuccesseur(Sommet s){
		/*Permet d'ajouter un successeur à un sommet donné
		  Par défaut la capacité par défaut associé à l'arc ainsi formé est 1*/
		this.listSuccesseurs.add(s);
		this.listFlux.add(1);
		this.listCapacite.add(1);
	}
	
	public void addSuccesseur(Sommet s, int f){
		/*Permet d'ajouter un successeur à un sommet donné et de définir la valeur du flux associé à l'arc ainsi formé, par défaut la capacité est égale à 1*/
		this.listSuccesseurs.add(s);
		this.listFlux.add(f);
		this.listCapacite.add(1);
	}
	
	public void addSuccesseur(Sommet s, int f, int c){
		/*Permet d'ajouter un successeur à un sommet donné et de définir la valeur de la capacité associé à l'ar ainsi formé, par défaut le flux est maximal et donc égal à la capacité*/
		this.listSuccesseurs.add(s);
		this.listFlux.add(f);
		this.listCapacite.add(c);
	}
	
	public void removeSuccesseur(Sommet s){
		/*Permet de supprimer un sommet de la liste des successeur d'un sommet donné
		 On passe en paramètre l'instance à supprimer*/
		/*On commence par chercher l'index du sommet s à supprimer de la liste*/
		int index= this.listSuccesseurs.indexOf(s);
		/*On supprime ensuite l'élément voulu de la liste des successeurs ainsi que le flux et la capacité associé dans les liste des flux et des capacités*/
		this.listSuccesseurs.remove(index);
		this.listFlux.remove(index);
		this.listCapacite.remove(index);
	}
	
	public void removeSuccesseur(int index){
		/*Permet de supprimer un sommet de la liste des successeur d'un sommet donné
		 On passe en paramètre l'index du sommet à supprimer*/
		this.listSuccesseurs.remove(index);
		this.listFlux.remove(index);
		this.listCapacite.remove(index);
	}
	
	public void setFlux(Sommet s, Integer f){
		/*Permet de passer le flux associée à un sommet donné à une valeur "f", on vérifie d'abord que ce sommet est bien un successeur*/
		if(this.verifSuccesseurs(s)){
			int index=this.listSuccesseurs.indexOf(s);
			this.listFlux.set(index, f);
		}
		else{
			System.out.println("Le sommet passé en argument n'est pas un successeur, pas de capacité associée") ;
		}
		
	}
	
	public void setCapacites(Sommet s, Integer c){
		/*Permet de passer la capacité associée à un sommet donné à une valeur "c", on vérifie d'abord que ce sommet est bien un successeur*/
		if(this.verifSuccesseurs(s)){
			int index=listSuccesseurs.indexOf(s);
			this.listCapacite.set(index, c);
		}
		else{
			System.out.println("Le sommet passé en argument n'est pas un successeur, pas de capacité associée") ;
		}
		
	}
	
	public String afficherSuccesseurs(){
		/*Permet d'afficher la liste des successeurs d'un sommet donné */
		String liste = new String();
		ListIterator<Sommet> iter = this.listSuccesseurs.listIterator();
		while(iter.hasNext()){
			String var = iter.next().getNom()+";";
			liste+=var;
		}
		return liste+"\n";
	}
	
	public String afficherFlux(){
		/*Permet de récupérer la liste des capacités associés aux successeurs d'un sommet donné*/
		String liste = new String();
		ListIterator<Integer> iter = this.listFlux.listIterator();
		while(iter.hasNext()){
			String var = iter.next().toString()+";";
			liste+=var;
		}
		return liste+"\n";

	}

	public String afficherCapacites(){
		/*Permet de récupérer la liste des capacités associés aux successeurs d'un sommet donné*/
		String liste = new String();
		ListIterator<Integer> iter = this.listCapacite.listIterator();
		while(iter.hasNext()){
			String var = iter.next().toString()+";";
			liste+=var;
		}
		return liste+"\n";

	}
	
	public Boolean verifSuccesseurs(Sommet s){
		/*Permet de vérifier que s est un successeur du sommet auquel on applique la méthode*/
		
		Boolean estSommet = false;
		ListIterator<Sommet> iter = this.listSuccesseurs.listIterator();
		
		while (iter.hasNext() && !estSommet){
			if (iter.next().getNom().equals(s.getNom())){
				estSommet = true ;
			}
		}
		return estSommet;
	}	
	
}

