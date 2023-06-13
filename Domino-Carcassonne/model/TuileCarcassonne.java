package model;

public class TuileCarcassonne extends Tuile {
    public boolean abbaye;
    public TypeTerrain nord;
    public TypeTerrain sud;
    public TypeTerrain est;
    public TypeTerrain ouest;

    
    public TuileCarcassonne(TypeTerrain nord, TypeTerrain est, TypeTerrain sud, TypeTerrain ouest,boolean abbaye){
        this.nord=nord;
        this.est=est;
        this.sud=sud;
        this.ouest=ouest;
        this.abbaye=abbaye;
    }
    public TuileCarcassonne(TuileCarcassonne t){
        this.nord=t.nord;
        this.est=t.est;
        this.ouest=t.ouest;
        this.sud=t.sud;
        this.abbaye=t.abbaye;
    }
    public boolean getAbbaye(){
        return abbaye;
    }

    public TypeTerrain rotation_aux(TypeTerrain o){
       if(o==TypeTerrain.CHEMINS) return TypeTerrain.CHEMINS;
       if(o==TypeTerrain.VILLES) return TypeTerrain.VILLES;
       if(o==TypeTerrain.PRES) return TypeTerrain.PRES;
    return null;
    }

    public void rotation(){
        TypeTerrain tmp=nord;
        nord=rotation_aux(ouest);
        ouest=rotation_aux(sud);
        sud=rotation_aux(est);
        est=rotation_aux(tmp);
    }
    public TuileCarcassonne resRota(){
        rotation();
        return this;
    }



}

