import java.util.ArrayList;
import java.util.ListIterator;

/*On implémente ici l'algorithme de Moore Dijkstra
 *Cet algorithme est basé sur une hypothèse restrictive : la fonction de coût est positive.
 *Etant donné un sommet-source s, il permet de trouver un plus court chemin de s vers tout autre sommet.
 *Par défaut l'algorithme retourne le plus court chemin vers le sommet le plus éloigné du sommet-source.
 */

public class Algo1_Moore_Dijkstra {

	//Attributs
	
	/*graphe sur lequel on va appliquer l'algorithme*/
	private Graphe graphe;
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
		this.graphe=g;
		this.s=s;	
		this.t = new ArrayList<Sommet>();
		this.pred = new ArrayList<Sommet>();
		this.pi = new ArrayList<Integer>();

		System.out.println("*L'objet Algo1_Moore_Dijkstra a été construit"+"\n"+"Le sommet-source est : "+s.getNom()+"\n"+"Les sommets du graphe d'intérêt sont : "+ g.afficheSommetsGraphe());
		
		/*L'étape d'initialisation de l'algo est réalisée lors de la construction de l'objet Algo1_Moore_Dijkstra*/
		
		System.out.print("*Etape d'initialisation de l'algorithme : "+"\n");

		/*initialisation de la liste des sommets à traiter, il s'agit de tous les sommets sauf le sommet-source*/
		ListIterator<Sommet> iter1 = this.graphe.getSommets().listIterator();
		while(iter1.hasNext()){
			Sommet varS = iter1.next();
			if (!varS.equals(s)){
				t.add(varS);
			}
		}
		System.out.print("La liste T des sommets à traiter est : "+this.afficherT());
		
		/*initialisation de la liste prédecesseurs : 
		 * On indique un sommet E comme prédecesseur de s1.
		 * Pour tous les autres prédecesseurs, on indique un sommet temporaire x0.
		 * pour chaque successeur direct du sommet-source s, on renseigne s comme meilleur prédecesseur. 
		 */
		Sommet E = new Sommet("E");
		Sommet x0 = new Sommet("x0");
		pred.add(E);
		for (int i=1 ; i<this.graphe.getNbSommets(); i++){
			this.pred.add(x0);
		}
		
		ListIterator<Sommet> iter2 = this.s.getSuccesseurs().listIterator();
		while(iter2.hasNext()){
			int index = g.indexOf(iter2.next());
			this.pred.set(index,s);
		}
		System.out.print("La liste des prédecesseurs A a été initialisée (même ordre que les sommets du graphe) : "+this.afficherPred());
		
		/*initialisation de la liste des coûts (=capacités) : 
		 * On indique 0 comme coût associé au sommet-source s
		 * On indique +infini pour les autres
		 * Pour chaque successeur direct du sommet-source s, on indique comme coût la distance au sommet 
		 */
	
		pi.add(0);
		for (int i=1 ; i<this.graphe.getNbSommets(); i++){
			this.pi.add(Integer.MAX_VALUE);
		}
		
		ListIterator<Sommet> iter3 = this.s.getSuccesseurs().listIterator();
		while(iter3.hasNext()){
			int i1 = iter3.nextIndex();
			int i2 = g.indexOf(iter3.next());
			this.pi.set(i2,this.s.getCapacites().get(i1));
		}
		System.out.print("La liste des couts Pi a été initialisée : "+this.afficherPi());
		System.out.println("Si le meileur prédecesseur est x0, cela signifie que le sommet en question n'a pas encore de meilleur prédecesseur. Le cout infini vaut ici 2147483647."+"\n");

	}
	
	//Méthodes d'affichage
	
	/*Permet d'afficher la liste des sommets à traiter ou des prédecesseurs*/
	public String afficherT(){
		String liste = new String();
		ListIterator<Sommet> iter = this.t.listIterator();
		while(iter.hasNext()){
			String var = iter.next().getNom()+";";
			liste+=var;
		}
		return liste+"\n";
	}
	
	/*Permet d'afficher la liste des meilleurs prédecesseurs*/
	public String afficherPred(){
		String liste = new String();
		ListIterator<Sommet> iter = this.pred.listIterator();
		while(iter.hasNext()){
			String var = iter.next().getNom()+";";
			liste+=var;
		}
		return liste+"\n";
	}
	
	/*Permet d'afficher la liste des couts depuis l'origine associés aux meilleurs prédecesseurs*/
	public String afficherPi(){
		return this.pi.toString()+"\n";
	}
	
	//Implémentation de l'algorithme
	
	public ArrayList<Sommet> algo() {
		
		/*on implémente ici  les itérations de l'algorithme qui devra s'appliquer à un objet Algo1_Moore_Dijkstra préalaablement construit en prenant
		*en argument le graphe d'intérêt g et le sommet-source.
		*L'initialisation de l'algorithme a déjà été réalisée dans le constructeur.
		*/
		
		/*L'algorithme tourne tant qu'il reste des sommets à traiter*/
		int iteration = 0 ; //permet de suivre le nombre d'itérations de l'algorithme

		while (this.t.size()>1){
			
			iteration++;
		/*Affichage des informations utiles*/
			System.out.print("*Itération n° "+iteration+"\n");
			System.out.print("- Sommets à traiter : "+this.afficherT());
			System.out.print("- Meilleurs prédecesseurs : "+this.afficherPred());
			System.out.print("- Sommets du graphe : "+this.graphe.afficheSommetsGraphe());
			System.out.print("- Liste des couts : "+this.afficherPi());
			
		/*On cherche dans la liste des sommets à traiter celui qui minimise Pi*/
			Sommet rechercheSommet = null; //initialisation de la variable qui contiendra le sommet de T qui minimise Pi
			int rechercheIndex = 0; //initialisation de la variable qui contiendra l'index du sommet dans le graphe
			int rechercheCout = Integer.MAX_VALUE ; //initialisation de la variable qui contiendra le cout depuis le sommet-source dans Pi
			ListIterator<Sommet> iter = this.t.listIterator();//itérateur sur la liste T des sommets à traiter

			while (iter.hasNext()){ //on parcourt tous les sommets de T
				Sommet sommetItere = iter.next();//sommet en cours d'étude
				int i = this.graphe.getSommets().indexOf(sommetItere);//index du sommet étudié dans le graphe
				if(this.pi.get(i)<rechercheCout){
					rechercheSommet = sommetItere;//Si le cout depuis le sommet-source dans Pi est minimal, on stocke le sommet T qui minimise Pi
					rechercheIndex = i ;//on sotcke également son index
					rechercheCout = this.pi.get(i);//et son cout
				}
			}
			System.out.print("Le sommet qui minimise Pi est : "+rechercheSommet.getNom()+". Son index dans T est : "+rechercheIndex+". La distance depuis le sommet-source est : "+rechercheCout+"\n"+"\n");

			t.remove(rechercheSommet);//on supprime de la liste T des sommets à traiter le sommet qui minimise le cout depuis le sommet-source dans Pi
			
			for (int i=0 ; i < rechercheSommet.getSuccesseurs().size() ; i++){
				Sommet varS = rechercheSommet.getSucc(i);//on parcourt la liste des sucesseurs du sommet recherché
				int nouvelleDistance = rechercheCout+rechercheSommet.getCapacites(i);//on calcul pour chaque successeur sa distance au sommet-source en passant par rechercheSommet
				
				if (nouvelleDistance<this.pi.get(this.graphe.indexOf(varS))){//si cette nouvelle distance est plus faible, on met à jour la liste des meilleurs prédecesseurs et la liste des couts
					System.out.println("Pour arriver à "+varS.getNom()+" qui est un successeur de "+rechercheSommet.getNom()+", on passe actuellement par le sommet "+this.pred.get(this.graphe.indexOf(varS)).getNom()+" qui est le meilleur prédécesseur");
					System.out.println("Actuellement le cout depuis l'origine dans Pi de "+this.pi.get(this.graphe.indexOf(varS)));
					this.pred.set(this.graphe.indexOf(varS), rechercheSommet);
					this.pi.set(this.graphe.indexOf(varS),nouvelleDistance);
					System.out.println("Il est plus rapide de passer par "+rechercheSommet.getNom()+". Le cout depuis l'origine dans Pi devient "+nouvelleDistance+"\n");
				}		
			}
		}
		
		if(this.t.size()==1){//on traite le cas du dernier sommet
			System.out.println("* Tous les sommets ont été traités sauf "+this.t.get(0).getNom()+" qui est le sommet le plus éloigné du sommet-source "+this.s.getNom()+"."+"\n"+"Le cout du trajet depuis le sommet-source est "+this.pi.get(this.graphe.indexOf(this.t.get(0)))+"\n");
		};

		/*On retourne et affiche le plus court-chemin*/
		
		ArrayList<Sommet> resultat = new ArrayList<Sommet>() ;

		Sommet varS = this.t.get(0);//on recupère dans une variable varS le dernier sommet non-traité
		while (! varS.equals(this.s)){
			/*la liste des sommets constituants le plus court chemin est complété en remontant un par un les meilleurs prédécesseurs. 
			 * La boucle s'arrête lorsqu'on retrouve le sommet-source.
			 */
			resultat.add(0, varS);
			int indexdansgraphe = this.graphe.indexOf(varS);
			Sommet meilleurpredecesseurVarS = this.pred.get(indexdansgraphe);
			varS = meilleurpredecesseurVarS;
		}
		resultat.add(0, this.s);//on ajoute le sommet-source en tête de la liste resultat.
		
		/*On affiche le résultat*/
		String liste = new String();
		ListIterator<Sommet> iter = resultat.listIterator();
		while(iter.hasNext()){
			String var = iter.next().getNom();
			if(iter.hasNext()){
				var+=" -> ";
			}
			liste+=var;
		}
		System.out.println("*Le plus court-chemin déterminé à l'aide de l'algorithme de Moore-Dijkstra est : "+liste);
	
		
		
		return resultat;
	}
}
