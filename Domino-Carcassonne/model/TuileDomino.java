package model;
import java.util.Random;
public class TuileDomino extends Tuile{
    public static Random rng =new Random();
    public int[] nord=new int[3];
    public int[] sud=new int[3];
    public int[] est=new int[3];
    public int[] ouest=new int[3];
    

    public TuileDomino(){
        for(int i=0; i<3; i++){
            int x=rng.nextInt(3);
            nord[i]=x;
        }
        for(int i=0; i<3; i++){
            int x=rng.nextInt(3);
            sud[i]=x;
        }
        for(int i=0; i<3; i++){
            int x=rng.nextInt(3);
            est[i]=x;
        }
        for(int i=0; i<3; i++){
            int x=rng.nextInt(3);
            ouest[i]=x;
        }
    }
    
    
    public void rotation(){
        int[] cpNord=new int[3];
        int[] cpEst=new int[3];
        int[] cpSud=new int[3];
        int[] cpOuest=new int[3];
        for(int i=0; i<3; i++){
            cpNord[i]=nord[i];
            cpEst[i]=est[i];
            cpSud[i]=sud[i];
            cpOuest[i]=ouest[i];
        }
        nord=cpOuest;
        est=cpNord;
        sud=cpEst;
        ouest=cpSud;
        Utils.inverser(nord);
        Utils.inverser(sud);
    }
    public void NbRotation(int n){
        for (int i=0; i<n; i++){
            rotation();
        }
    }
    //affichage textuel : necessite decompositon ligne par ligne pour affichage plateau

    public String ligne1(){
        return("." + nord[0] + nord[1] + nord[2] + ".");
    }
    public String ligne2(){
        return(ouest[0] + "   " + est[0]);
    }
    public String ligne3(){
        return(ouest[1] + "   " + est[1]);
    }
    public String ligne4(){
        return(ouest[2] + "   " + est[2]);
    }
    public String ligne5(){
        return("." + sud[0] + sud[1] + sud[2] + ".");
    }
    public void afficher(){
        System.out.println(ligne1());
        System.out.println(ligne2());
        System.out.println(ligne3());
        System.out.println(ligne4());
        System.out.println(ligne5());
        System.out.println();
    }
    public static void main(String[] args){
        TuileDomino d=new TuileDomino();
        d.afficher();
        d.rotation();
        System.out.println();
        d.afficher();
    }
}
