package view;

import model.*;
import java.util.Scanner;
import java.util.ArrayList;

public class DominoTextuel {
    Domino modelD;
    PlateauTextuel plateau;
    Scanner sc=new Scanner(System.in);
    public DominoTextuel(Domino modelD){
        this.modelD=modelD;
        plateau=this.new PlateauTextuel((PlateauDomino)modelD.plateau);
    }
    
    public class PlateauTextuel{
        PlateauDomino modelP;
        public PlateauTextuel(PlateauDomino modelP){
            this.modelP=modelP;
        }
        public void afficher(){
            int minJ,maxJ;
            if (modelP.getMinJ()==0) minJ=0;
            else minJ=modelP.getMinJ()-1;
    
            if (modelP.getMaxJ()==modelP.largeur-1) maxJ=modelP.largeur-1;
            else maxJ=modelP.getMaxJ()+1;
    
            int minI,maxI;
            if (modelP.getMinI()==0) minI=0;
            else minI=modelP.getMinI()-1;
    
            if (modelP.getMaxI()==modelP.hauteur-1) maxI=modelP.hauteur-1;
            else maxI=modelP.getMaxI()+1;

            System.out.println();
            for(int j=minJ; j<=maxJ; j++){
                System.out.print("  "+j+"  ");
            }
            System.out.println();
            for (int i=minI; i<=maxI; i++){ 
                String s1="";
                String s2="";
                String s3="";
                String s4="";
                String s5="";
                for(int j=minJ; j<=maxJ; j++){
                    if(modelP.cases[i][j].estOccupee()){
                        s1=s1+((TuileDomino)modelP.cases[i][j].tuile).ligne1();
                        s2=s2+((TuileDomino)modelP.cases[i][j].tuile).ligne2();
                        s3=s3+((TuileDomino)modelP.cases[i][j].tuile).ligne3();
                        s4=s4+((TuileDomino)modelP.cases[i][j].tuile).ligne4();
                        s5=s5+((TuileDomino)modelP.cases[i][j].tuile).ligne5();
                    }else{
                        s1=s1+".....";
                        s2=s2+".....";
                        s3=s3+".....";
                        s4=s4+".....";
                        s5=s5+".....";
                    }
                }
                System.out.println(s1);
                System.out.println(s2);
                System.out.println(s3+"   "+i);
                System.out.println(s4);
                System.out.println(s5);
            }
            System.out.println();
        }
    
    }
    public void jouerTour(Player p){
        plateau.afficher();
        TuileDomino t=modelD.piocherTuile();
        System.out.println("---------- Au tour de "+p.getPseudo()+" ("+p.getId()+")"+" ----------"+"\n");
        

        if(p instanceof IA){
            modelD.tourIA(t);
        }else{
            System.out.println("--> Appuyez sur q si vous voulez quitter la partie, autre sinon");
            if(sc.next().equals("q")){
                modelD.abandon(p);
                return;
            }
            int i,j;
            do{
                System.out.println("Vous avez pioché ce domino : ");
                t.afficher();
                System.out.println("--> Appuyez d si vous voulez defausser, autre sinon");

                if(sc.next().equals("d")){
                    modelD.auSuivant();
                    return;
                }

                System.out.println("--> Renseignez l'abscisse de la case choisie");
                j=Integer.valueOf(sc.next());

                System.out.println("--> Renseignez l'ordonnée de la case choisie");
                i=Integer.valueOf(sc.next());

                System.out.println("--> Combien de fois souhaitez vous faire pivoter à droite le domino ?");
                int n=Integer.valueOf(sc.next());
                t.NbRotation(n);
            }while(modelD.plateau.valide(t,i,j)==-1);

            p.setPoint(modelD.plateau.valide(t,i,j));
            modelD.plateau.poserTuile(t,i,j);
        }
        modelD.auSuivant();
    }
    public void initPlayers(){
        System.out.println("Nombre de joueurs humains : ");
        int n=Integer.valueOf(sc.next());
        for (int i=0; i<n; i++){
            System.out.println("Pseudo du joueur "+i+":");
            String s=sc.next();
            modelD.addPlayer(s);
        }
        System.out.println("Nombre d'IA souhaitées :");
        int a=Integer.valueOf(sc.next());
        for (int i=0; i<a; i++){
            modelD.addIA();
        }
    }
    public void recap(){
        System.out.println("---------- FIN DE LA PARTIE ----------");
        
        for(Player p : modelD.participants){
            System.out.println(p.getPseudo()+" ("+p.getId()+")"+" : "+p.getPoint()+" point(s)");
        }
        String s="Voici le(s) vainqueur(s) : ";
        ArrayList <Player> g=modelD.gagnants();
        if(g!=null){
            for(Player p : g){
                s+=p.getPseudo()+" ("+p.getId()+")";
                if(g.indexOf(p)!=g.size()-1) s+=", ";
            }
            System.out.println(s);
        }
        
    }
    
    public void jouerManche(){
        initPlayers();
        modelD.premierJoueur();
        modelD.premiereTuile();
        while(modelD.sac.size()>0 && modelD.participants.size()>0){
            this.jouerTour(modelD.actualPlayer);
            if(modelD.finPartie()){
                plateau.afficher();
                recap();
                return;
            }
        }
        recap();
        return;

    }
    public static void main(String[] args){
        Domino model=new Domino(28);
        DominoTextuel view=new DominoTextuel(model);
        view.jouerManche();
    }
    
    
}
