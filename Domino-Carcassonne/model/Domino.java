package model;

import java.util.ArrayList;
import java.util.Random;
public class Domino extends Jeu{
    //constructeur pour vue graphique
    public Domino(int n,ArrayList<Player> players){
        if(n>121) n=121;
        if(n<0) n=0;

        sac=new ArrayList<>();
        for (int i=0; i<n; i++){
            sac.add(new TuileDomino());
        }

        participants=players;
        premierJoueur();

        plateau=new PlateauDomino();
    }
    //constructeur pour vue textuelle
    public Domino(int n){
        if(n>121) n=121;
        if(n<0) n=0;
        sac=new ArrayList<>();
        for (int i=0; i<n; i++){
            sac.add(new TuileDomino());
        }

        participants=new ArrayList<>();
        plateau=new PlateauDomino();
    }
   
    public TuileDomino piocherTuile(){
        Random r=new Random();
        TuileDomino t=(TuileDomino)sac.get(r.nextInt(sac.size()));
        sac.remove(t);
        return t;
    }
   
    public int[] tourIA(Tuile t){
        for(int n=0; n<4; n++){
            ((TuileDomino) t).rotation();
            for (int i=0; i<plateau.hauteur; i++){
                for(int j=0; j<plateau.largeur; j++){
                    int x=plateau.valide(t,i,j);
                    if(x>=0) {
                        plateau.poserTuile(t,i,j);
                        actualPlayer.setPoint(x);
                        return new int[]{i,j};
                    }
                }
            }
        }
        return new int[]{-1,-1};
    }

   
}
    
