package view;
import model.*;
import java.awt.*;
import javax.swing.*;
public class TuileDominoView extends JPanel{
    TuileDomino model;
    JPanel container;
    Case n0,n1,n2;
    Case e0,e1,e2;
    Case s0,s1,s2;
    Case o0,o1,o2;
    public TuileDominoView(TuileDomino model){
        this.model=model;
        this.setLayout(new GridLayout(5,5));
        this.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK));

        add(new Case(-1));
        n0=new Case(model.nord[0]);
        add(n0);
        n1=new Case(model.nord[1]);
        add(n1);
        n2=new Case(model.nord[2]);
        add(n2);
        add(new Case(-1));

        o0=new Case(model.ouest[0]);
        add(o0);
        add(new Case(-1));
        add(new Case(-1));
        add(new Case(-1));
        e0=new Case(model.est[0]);
        add(e0);

        o1=new Case(model.ouest[1]);
        add(o1);
        add(new Case(-1));
        add(new Case(-1));
        add(new Case(-1));
        e1=new Case(model.est[1]);
        add(e1);

        o2=new Case(model.ouest[2]);
        add(o2);
        add(new Case(-1));
        add(new Case(-1));
        add(new Case(-1));
        e2=new Case(model.est[2]);
        add(e2);

        add(new Case(-1));
        s0=new Case(model.sud[0]);
        add(s0);
        s1=new Case(model.sud[1]);
        add(s1);
        s2=new Case(model.sud[2]);
        add(s2);
        add(new Case(-1));

        this.setVisible(true);
    }
    public class Case extends JPanel{
        JLabel id=new JLabel("");
        public Case(int n){
            if(n>=0){
                Font f=new Font("Serif",Font.BOLD,10);
                id.setFont(f);
                id.setText(String.valueOf(n));
                id.setForeground(Color.BLACK);
                this.add(id);
            }
            this.setBackground(new Color(221,160,221));  
        }
    }

    public TuileDominoView update(){
        n0.id.setText(String.valueOf(model.nord[0]));
        n1.id.setText(String.valueOf(model.nord[1]));
        n2.id.setText(String.valueOf(model.nord[2]));
        e0.id.setText(String.valueOf(model.est[0]));
        e1.id.setText(String.valueOf(model.est[1]));
        e2.id.setText(String.valueOf(model.est[2]));
        s0.id.setText(String.valueOf(model.sud[0]));
        s1.id.setText(String.valueOf(model.sud[1]));
        s2.id.setText(String.valueOf(model.sud[2]));
        o0.id.setText(String.valueOf(model.ouest[0]));
        o1.id.setText(String.valueOf(model.ouest[1]));
        o2.id.setText(String.valueOf(model.ouest[2]));
        this.repaint();
        return this;
    }
}