package model;

public class PlateauDomino extends Plateau{
    public PlateauDomino(){
        super();
    }
    public void afficher(){
        int minJ,maxJ;
        if (getMinJ()==0) minJ=0;
        else minJ=getMinJ()-1;

        if (getMaxJ()==largeur-1) maxJ=largeur-1;
        else maxJ=getMaxJ()+1;

        int minI,maxI;
        if (getMinI()==0) minI=0;
        else minI=getMinI()-1;

        if (getMaxI()==hauteur-1) maxI=hauteur-1;
        else maxI=getMaxI()+1;

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
                if(cases[i][j].estOccupee()){
                    s1=s1+((TuileDomino)cases[i][j].tuile).ligne1();
                    s2=s2+((TuileDomino)cases[i][j].tuile).ligne2();
                    s3=s3+((TuileDomino)cases[i][j].tuile).ligne3();
                    s4=s4+((TuileDomino)cases[i][j].tuile).ligne4();
                    s5=s5+((TuileDomino)cases[i][j].tuile).ligne5();
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

    
    public int valide(Tuile t,int i, int j){
        if(i<0 || i>hauteur-1 || j<0 || j>largeur-1 ) return -1;
        if (nbAdjacents(i, j)==0) return -1;
        if(cases[i][j].estOccupee()) return -1;
        int score=0;

        if(i!=0 && cases[i-1][j].estOccupee()){
            if(!Utils.correspond(((TuileDomino)t).nord,((TuileDomino)cases[i-1][j].tuile).sud)) return -1;
            else score+=Utils.somme(((TuileDomino)t).nord);
        }
        if(i!=hauteur-1 && cases[i+1][j].estOccupee()){
            if(!Utils.correspond(((TuileDomino)t).sud,((TuileDomino)cases[i+1][j].tuile).nord)) return -1;
            else score+=Utils.somme(((TuileDomino)t).sud);
        }
        if(j!=0 && cases[i][j-1].estOccupee()){
            if(!Utils.correspond(((TuileDomino)t).ouest,((TuileDomino)cases[i][j-1].tuile).est)) return -1;
            else score+=Utils.somme(((TuileDomino)t).ouest);
        }
        if(j!=largeur-1 && cases[i][j+1].estOccupee()){
            if(!Utils.correspond(((TuileDomino)t).est,((TuileDomino)cases[i][j+1].tuile).ouest)) return -1;
            else score+=Utils.somme(((TuileDomino)t).est);
        }
        return score;
    }
}
