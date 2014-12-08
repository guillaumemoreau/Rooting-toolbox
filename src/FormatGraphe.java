import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *  <P>Cette permet de transformer le format de description d'un graphe.
 *  <BR>Elle accepte comme format d'entr�e :
 *  <ul>
 *  	<li>Un objet de la classe Graphe</li>
 *  	<li>Un fichier au format Graphviz</li>
 *  	<li>Une matrice d'adjacence enti�re</li>
 *  </ul>
 *  <BR>Elle permet de r�cup�rer le graphe sous diff�rents formats:
 *  <ul>
 *  	<li>Matrice d'adjacence</li>
 *  	<li>Tableaux d'arcs</li>
 *  	<li>Tableaux de successeurs</li>
 *  	<li>Un fichier Graphviz</li>
 *  	<li>Un objet de la classe Graphe</li>
 * </ul>
 * <BR>Dans cette classe le graphe est enregistr� dans la classe Graphe qui contient
 *   la liste des Sommets.
 * <P>A propos du format Grapviz, la plupart des fichiers au format graphviz
 *  peuvent �tre lus.Toutefois, certains fichiers peuvent ne pas fonctionner,
 *  s'ils emploient des �l�ments syntaxiques non reconnus par ce programme.
 * <P>Par exemple, la syntaxe :
 * <ul><li>Sommet -- Sommet -- Sommet -- ...</li>
 * <li>ou Sommet -> Sommet -> Sommet -> ...</li></ul>
 * <BR>n'est pas reconnue.
 * <P>Une valuation des arcs est lues � l'aide du champ d'option des Sommets
 * "label". La valeur doit �tre dans l'une des 2 formes suivantes :
 *  <ul>
 *  	<li>"flux"</li>
 *  	<li>"flux[capacit�]"</li>
 *  </ul>
 * les valeurs devant �tre enti�res.
 * Si aucune valuation n'est pr�cis�e la valeu par d�faut du flux est de 1,
 * celle de la capacit� est de
 * </P>
 *
 * @author Louis-Marie
 */
public class FormatGraphe {
	/**
	 * Indique si le graphe est orient� (true) ou non (false).
	 */
	protected boolean oriente; // Orient� (true) ou non-orient� (false)
	/**
	 * D�crit le graphe � l'aide d'objets Java.
	 */
	public Graphe graphe;
	
//**********************************************************************
	
	/**
	 * Construit un objet FormatGraphe � partir d'un objet Graphe
	 *
	 * @param G		Le graphe.
	 */
	public FormatGraphe(Graphe G){
		graphe = G;
		oriente = G.isOriente();
	}
	
	/**
	 * Construit un nouvel objet FormatGraphe � l'aide de la matrice
	 *  d'adjacence.
	 *
	 * @param mat	Matrice d'ajacence
	 */
	public FormatGraphe(int[][] mat){
		this.oriente = !(estSymetrique(mat));
		Graphe graphe = new Graphe(oriente);
		int nbSommets = mat.length;
		for (int i = 0; i < nbSommets; i++) {
			Sommet courant = new Sommet(new Integer(i).toString());
			graphe.addSommet(courant);
			for (int j = 0; j < nbSommets; j++){
				Sommet succCourant = new Sommet(new Integer(j).toString());
				courant.addSuccesseur(succCourant, mat[i][j]);
			}
		}
	}
	
	/**
	 * Construit un nouvel objet FormatGraphe � l'aide d'un fichier au
	 *  format Graphviz (fichier texte).
	 *
	 * @param fichier	nom du fichier
	 * @throws IOException	En cas d'erreur dans la lecteure du fichier.
	 */
	public FormatGraphe(File fichier) throws IOException{
		// Initialisation de la lecture
		FileReader read = new FileReader(fichier);
		BufferedReader in = new BufferedReader(read);
		
		// Recherche d'une ligne commencant par 'g' ou 'd'
		String ligne;
		char c;
		do{
			ligne = in.readLine();
			c = ligne.charAt(0);
			if(ligne == null){
				throw new IOException("Format de fichier erron�.\n" +
						"Pas de balise graph ou digraph");
			}
		}while(c!='d' && c!='g');
		
		// Determination du type de graphe : orient� ou non-orient�
		StringTokenizer st = new StringTokenizer(ligne);
		String champ = st.nextToken();
		if(champ.equals("graph")){
			oriente = false;
		}
		else if(champ.equals("digraph")){
			oriente = true;
		}
		else{
			throw new IOException("Format de fichier erron�.\n" +
					"Pas de balise graph ou digraph");
		}
		
		graphe = new Graphe(oriente);
		
		//Lecture des successeurs des sommets
		if(!oriente){
			// 1er cas : graphe non oriente
			ligne = in.readLine();
			while(!ligne.equals("}")){
				String options = null;
				String nomSommet = null;
				int flux = 1;
				int capacite = -1;
				String delimiteurs;
				
				//Traitement de la chaine d'options
				int indexOpt = ligne.indexOf('[');
				if(indexOpt!=-1) {
					options = ligne.substring(indexOpt);
					ligne = ligne.substring(0,indexOpt);
					boolean finit = false;
					while(!finit){
						int index = options.indexOf('l');
						options = options.substring(index+1);
						if(options.startsWith("abel")){
							delimiteurs = "\"";
							st = new StringTokenizer(options, delimiteurs);
							st.nextToken();
							options = st.nextToken();
							if(options.contains("[")){
								delimiteurs = "[]";
								st = new StringTokenizer(options, delimiteurs);
								try{
									flux = new Integer(st.nextToken());
								}catch(Exception e){flux = 1;}
								capacite = new Integer(st.nextToken());
							}
							else{
								try{
									flux = new Integer(options);
								}catch(Exception e){flux = 1;}
							}
							finit = true;
						}
						else if(index == -1){
							finit = true;
						}
					}
				}
				
				// Traitement des successeurs
				delimiteurs = " \t{;}=";
				st = new StringTokenizer(ligne, delimiteurs);
				if(st.hasMoreTokens()){
					nomSommet= st.nextToken();
					if(st.hasMoreTokens()){
						String operande = st.nextToken();
						if(operande.equals("--")){
							Sommet courant;
							if(graphe.getSommet(nomSommet)!=null){
								courant = graphe.getSommet(nomSommet);
							}
							else{
								courant = new Sommet(nomSommet);
								graphe.addSommet(courant);
							}
							while(st.hasMoreTokens()){
								Sommet succ;
								String nomSucc = st.nextToken();
								if(graphe.getSommet(nomSucc)!=null){
									succ = graphe.getSommet(nomSucc);
								}
								else{
									succ = new Sommet(nomSucc);
									graphe.addSommet(succ);
								}
								courant.addSuccesseur(succ,flux,capacite);
								succ.addSuccesseur(courant,flux,capacite);
							}
						}
						//Si l'operande est inconnu ne rien faire
					}
					else if(!nomSommet.equals("node")){
						Sommet courant;
						if(graphe.getSommet(nomSommet)==null){
							courant = new Sommet(nomSommet);
							graphe.addSommet(courant);
						}
					}
				}
				ligne = in.readLine();
			}
		}
		else{
			// 2eme cas : graphe oriente
			ligne = in.readLine();
			while(!ligne.equals("}")){
				String options = null;
				String nomSommet = null;
				int flux = 1;
				int capacite = -1;
				String delimiteurs;
				
				//Traitement de la chaine d'options
				int indexOpt = ligne.indexOf('[');
				if(indexOpt!=-1) {
					options = ligne.substring(indexOpt);
					ligne = ligne.substring(0,indexOpt);
					boolean finit = false;
					while(!finit){
						int index = options.indexOf('l');
						options = options.substring(index+1);
						if(options.startsWith("abel")){
							delimiteurs = "\"";
							st = new StringTokenizer(options, delimiteurs);
							st.nextToken();
							options = st.nextToken();
							if(options.contains("[")){
								delimiteurs = "[]";
								st = new StringTokenizer(options, delimiteurs);
								try{
									flux = new Integer(st.nextToken());
								}catch(Exception e){flux = 1;}
								capacite = new Integer(st.nextToken());
							}
							else{
								try{
									flux = new Integer(options);
								}catch(Exception e){flux = 1;}
							}
							finit = true;
						}
						else if(index == -1){
							finit = true;
						}
					}
				}
				
				// Traitement des successeurs
				delimiteurs = " \t{;}=";
				st = new StringTokenizer(ligne, delimiteurs);
				if(st.hasMoreTokens()){
					nomSommet= st.nextToken();
				
					if(st.hasMoreTokens()){
						String operande = st.nextToken();
						if(operande.equals("->")){
							Sommet courant;
							if(graphe.getSommet(nomSommet)!=null){
								courant = graphe.getSommet(nomSommet);
							}
							else{
								courant = new Sommet(nomSommet);
								graphe.addSommet(courant);
							}
							while(st.hasMoreTokens()){
								Sommet succ;
								String nomSucc = st.nextToken();
								if(graphe.getSommet(nomSucc)!=null){
									succ = graphe.getSommet(nomSucc);
								}
								else{
									succ = new Sommet(nomSucc);
									graphe.addSommet(succ);
								}
								courant.addSucc(succ,flux,capacite);
							}
						}
						//Si l'operande est inconnu ne rien faire
					}
					else if(!nomSommet.equals("node")){
						Sommet courant;
						if(graphe.getSommet(nomSommet)==null){
							courant = new Sommet(nomSommet);
							graphe.addSommet(courant);
						}
					}
				}
				ligne = in.readLine();
			}
		}
		in.close();
		read.close();
	}
	
	/**
	 * Fournit la matrice d'adjacence du graphe.
	 *
	 * @return	Matrice d'adjacence
	 */
	public int[][] getMatrice(){
		int nbSommets = graphe.getNbSommets();
		int[][] matriceAdjacence = new int[nbSommets][nbSommets];
		for (int i = 0; i < nbSommets; i++) {
			Sommet courant = graphe.getSommet(i);
			for (int j = 0; j < courant.getNbSucc(); j++) {
				Sommet succ = courant.getSucc(j);
				int indexSucc = graphe.indexOf(succ);
				matriceAdjacence[i][indexSucc] = courant.getFlux(j);
			}
		}
		return matriceAdjacence;
	}
	
	/**
	 * Fournit un tableau contenant les noms des sommets du graphe.
	 *
	 * @return	Liste des sommets
	 */
	public String[] getListesommets(){
		return graphe.getListesommets();
	}
	// A VOIR !!!!!!!!!!!
	
	/**
	 * Fournit une repr�sentation du graphe sous la forme d'un tableau arc.
	 * <P>Il y a en fait 2 tableaux repr�sent�s par des vecteurs de
	 *  chaines de caract�res.
	 * <P>Le premier contient les Sommets de d�part des arcs.<br>
	 * Le second contient les Sommets d'arriv� des arcs.
	 * <P>Les vecteurs doivent �tre pass�s vides en param�tres.
	 *
	 * @param depart	Liste des Sommets d�part d'arcs	
	 * @param arrivee	Liste des Sommets arriv�e des arcs.
	 */
	public void getTableauxArcs(Vector<String> depart, Vector<String> arrivee){
		for (int i = 0; i < graphe.getNbSommets(); i++) {
			Sommet courant = graphe.getSommet(i);
			for (int j = 0; j < courant.getNbSucc(); j++) {
				depart.add(courant.getNom());
				arrivee.add(courant.getSucc(j).getNom());
			}
		}
	}
	
	/**
	 *  Fournit une repr�sentation du graphe sous la forme d'un tableau
	 *  des successeurs.
	 * <P>Le vecteur pass� en param�tre contient la liste des successeurs
	 * de chaque Sommet. Il doit �tre vide au d�part.
	 * <P>Le tableau renvoy� contient le nombre de successeurs de chaque Sommet.
	 *
	 * @param succ	Liste des successeurs de chaque Sommet.
	 * @return	Nombre de successeurs de chaque Sommet.
	 */
	public int[] getTableauxSucc(Vector<String> succ){
		int nbSommets = graphe.getNbSommets();
		int[] nbSucc = new int[nbSommets];
		for (int i = 0; i < nbSommets; i++) {
			Sommet courant = graphe.getSommet(i);
			nbSucc[i] = courant.getNbSucc();
			for (int j = 0; j < courant.getNbSucc(); j++) {
				succ.add(courant.getSucc(j).getNom());
			}
		}
		return nbSucc;
	}
	
	/**
	 * Teste si une matrice d'entier est sym�trique.
	 *
	 * @param mat	Matrice � tester
	 * @return	Booleen indiquant le r�sultat.
	 */
	public boolean estSymetrique(int[][] mat){
		boolean sym = true;
		for (int i = 0; i < mat.length; i++) {
			for (int j = i+1; j < mat.length; j++) {
				if(mat[i][j] != mat[j][i]){
					sym = false;
				}
			}
		}
		return sym;
	}
	
	/**
	 * Transpose une matrice d'entier
	 *
	 * @param mat	Matrice � transposer
	 * @return	Transpos�e de la matrice
	 */
	public int[][] transpose(int[][] mat){
		int [][]trans = new int[mat.length][mat.length];
		for (int i = 0; i < mat.length; i++) {
			for (int j = i+1; j < mat.length; j++) {
				trans[i][j] = mat[j][i];
			}
		}
		return trans;
	}
	
	/**
	 * Ecrit le graphe dans un fichier texte au format Graphviz
	 *
	 * @param nomFichier
	 */
	public void ecrireGraphe(String nomFichier){
		File fichier = new File(nomFichier);
		try{
			FileWriter out = new FileWriter(fichier);
			if(oriente){
				out.write("digraph G {\n");
				for (int i = 0; i < graphe.getNbSommets(); i++) {
					Sommet courant = graphe.getSommet(i);
					if(courant.getNbSucc()!=0){
						for (int j = 0; j < courant.getNbSucc(); j++) {
							out.write("\t"+courant.getNom());
							out.write(" -> ");
							out.write(courant.getSucc(j).getNom());
							int flux = courant.getFlux(j);
							int capacite = courant.getCapacites(j);
							if(flux!= 1 || capacite!=-1){
								out.write(" [label = \""+flux);
								if(capacite!=-1){
									out.write("["+capacite+"]");
								}
								out.write("\"]");
							}
							out.write(";\n");
						}
					}
					else{
						out.write("\t"+courant.getNom());
						out.write(";\n");
					}
				}
				out.write("}");
			}
			else{
				int[][] matrice = getMatrice();
				int nbSommets = matrice.length;
				out.write("graph G {\n");
				
				for (int i = 0; i < nbSommets; i++) {
					Sommet courant = graphe.getSommet(i);
					if(courant.getNbSucc()!=0){
						for (int j = i; j < nbSommets; j++) {
							if(matrice[i][j]!=0){
								out.write("\t"+courant.getNom());
								out.write(" -- ");
								out.write(graphe.getSommet(j).getNom());
								int flux = matrice[i][j];
								if(flux!= 1){
									out.write(" [label = \""+flux);
									out.write("\"]");
								}
								out.write(";\n");
							}
						}
					}
					else{
						out.write("\t"+courant.getNom());
						out.write(";\n");
					}
				}
				out.write("}");
			}
			out.close();
		}catch(Exception e){
			System.err.println("Erreur d'ecriture");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		File fichier = new File("graph.dot");
		try{
			FormatGraphe b = new FormatGraphe(fichier);
			int[][] mat = b.getMatrice();
			for (int i = 0; i < b.graphe.getNbSommets(); i++) {
				for (int j = 0; j < b.graphe.getNbSommets(); j++) {
					System.out.print(mat[i][j]+" ");
				}
				System.out.println();
			}
			for (int i = 0; i < b.graphe.getNbSommets(); i++) {
				System.out.println(b.graphe.getSommet(i).getNom());
			}
			b.ecrireGraphe("graph.dot");
		}catch(IOException e){
			System.err.println("erreur");
			System.err.println(e.getMessage());
		}
	}

}
