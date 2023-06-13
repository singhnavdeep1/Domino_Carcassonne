package model;
import java.awt.*;
public class Player{
    protected Color color;
    protected int partisans=8;
    protected String pseudo;
    protected int id;
    protected static int nbId=0;
    protected int point;

    public Player(String pseudo){
        this.pseudo=pseudo;
        this.id=nbId;
        nbId++;
    }
    public Player(){
        this.id=nbId;
        nbId++;
        this.point=0;
    }
    public void setColor(Color c){
        this.color=c;
    }
    public Color getColor() {
        return color;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point+= point;
    }

    public String getPseudo(){
        return this.pseudo;
    }
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getId(){
        return this.id;
    }
    public boolean poserPartisans(){
        if(partisans<=0){
            return false;
        }else {
            partisans--;
            return true;
        } 
    }
    public static void reset(){
        nbId=0;
    }
}