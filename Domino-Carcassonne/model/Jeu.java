package model;


import java.util.ArrayList;
public abstract class Jeu{
    public ArrayList<Player> participants;
    public ArrayList<Tuile> sac;
    public Player actualPlayer;
    public Plateau plateau;

   
    
    public void auSuivant(){
        int index=participants.indexOf(actualPlayer);
        if(index==participants.size()-1) index=0;
        else index+=1;
        actualPlayer=participants.get(index);
    }

    public void abandon(Player p){
        if(participants.size()==1){
            participants.remove(p);
        }else{
            auSuivant();
            participants.remove(p);
        }
    }
    public abstract int[] tourIA(Tuile t);

    public boolean finPartie(){
        if (participants.size()==0 || sac.size()==0) return true;
        return false;
    }
    public void addPlayer(String s){
        participants.add(new Player(s));
    }
    public void addIA(){
        participants.add(new IA());
    }

    public abstract Tuile piocherTuile();
    
    public void premierJoueur(){
        actualPlayer=participants.get(0);
    }

    public Tuile premiereTuile(){
        Tuile t=piocherTuile();
        plateau.poserTuile(t,plateau.hauteur/2,plateau.largeur/2);
        return t;
    }
    public ArrayList<Player> gagnants(){
        if(participants.size()>0){
            Player gagnant=participants.get(0);
            for(Player p : participants){
                if (p.getPoint()>gagnant.getPoint()){
                    gagnant=p;
                }
            }
            ArrayList<Player> gagnants=new ArrayList<>();
            for(Player p : participants){
                if (p.getPoint()==gagnant.getPoint()){
                    gagnants.add(p);
                }
            }
            return gagnants;
        }
        return null;
    }
}

