package view;
import model.*;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
public class TuileCarcassonneView extends JPanel implements MouseInputListener{
    private TuileCarcassonne model;
    
    private boolean chemin;
    private Polygon nord;
    private Polygon est;
    private Polygon sud;
    private Polygon ouest;
    
    private boolean aPartisan;
    private Player detenteur;
    private boolean peutPoser=true;
    private int xClick=-1;
    private int yClick=-1;
    public TuileCarcassonneView(TuileCarcassonne model,Player detenteur,CarcassonneView c){
        this.model=model;
        this.detenteur=detenteur;
        this.setSize(c.plateau.tailleTuileX,c.plateau.tailleTuileY);

        this.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.WHITE));

        int[] x={0,this.getWidth()/2,this.getWidth()};
        int[] y={0,this.getHeight()/2,0};
        nord=new Polygon(x,y,3);

        int[] x2={0,this.getWidth()/2,0};
        int[] y2={0,this.getHeight()/2,this.getHeight()};
        ouest=new Polygon(x2,y2,3);
            
        int[] x3={0,this.getWidth()/2,this.getWidth()};
        int[] y3={this.getHeight(),this.getHeight()/2,this.getHeight()};
        sud=new Polygon(x3,y3,3);

        int[] x4={this.getWidth(),this.getWidth()/2,this.getWidth()};
        int[] y4={0,this.getHeight()/2,this.getHeight()};
        est=new Polygon(x4,y4,3);

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D) g.create();

        //NORD
        if(model.nord==TypeTerrain.PRES) g2d.setColor(Color.GREEN);
        if(model.nord==TypeTerrain.CHEMINS){
            chemin=true;
            g2d.setColor(Color.GREEN);
        }
        if(model.nord==TypeTerrain.VILLES) g2d.setColor(new Color(102,55,0)); //marron
        g2d.fill(nord);
        if(chemin==true){
            g2d.setColor(Color.BLACK);
            BasicStroke line=new BasicStroke(4.0f);
            g2d.setStroke(line);
            g2d.drawLine(this.getWidth()/2,0,this.getWidth()/2,this.getHeight()/2);
            chemin=false;
        }

        //OUEST
        if(model.ouest==TypeTerrain.PRES) g2d.setColor(Color.GREEN);
        if(model.ouest==TypeTerrain.CHEMINS){
            chemin=true;
            g2d.setColor(Color.GREEN);
        }
        if(model.ouest==TypeTerrain.VILLES) g2d.setColor(new Color(102,55,0)); //marron  
        g2d.fill(ouest);
        if(chemin==true){
            g2d.setColor(Color.BLACK);
            BasicStroke line=new BasicStroke(4.0f);
            g2d.setStroke(line);
            g2d.drawLine(0,this.getHeight()/2,this.getWidth()/2,this.getHeight()/2);
            chemin=false;
        }
        //SUD
        if(model.sud==TypeTerrain.PRES) g2d.setColor(Color.GREEN);
        if(model.sud==TypeTerrain.CHEMINS){
            chemin=true;
            g2d.setColor(Color.GREEN);
        }
        if(model.sud==TypeTerrain.VILLES) g2d.setColor(new Color(102,55,0)); //marron
        g2d.fill(sud);
        if(chemin==true){
            g2d.setColor(Color.BLACK);
            BasicStroke line=new BasicStroke(4.0f);
            g2d.setStroke(line);
            g2d.drawLine(this.getWidth()/2,this.getHeight(),this.getWidth()/2,this.getHeight()/2);
            chemin=false;
        }

        //EST
        if(model.est==TypeTerrain.PRES) g2d.setColor(Color.GREEN);
        if(model.est==TypeTerrain.CHEMINS){
            chemin=true;
            g2d.setColor(Color.GREEN);
        }
        if(model.est==TypeTerrain.VILLES) g2d.setColor(new Color(102,55,0)); 
        g2d.fill(est);
        if(chemin==true){
            g2d.setColor(Color.BLACK);
            BasicStroke line=new BasicStroke(4.0f);
            g2d.setStroke(line);
            g2d.drawLine(this.getWidth(),this.getHeight()/2,this.getWidth()/2,this.getHeight()/2);
            chemin=false;
        }
        if(model.getAbbaye()==true){
            g2d.setColor(Color.RED);
            g2d.fillOval(this.getWidth()/2-10,this.getHeight()/2-10,20,20);
        }

        g2d.setColor(detenteur.getColor());
        if(xClick!=-1 && yClick!=-1){
            g2d.fillRect(xClick,yClick,10,10);
        } 
    }

    public void nePeutPlusPoser(){
        peutPoser=false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(aPartisan==true && peutPoser==true){
            xClick=e.getX();
            yClick=e.getY();
        }
        if(aPartisan==false && detenteur.poserPartisans()==true && peutPoser==true){
            xClick=e.getX();
            yClick=e.getY();
            aPartisan=true;
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {}

    @Override
    public void mouseExited(MouseEvent arg0) {}

    @Override
    public void mousePressed(MouseEvent arg0) {}

    @Override
    public void mouseReleased(MouseEvent arg0) {}

    @Override
    public void mouseDragged(MouseEvent arg0) {}

    @Override
    public void mouseMoved(MouseEvent arg0) {}

}
