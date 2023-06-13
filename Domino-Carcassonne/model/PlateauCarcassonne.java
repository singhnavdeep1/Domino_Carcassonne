package model;

public class PlateauCarcassonne extends Plateau{
    public PlateauCarcassonne(){
        super();
    }
    
    public static boolean correspond(TypeTerrain t1, TypeTerrain t2){
        return(t1==t2); 
    }

    public int valide(Tuile t,int i, int j){
        if(i<0 || i>=hauteur || j<0 || j>=largeur ) return -1;
        if (nbAdjacents(i, j)==0) return -1;
        if(cases[i][j].estOccupee()) return -1;

        if(i!=0 && cases[i-1][j].estOccupee()){
            if(!correspond(((TuileCarcassonne)t).nord,((TuileCarcassonne)cases[i-1][j].tuile).sud)){
                return -1;
            }
            
        }
        if(i!=hauteur-1 && cases[i+1][j].estOccupee()){
            if(!correspond(((TuileCarcassonne)t).sud,((TuileCarcassonne)cases[i+1][j].tuile).nord)){
                return -1;
            }
        }

        if(j!=largeur-1 && cases[i][j+1].estOccupee()){
            if(!correspond(((TuileCarcassonne)t).est,((TuileCarcassonne)cases[i][j+1].tuile).ouest)){
                return -1;
            }
        }
        if(j!=0 && cases[i][j-1].estOccupee()){
            if(!correspond(((TuileCarcassonne)t).ouest,((TuileCarcassonne)cases[i][j-1].tuile).est)){
                return -1;
            }
        }
        return 1;
    }
}

