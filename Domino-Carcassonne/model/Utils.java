package model;
import java.util.Random;
import java.util.ArrayList;
public class Utils {
    public static int somme(int[] tab){
        int somme=0;
        for(int i=0; i<tab.length; i++){
            somme+=tab[i];
        }
        return somme;
    }
    public static void inverser(int[] tab){
        int tmp=tab[0];
        tab[0]=tab[2];
        tab[2]=tmp;
    }
    public static boolean correspond(int[] t1, int[] t2){
        for(int i=0; i<t1.length; i++){
            if(t1[i]!=t2[i]) return false;
        }
        return true;
    }
    public static int generer(int min, int max){
        Random rng=new Random();
        int n=min+rng.nextInt(max-min);
        return n;
    }
    public static void afficher(ArrayList<Player> a){
        for(Player p : a){
            System.out.println(p.getPseudo());
        }
    }
    public static boolean isNumber(String s){
        for (int i=0; i<s.length(); i++){
            if(s.charAt(i)<48 || s.charAt(i)>57) return false;
        }
        return true;
    }
    
}
