import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FallingWords extends JFrame implements Runnable, KeyListener{

    ArrayList<String> words = new ArrayList<>();
    String[] list = {"Kite", "Mellow", "Rush", "Swamp"};
    Thread runner;

    int score = 0;

    JPanel panel = new JPanel();
    JTextField tfWord = new JTextField(20);
    JLabel lblScore = new JLabel("Score: "+ score);

    int[] xVal = {80, 200, 140, 300};
    int y = 30;

    public FallingWords(){
        this.setSize(800,600);
        this.setLayout(null);
        this.setTitle("Falling Words");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        tfWord.setFocusable(true);
        tfWord.addKeyListener((KeyListener) this);

        panel.add(tfWord);
        panel.add(lblScore);
        panel.setBounds(230,450,300, 100);

        add(panel);

        words.addAll(Arrays.asList(list));
        runner = new Thread(this);
        runner.start();
        this.setVisible(true);
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        for (int i = 0; i < words.size(); i++){
            g.drawString(words.get(i), xVal[i], y);
        }

    }
    public static void main(String[] args) {
        new FallingWords();
    }
    @Override
    public void run() {
        boolean correct = false;
        while (true){
        
            if(y > 750){
                y = 30;
            }
            if(y != 750){
                y += 5;
            }
            try {
                runner.sleep(500);
            }
            catch (InterruptedException ex) {
                ex.getMessage();
            }
            repaint();
        }
    }
    @Override
    public void keyPressed (KeyEvent e) {
        boolean correct = false;
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            for (int i = 0; i< words.size(); i++) {
                if (tfWord.getText().equals(words.get(i))){
                    correct = true;
                    break;
                }
            }
            if(correct){
                lblScore.setText("Score: "+ ++score);
            }
            else{
                lblScore.setText("SCore: "+ --score);
            }
            
            
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
       
    @Override
    public void keyReleased(KeyEvent e) {
        
    }

}
