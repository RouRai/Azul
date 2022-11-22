package panels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JPanel;

import datastructures.Stack;
import game.Constants;

public class RulebookPanel extends JPanel implements ActionListener{

    //instanstiates variables
    private ArrayList<BufferedImage> rules;
    private BufferedImage bg;
    private BufferedImage currentPage;
    private JButton forwardButton;
    private JButton backwardButton;
    private JButton returnButton;
    private Stack<BufferedImage> previousPages; // Used for previous pages
    private Stack<BufferedImage> nextPages; // Used for next pages
    private int i;

    //declares variables
    public RulebookPanel(){
        previousPages = new Stack<>();
        nextPages = new Stack<>();
        currentPage = Constants.getImage("Rulebook1");
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
        g.drawImage(currentPage, (int)(getWidth() / 2.6), getHeight() / 6, getWidth() /4, getWidth() / 4, null);
        
    }


    // Flips the pages after recording button clicks
    public void actionPerformed(ActionEvent e){
        String str = e.getActionCommand();
        if(str.equals("next")){
            //previousPages.push(currentPage);
            if(!nextPages.isEmpty()){
                previousPages.push(currentPage);
                currentPage = nextPages.pop();
            }
        } else if(str.equals("back")){
            //nextPages.push(currentPage);
            if(!previousPages.isEmpty()){
                nextPages.push(currentPage);
                currentPage = previousPages.pop();
            }
        }
        repaint();
    }

    // Verifies that whenever a player wants to go to the next image 
    public void checkBounds(){
        if(i < 0){
            i = 5;
        } else if(i > 5){
            i = 0;
        }
    }

    // Sets images
    public void setIm() {
        try {
            bg = Constants.getImage("Background");
            setSingleIm("Rulebook6"); 
            setSingleIm("Rulebook5"); 
            setSingleIm("Rulebook4"); 
            setSingleIm("Rulebook3"); 
            setSingleIm("Rulebook2"); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Used for adding Rulebook pages, simply input basic name of file.
    private void setSingleIm(String name) {
        try{
            nextPages.push(Constants.getImage(name));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
