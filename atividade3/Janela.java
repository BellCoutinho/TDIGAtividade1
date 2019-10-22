import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;

public class Janela extends JFrame {
    public Janela() {
        super("Atividade 3");

        Panel panel = new Panel();
        add(panel);
        addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent event){
                System.out.println("Characters: " + event.getKeyChar());

                //repaint();
              }
        });
        this.setSize(600, 500);
        this.setMinimumSize(new Dimension(600, 500));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
    }   
    public class Panel extends JPanel {
        private String texto;
        public Panel() {
            texto = new String("");

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent event) {
                    if ((event.getX() > 50 && event.getX() < 550) && (event.getY() > 30 && event.getY() < 80))  
                        System.out.printf("Mouse (%d, %d)\n", event.getX(), event.getY());
                }
            });
           
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setFont(new Font("SansSerif", Font.PLAIN, 14));
            g.drawRect(10, 10, 575, 449);
            g.drawRect(50, 30, 500, 50);
            g.drawString(texto, 20, 110);
        }
    }   
}


