package view;
import model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class CarcassonneView extends JFrame implements Affichable{
    Carcassonne modelC;
    PlateauCarcassonne modelP;
    TuileCarcassonne modelT;

    TuileCarcassonneView viewT;
    PlateauView plateau;

    JPanel com;
    JLabel tour;

    JButton pioche,pivot,defausse,abandon;

    public CarcassonneView(Carcassonne modelC){
        setTitle("Bienvenue dans Carcassonne !");
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0,0,screenSize.width,screenSize.height);
        this.getContentPane().setBackground(Color.LIGHT_GRAY);

        this.modelC=modelC;
        this.modelP=(PlateauCarcassonne)modelC.plateau;
        plateau=new PlateauView();
        plateau.setBackground(new Color(250,240,230));

        TuileCarcassonneView initV=new TuileCarcassonneView((TuileCarcassonne)modelC.premiereTuile(),modelC.actualPlayer,this);
        plateau.add(initV);
        initV.setBounds(plateau.tailleTuileX*(modelP.largeur/2),plateau.tailleTuileY*(modelP.hauteur/2),plateau.tailleTuileX, plateau.tailleTuileY);
        initV.nePeutPlusPoser();
        plateau.repaint();

        com=new JPanel();
        com.setLayout(null);
        com.setBackground(new Color(250,240,230));
        com.setBounds(plateau.getWidth()+50,20,500,this.getHeight()-100);
        add(com);
        setBoutons();

        tour=new JLabel();
        tour.setBounds(0,0,500,50);
        com.add(tour);

        
        JLabel regles=new JLabel("Cliquez sur la tuile pour ajouter/déplacer un pion !");
        JLabel ville=new JLabel("ville -> marron");
        JLabel pre=new JLabel("pre -> vert");
        JLabel chemin=new JLabel("chemin -> trait noir");
        JLabel abbaye=new JLabel("abbaye -> rond rouge");
        com.add(regles);
        com.add(pre);
        com.add(ville);
        com.add(chemin);
        com.add(abbaye);
        regles.setBounds(0,300+initV.getHeight(),500,30);
        pre.setBounds(0,300+initV.getHeight()+40,500,30);
        ville.setBounds(0,300+initV.getHeight()+80,500,30);
        chemin.setBounds(0,300+initV.getHeight()+120,500,30);
        abbaye.setBounds(0,300+initV.getHeight()+160,500,30);
        
        updateCom();
        if(modelC.actualPlayer instanceof IA) tourIA();
    }
    

    public void setBoutons(){
        pioche=new JButton("piocher");
        pioche.setBounds(0,50,250,50);
        com.add(pioche);
        pioche.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    
                    if(modelC.finPartie()){
                        messageFin();
                    }else{
                        plateau.activer();
                        pioche.setEnabled(false);
                        pivot.setEnabled(true);
                        
                        modelT=modelC.piocherTuile();
                        viewT=new TuileCarcassonneView(modelT,modelC.actualPlayer,CarcassonneView.this);
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
                    viewT.repaint();
                }
            }
        );
        defausse=new JButton("defausser/passer mon tour");
        com.add(defausse);
        defausse.setBounds(0,150,250,50);
        defausse.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    plateau.desactiver(); 
                    pioche.setEnabled(true);
                    pivot.setEnabled(false);

                    modelC.auSuivant();
                    updateCom();
                    if(modelC.actualPlayer instanceof IA) tourIA();
                    
                }
            }
        );
        abandon=new JButton("abandonner");
        com.add(abandon);
        abandon.setBounds(0,200,250,50);
        abandon.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e){
                    plateau.desactiver();
                    pioche.setEnabled(true);
                    pivot.setEnabled(false);

                    modelC.abandon(modelC.actualPlayer);
                    updateCom();

                    if(modelC.actualPlayer instanceof IA) tourIA();

                    if(modelC.finPartie()){
                        messageFin();
                    }  
                }
            }
        );
    }
    public void updateCom(){
        String s="A ton tour " +modelC.actualPlayer.getPseudo()+" ("+modelC.actualPlayer.getId()+")";
        tour.setText(s);
        tour.repaint();

        for(Component c : com.getComponents()){
            if (c instanceof TuileCarcassonneView){
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
        if(modelC.finPartie()){
            messageFin();
            return;
        }
        modelT=modelC.piocherTuile();

        int[] tab=modelC.tourIA(modelT);
        if(tab[0]!=-1 && tab[1]!=-1) {
            viewT=new TuileCarcassonneView(modelT,modelC.actualPlayer,this);
            plateau.add(viewT);
            viewT.setBounds(tab[1]*plateau.tailleTuileX,tab[0]*plateau.tailleTuileY,plateau.tailleTuileX, plateau.tailleTuileY);
            this.getContentPane().revalidate();
        }
        pioche.setEnabled(true);
        pivot.setEnabled(false);
        defausse.setEnabled(true);
        viewT.nePeutPlusPoser();
        plateau.desactiver();

        modelC.auSuivant();
        updateCom();
        if(modelC.actualPlayer instanceof IA) tourIA(); 
    }

    public void messageFin(){
        plateau.setVisible(false);
        com.setVisible(false);
        JLabel msg=new JLabel("FIN DE LA PARTIE");
        this.add(msg);
        msg.setBounds(getWidth()/2,0,500,50);
        this.getContentPane().revalidate();
    }

    public class PlateauView extends JPanel implements MouseInputListener{
        PlateauCarcassonne model;
        boolean active; 
        int tailleTuileX;
        int tailleTuileY;
        int coinX;
        int coinY;
        public PlateauView(){
            super();
            CarcassonneView.this.add(this);
            int hauteur=CarcassonneView.this.getHeight();
            int largeur=CarcassonneView.this.getWidth();
            setBounds(20,20,largeur-600,hauteur-100);
            setLayout(null);
            addMouseListener(this);
            addMouseMotionListener(this);

            model=CarcassonneView.this.modelP;

            tailleTuileX=this.getWidth()/model.largeur;
            tailleTuileY=this.getHeight()/model.hauteur;
        }
        public void ajouter(int x, int y){
            int score=model.valide(modelT,y,x);
            if(score>=0){
                modelP.poserTuile(modelT,y,x);
                this.add(viewT);
                viewT.setBounds(coinX,coinY,tailleTuileX,tailleTuileY);
                viewT.nePeutPlusPoser(); 
                
                
                pioche.setEnabled(true);
                defausse.setEnabled(true);
                pivot.setEnabled(false);
                desactiver(); //sera reactive a la prochaine pioche

                modelC.auSuivant();
                updateCom(); 
            }
            if(modelC.actualPlayer instanceof IA) tourIA();
            CarcassonneView.this.getContentPane().revalidate();
        }
        public void desactiver(){
            active=false;
        }
        public void activer(){
            active=true;
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
