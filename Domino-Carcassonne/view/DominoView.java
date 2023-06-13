package view;
import java.util.ArrayList;
import model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class DominoView extends JFrame implements Affichable{
    Domino modelD;
    PlateauDomino modelP;
    TuileDomino modelT;

    TuileDominoView viewT;
    PlateauView plateau;

    JPanel com,recap;
    JLabel tour;
    JButton pioche,pivot,defausse,abandon;

    public DominoView(Domino modelD){
        this.setTitle("Bienvenue dans Domino !");
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(0,0,screenSize.width,screenSize.height);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);

        this.modelD=modelD;
        this.modelP=(PlateauDomino)modelD.plateau;
        plateau=new PlateauView();
        plateau.setBackground(new Color(250,240,230));

        
        TuileDominoView initV=new TuileDominoView((TuileDomino)modelD.premiereTuile());
        plateau.add(initV);
        initV.setBounds(plateau.tailleTuileX*(modelP.largeur/2),plateau.tailleTuileY*(modelP.hauteur/2),plateau.tailleTuileX, plateau.tailleTuileY);
        plateau.repaint();

        com=new JPanel();
        com.setLayout(null);
        com.setBackground(new Color(250,240,230));
        com.setBounds(plateau.getWidth()+50,20,500,this.getHeight()-100);
        add(com);
        setBoutons();

        tour=new JLabel();
        tour.setBounds(0,0,500,50);


        recap=new JPanel();
        recap.setLayout(null);
        recap.setBounds(0,300+initV.getHeight(),500,500);
        recap.setBackground(new Color(250,240,230));

        com.add(tour);
        com.add(recap);
        updateCom();

      
        if(modelD.actualPlayer instanceof IA) tourIA();
        
    }
    public void setBoutons(){
        pioche=new JButton("piocher");
        pioche.setBounds(0,50,250,50);
        com.add(pioche);
        pioche.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    if(modelD.finPartie()){
                        messageFin();
                    }else{
                        plateau.activer();
                        pioche.setEnabled(false);
                        pivot.setEnabled(true);

                        modelT=modelD.piocherTuile();
                        viewT=new TuileDominoView(modelT);
                        afficherPioche();
                    }
                }
            }
        );
        
        pivot=new JButton("pivoter");
        pivot.setEnabled(false);
        com.add(pivot);
        pivot.setBounds(0,100,250,50);
        pivot.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    modelT.rotation();
                    viewT.update();
                }
            }
        );

        defausse=new JButton("defausser/passer mon tour");
        com.add(defausse);
        defausse.setBounds(0,150,250,50);
        defausse.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    plateau.desactiver(); //sera reactive a la prochaine pioche
                    pioche.setEnabled(true);
                    pivot.setEnabled(false);

                    modelD.auSuivant();
                    updateCom();
                    if(modelD.actualPlayer instanceof IA) tourIA();
                    
                }
            }
        );
    

        abandon=new JButton("abandonner");
        com.add(abandon);
        abandon.setBounds(0,200,250,50);
        abandon.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    plateau.desactiver(); //sera reactive a la prochaine pioche
                    pioche.setEnabled(true);
                    pivot.setEnabled(false);
                    defausse.setEnabled(true);

                    modelD.abandon(modelD.actualPlayer);
                    updateCom();
                    if(modelD.finPartie()) messageFin(); 
                    if(modelD.actualPlayer instanceof IA) tourIA();

                    
                }
            }
        );
        com.repaint();
    }
    public void updateCom(){
        String s="A ton tour " +modelD.actualPlayer.getPseudo()+" ("+modelD.actualPlayer.getId()+")";
        tour.setText(s);
        tour.repaint();

        if(recap.getComponents()!=null){
            recap.removeAll();
        }
        int i=0;
        for(Player p: modelD.participants){
            JLabel j=new JLabel(p.getPseudo()+"("+p.getId()+") : "+p.getPoint()+"points");
            recap.add(j);
            j.setBounds(0,i*30,500,30);
            i++;
        }
        recap.repaint();

        for(Component c : com.getComponents()){
            if (c instanceof TuileDominoView){
                com.remove(c);
            }
        }
        com.repaint();
    }

    public void afficherPioche(){
        com.add(viewT);
        viewT.setBounds(0,270,plateau.tailleTuileX,plateau.tailleTuileY);
        com.repaint();
        this.getContentPane().revalidate();

    }
    
    public void tourIA(){
        if(modelD.finPartie()){
            messageFin();
            return;
        }
        modelT=modelD.piocherTuile();

        int[] tab=modelD.tourIA(modelT);
        if(tab[0]!=-1 && tab[1]!=-1) {
            viewT=new TuileDominoView(modelT);
            plateau.add(viewT);
            viewT.setBounds(tab[1]*plateau.tailleTuileX,tab[0]*plateau.tailleTuileY,plateau.tailleTuileX, plateau.tailleTuileY);
            this.getContentPane().revalidate();
        }
        plateau.desactiver();
        pioche.setEnabled(true);
        pivot.setEnabled(false);
        defausse.setEnabled(true);
        
        modelD.auSuivant();
        updateCom();
        if(modelD.actualPlayer instanceof IA) tourIA();
        
    }

    public void messageFin(){
        plateau.setVisible(false);
        com.setVisible(false);
        JLabel msg=new JLabel("FIN DE LA PARTIE");
        this.add(msg);
        msg.setBounds(getWidth()/2,0,500,50);

        String s="Voici le(s) vainqueur(s) : ";
        ArrayList <Player> g=modelD.gagnants();
        if(g!=null){
            for(Player p : g){
                s+=p.getPseudo()+" ("+p.getId()+")";
                if(g.indexOf(p)!=g.size()-1) s+=", ";
            }
            JLabel gagnants=new JLabel(s);
            add(gagnants);
            gagnants.setBounds(getWidth()/2,70,getWidth()/2,50);
        }
        int i=0;
        for(Player p: modelD.participants){
            JLabel j=new JLabel(p.getPseudo()+"("+p.getId()+") : "+p.getPoint()+" points");
            add(j);
            j.setBounds(getWidth()/2,140+i*50,500,30);
            i++;
        }
        this.getContentPane().revalidate();
    }
    public class PlateauView extends JPanel implements MouseInputListener{
        PlateauDomino model;
        int tailleTuileX;
        int tailleTuileY;
        int coinX;
        int coinY;
        boolean active;
        public PlateauView(){
            super();
            DominoView.this.add(this);
            int hauteur=DominoView.this.getHeight();
            int largeur=DominoView.this.getWidth();
            setBounds(20,20,largeur-600,hauteur-100);
            model=DominoView.this.modelP;
            setLayout(null);
            addMouseListener(this);
            addMouseMotionListener(this);
            tailleTuileX=this.getWidth()/model.largeur;
            tailleTuileY=this.getHeight()/model.hauteur;
        }
        public void activer(){
            active=true;
        }
        public void desactiver(){
            active=false;
        }
        public void ajouter(int x, int y){
            int score=model.valide(modelT,y,x);
            if(score>=0){
                this.add(viewT);
                viewT.setBounds(coinX,coinY,tailleTuileX,tailleTuileY);
                modelP.poserTuile(modelT,y,x);
                modelD.actualPlayer.setPoint(score);
                
                pioche.setEnabled(true);
                defausse.setEnabled(true);
                pivot.setEnabled(false);
                desactiver();
                modelD.auSuivant();
                updateCom();

                
            }
            if(modelD.actualPlayer instanceof IA) tourIA();
            DominoView.this.getContentPane().revalidate();
        }
        
        public void mouseClicked(MouseEvent e) {
            if(active==true){
                int x=e.getX()/tailleTuileX;
                int y=e.getY()/tailleTuileY;
                coinX=tailleTuileX*x;
                coinY=tailleTuileY*y;
                this.ajouter(x,y);
            }
            
        }
        
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseDragged(MouseEvent e) {}
        public void mouseMoved(MouseEvent e) {}
    }


}