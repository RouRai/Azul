package panels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import datastructures.Stack;
import game.Constants;

public class RulebookPanel extends JPanel implements ActionListener{

    // Declare variables
    private BufferedImage bg, currentPage;
    private JButton forwardButton, backwardButton, returnButton;
    private Stack<BufferedImage> previousPages, nextPages; // Used for previous pages and next pages

    //declares variables
    public RulebookPanel(){
        previousPages = new Stack<>();
        nextPages = new Stack<>();
        currentPage = Constants.getImage("Rulebook1");
        forwardButton = new JButton("Next");
        backwardButton = new JButton("Previous");
        returnButton = new JButton("Return");
        setIm();
        setButtons();
    }

    //sets Buttons up and their actions
    private void setButtons() {
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
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
        returnButton.setBounds((int)(getWidth() / 2.3), (int)(getHeight() / 1.155), getWidth() / 8, getHeight() / 15);
        backwardButton.setBounds(getWidth() / 10, (int)(getHeight() / 1.125), getWidth() / 10, getHeight() / 20);
        forwardButton.setBounds((int)(getWidth() / 1.3), (int)(getHeight() / 1.125), getWidth() / 10, getHeight() / 20);
        g.drawImage(currentPage, (int)(getWidth() / 3.45), getHeight() / 12, (int)(getWidth() / 2.5), (int)(getWidth() / 2.5), null);
    }


    // Flips the pages after recording button clicks
    public void actionPerformed(ActionEvent e){
        String str = e.getActionCommand();
        if(str.equals("next")){
            if(!nextPages.isEmpty()){
                previousPages.push(currentPage);
                currentPage = nextPages.pop();
            }
        } else if(str.equals("back")){
            if(!previousPages.isEmpty()){
                nextPages.push(currentPage);
                currentPage = previousPages.pop();
            }
        }
        repaint();
    }

    // Sets images
    private void setIm() {
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
