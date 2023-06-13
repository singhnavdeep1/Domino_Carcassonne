package view;
import model.*;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Menu extends JFrame{
    JTextField jt;
    JTextField jt2;
    JButton domino;
    JButton carcassonne;
    JButton valider;
    JButton valider2;
    ArrayList<Player> players;

    public Menu(){
        this.setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0,0,screenSize.width,screenSize.height);
        setTitle("Accueil");
        players=new ArrayList<>();
        
        jt=new JTextField("nom des joueurs (1 par 1)");
        add(jt);
        jt.setBounds(getWidth()/2-200,100,200,40);

        jt2=new JTextField("nombre d'IA");
        add(jt2);
        jt2.setBounds(getWidth()/2-200,150,200,40);


        valider=new JButton("soumettre");
        add(valider);
        valider.setBounds(getWidth()/2+20,100,150,40);
        valider.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String s=jt.getText();
                players.add(new Player(s));
                jt.setText("");
                
            }
        });
        valider2=new JButton("soumettre");
        add(valider2);
        valider2.setBounds(getWidth()/2+20,150,150,40);
        valider2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(!jt2.getText().equals("") && Utils.isNumber(jt2.getText())){
                    int n=Integer.valueOf(jt2.getText());
                    for(int i=0; i<n; i++){
                        players.add(new IA());
                    }
                    jt2.setText("");
                }else{
                    jt2.setText("invalide");
                }
            }
        });

        domino=new JButton("Domino");
        add(domino);
        domino.setBounds(getWidth()/2-200,210,200,40);
        domino.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(players.size()>0){
                    Domino model=new Domino(28,players);
                    DominoView view=new DominoView(model);
                    view.setVisible(true);
                    dispose();
                }
            }
        });

        carcassonne=new JButton("Carcassonne");
        add(carcassonne);
        carcassonne.setBounds(getWidth()/2-200,250,200,40);
        carcassonne.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(players.size()>0 && players.size()<=7){
                    Carcassonne model=new Carcassonne(players);
                    CarcassonneView view=new CarcassonneView(model);
                    view.setVisible(true);
                    dispose();
                }else{
                    players=new ArrayList<>();
                    Player.reset();
                    JLabel msg=new JLabel("Le nombre de joueurs pour Carcassonne est limité à 7, recommencez.");
                    add(msg);
                    msg.setBounds(getWidth()/2-200,300,600,40);
                    getContentPane().revalidate();
                }
            }
        });
        JLabel regles=new JLabel("Renseignez d'abord les joueurs, puis selectionnez un jeu");
        add(regles);
        regles.setBounds(getWidth()/2-220,30,600,40);

    }
}
