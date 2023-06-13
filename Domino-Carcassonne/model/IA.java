package model;

public class IA extends Player{
    public IA(){
        super("");
        this.setPseudo("Robot");
    }
    public int choose(int min, int max){
        return Utils.generer(min,max);
    }

    
}
