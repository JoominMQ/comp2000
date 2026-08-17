import javax.swing.*;
import java.awt.*;


public class App{
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Test stringing nows");
        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                g.setColor(Color.MAGENTA);
                g.fillRect(50, 50, 100, 100);
            }
        };
    }
}
