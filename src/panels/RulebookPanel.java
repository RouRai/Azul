package panels;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
public class RulebookPanel extends JPanel implements ActionListener{
    //instanstiates variables
    private ArrayList<BufferedImage> rules;
    private BufferedImage p1, p2, p3, p4, p5, p6, bg;
    private JButton forwardButton;
    private JButton backwardButton;
    private JButton returnButton;
    private int i;
    //declares variables
    public RulebookPanel(){
        rules = new ArrayList<>();
        i = 0;
        forwardButton = new JButton("Next");
        backwardButton = new JButton("Previous");
        returnButton = new JButton("Return");
        setIm();
        setButtons();
    }
    //sets Buttons up and their actions
    public void setButtons() {
        super.add(backwardButton);
        backwardButton.setActionCommand("back");
        backwardButton.addActionListener(this);
        super.add(forwardButton);
        forwardButton.setActionCommand("next");
        forwardButton.addActionListener(this);
        super.add(returnButton);
        returnButton.setActionCommand("return");
        returnButton.addActionListener(this);
    }
    //draws the images/buttons
    public void paintComponent(Graphics g){
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
        checkBounds();
        returnButton.setBounds((int)(getWidth() / 2.3), (int)(getHeight() / 1.35), getWidth() / 8, getHeight() / 15);
        backwardButton.setBounds(getWidth() / 10, (int)(getHeight() / 1.3), getWidth() / 10, getHeight() / 20);
        forwardButton.setBounds((int)(getWidth() / 1.3), (int)(getHeight() / 1.3), getWidth() / 10, getHeight() / 20);
        g.drawImage(rules.get(i), (int)(getWidth() / 2.6), getHeight() / 6, getWidth() /4, getWidth() / 4, null);
        
    }
    public void actionPerformed(ActionEvent e){
        String str = e.getActionCommand();
        if(str.equals("next")){
            i++;
            System.out.println("next");
        } else if(str.equals("back")){
            i--;
        }
        repaint();
    }
    //verifies that whenever a player wants to go to the next image 
    public void checkBounds(){
        if(i < 0){
            i = 5;
        } else if(i > 5){
            i = 0;
        }
    }
    //sets images
    public void setIm(){
        try {
            bg = ImageIO.read(RulebookPanel.class.getResource("/images/Background.jpg"));
            p1 = ImageIO.read(RulebookPanel.class.getResource("/images/Rulebook1.jpg.png"));
            p2 = ImageIO.read(RulebookPanel.class.getResource("/images/Rulebook2.jpg.png"));
            p3 = ImageIO.read(RulebookPanel.class.getResource("/images/Rulebook3.jpg.png"));
            p4 = ImageIO.read(RulebookPanel.class.getResource("/images/Rulebook4.jpg.png"));
            p5 = ImageIO.read(RulebookPanel.class.getResource("/images/Rulebook5.jpg.png"));
            p6 = ImageIO.read(RulebookPanel.class.getResource("/images/Rulebook6.jpg.png"));
            rules.add(p1);
            rules.add(p2);
            rules.add(p3);
            rules.add(p4);
            rules.add(p5);
            rules.add(p6);
            
        } catch (Exception e) {
            return;
        }
    }
}
