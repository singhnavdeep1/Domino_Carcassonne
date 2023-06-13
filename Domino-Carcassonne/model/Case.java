package model;
public  class Case {
    public boolean occupee;
    public Tuile tuile;
    
    public Case(){}

    public boolean estOccupee(){
        return this.occupee;
    }
    public void poserTuile(Tuile t){
        this.tuile=t;
        this.occupee=true;
    }
    
}
