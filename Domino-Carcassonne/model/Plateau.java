package model;

public abstract class Plateau {
    public final int hauteur=11;
    public final int largeur=11;
    public Case[][] cases;
    public Plateau(){
        this.cases=new Case[hauteur][largeur];
        for(int i=0; i<hauteur; i++){
            for(int j=0; j<largeur; j++){
                this.cases[i][j]=new Case();
            }
        }
    }
    public Case getCase(int i, int j){
        return this.cases[i][j];
    }
    public void setCase(int i, int j, Case c){
        this.cases[i][j]=c;
    }

    public int nbAdjacents(int i, int j){
        int a=0;
        if(i!=0 && cases[i-1][j].occupee) a++;
        if(i!=hauteur-1 && cases[i+1][j].occupee) a++;
        if(j!=0 && cases[i][j-1].occupee) a++;
        if(j!= largeur-1 && cases[i][j+1].occupee) a++;
        return a;
    }
    
    public int getMaxI(){
        int maxI=0;
        for (int i=0; i<hauteur; i++){
	        for(int j=0; j<largeur; j++){
		        if(cases[i][j].estOccupee()) maxI=i;
            }
        }
        return maxI;
    }
    public int getMinI(){
        for (int i=0; i<hauteur; i++){
	        for(int j=0; j<largeur; j++){
		        if(cases[i][j].estOccupee()) return i;
            }
        }
        return -1;
    }
    public int getMaxJ(){
        int maxJ=0;
        for (int j=0; j<largeur; j++){
            for (int i=0; i<hauteur; i++){
		        if(cases[i][j].estOccupee()) maxJ=j;
            }
        }
        return maxJ;
    }
    public int getMinJ(){
        for (int j=0; j<largeur; j++){
            for (int i=0; i<hauteur; i++){
		        if(cases[i][j].estOccupee()) return j;
            }
        }
        return -1;
    }
    public void poserTuile(Tuile t, int i, int j){
        this.cases[i][j].tuile=t;
        cases[i][j].occupee=true;
    }
    public abstract int valide(Tuile t, int i, int j);
}
