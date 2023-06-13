import view.*;
public class Launcher{
    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(
            new Runnable(){
                public void run(){
                    Menu m=new Menu();
                    m.setVisible(true);
                }
            }
        );
    }
}
