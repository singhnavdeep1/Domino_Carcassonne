package model;
import java.util.Random;
import java .util.ArrayList;
import java.awt.*;

public class Carcassonne extends Jeu{

    public Carcassonne(ArrayList<Player> players){
        sac=new ArrayList<>();
        for(int i=0; i<9;i++){
            this.sac.add(new TuileCarcassonne(TypeTerrain.PRES,TypeTerrain.PRES,TypeTerrain.CHEMINS, TypeTerrain.CHEMINS,false));
        }
        for(int i=0; i<3; i++){
            this.sac.add(new TuileCarcassonne(TypeTerrain.VILLES, TypeTerrain.CHEMINS, TypeTerrain.CHEMINS, TypeTerrain.PRES,false));
        }
        for(int i=0; i<5; i++){
            this.sac.add(new TuileCarcassonne(TypeTerrain.VILLES, TypeTerrain.CHEMINS, TypeTerrain.CHEMINS, TypeTerrain.VILLES,false));
        }
        for(int i=0; i<3; i++){
            this.sac.add(new TuileCarcassonne(TypeTerrain.VILLES, TypeTerrain.VILLES, TypeTerrain.CHEMINS, TypeTerrain.VILLES,false));
        }
        for(int i=0; i<4; i++){
            this.sac.add(new TuileCarcassonne(TypeTerrain.VILLES, TypeTerrain.VILLES, TypeTerrain.PRES, TypeTerrain.VILLES,false));
        }
        for(int i=0; i<3; i++){
            this.sac.add(new TuileCarcassonne(TypeTerrain.VILLES, TypeTerrain.CHEMINS, TypeTerrain.CHEMINS, TypeTerrain.CHEMINS,false));
        }
        for(int i=0; i<8; i++){
            this.sac.add(new TuileCarcassonne(TypeTerrain.CHEMINS, TypeTerrain.PRES, TypeTerrain.CHEMINS, TypeTerrain.PRES,false));
        }
        for(int i=0; i<4; i++){
            this.sac.add(new TuileCarcassonne(TypeTerrain.PRES, TypeTerrain.CHEMINS, TypeTerrain.CHEMINS, TypeTerrain.CHEMINS, true));
        }
        for(int i=0; i<5; i++){
            this.sac.add(new TuileCarcassonne(TypeTerrain.VILLES, TypeTerrain.PRES, TypeTerrain.PRES, TypeTerrain.PRES,false));
        }
        for(int i=0; i<2; i++){
            this.sac.add(new TuileCarcassonne(TypeTerrain.VILLES, TypeTerrain.VILLES, TypeTerrain.PRES, TypeTerrain.PRES,false));
        }
        for(int i=0; i<4; i++){
            this.sac.add(new TuileCarcassonne(TypeTerrain.PRES, TypeTerrain.PRES, TypeTerrain.PRES, TypeTerrain.PRES,true));
        }
        for(int i=0; i<2; i++){
            this.sac.add(new TuileCarcassonne(TypeTerrain.PRES, TypeTerrain.PRES, TypeTerrain.CHEMINS, TypeTerrain.PRES,true));
        }
        for(int i=0; i<3; i++){
            this.sac.add(new TuileCarcassonne(TypeTerrain.VILLES, TypeTerrain.PRES, TypeTerrain.PRES, TypeTerrain.VILLES,false));
        }
        for(int i=0; i<4; i++){
            this.sac.add(new TuileCarcassonne(TypeTerrain.VILLES, TypeTerrain.CHEMINS, TypeTerrain.PRES, TypeTerrain.CHEMINS,false));
        }
        for(int i=0; i<3; i++){
            this.sac.add(new TuileCarcassonne(TypeTerrain.VILLES, TypeTerrain.PRES, TypeTerrain.CHEMINS, TypeTerrain.CHEMINS,false));
        }
        for(int i=0; i<3; i++){
            this.sac.add(new TuileCarcassonne(TypeTerrain.VILLES, TypeTerrain.CHEMINS, TypeTerrain.CHEMINS, TypeTerrain.VILLES,false));
        }
       this.sac.add(new TuileCarcassonne(TypeTerrain.VILLES, TypeTerrain.VILLES, TypeTerrain.VILLES, TypeTerrain.VILLES,false));
       this.sac.add(new TuileCarcassonne(TypeTerrain.CHEMINS, TypeTerrain.CHEMINS, TypeTerrain.CHEMINS, TypeTerrain.CHEMINS,false));

        for(int i=0; i<2; i++){
            this.sac.add(new TuileCarcassonne(TypeTerrain.VILLES, TypeTerrain.PRES, TypeTerrain.PRES, TypeTerrain.VILLES,false));
        }
        for(int i=0; i<3; i++){
            this.sac.add(new TuileCarcassonne(TypeTerrain.PRES, TypeTerrain.VILLES, TypeTerrain.PRES, TypeTerrain.VILLES,false));
        }
        ArrayList<Color> colors=new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.PINK);
        colors.add(Color.YELLOW);
        colors.add(Color.ORANGE);
        colors.add(Color.MAGENTA);
        colors.add(Color.WHITE);
        colors.add(Color.LIGHT_GRAY);


        participants=players;
        for(int i=0;i<participants.size();i++){
            participants.get(i).setColor(colors.get(i));
        }
        premierJoueur();

        plateau=new PlateauCarcassonne();
    }

    public TuileCarcassonne piocherTuile(){
        Random r=new Random();
        TuileCarcassonne t=(TuileCarcassonne)sac.get(r.nextInt(sac.size()));
        sac.remove(t);
        return t;
    }

    public int[] tourIA(Tuile t){
        for(int n=0; n<4; n++){
            ((TuileCarcassonne) t).rotation();
            for (int i=0; i<plateau.hauteur; i++){
                for(int j=0; j<plateau.largeur; j++){
                    int x=plateau.valide(t,i,j);
                    if(x>=0) {
                        plateau.poserTuile(t,i,j);
                        return new int[]{i,j};
                    }
                }
            }
        }
        return new int[]{-1,-1};
    }

}

