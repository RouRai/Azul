package panels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JPanel;
import datastructures.Stack;
import java.awt.*;
import game.Constants;
public class InstructionsPanel extends JPanel implements ActionListener{
    private CardLayout cl;
    private BufferedImage bg, currentPage;
    private JButton forwardButton, backwardButton, returnButton;
    private Stack<BufferedImage> previousPages, nextPages; 
    public InstructionsPanel(CardLayout c){
        cl = c;
        previousPages = new Stack<>();
        nextPages = new Stack<>();
        setButtons();
    }

    private void setButtons(){
        forwardButton = new JButton("Next");
        backwardButton = new JButton("Previous");
        returnButton = new JButton("Return");
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

    private void setIm() {
        try {
            bg = Constants.getImage("Background");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        } else if(str.equals("return")){
            cl.show(Constants.PANEL_CONT, Constants.START_PANEL);
        }
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
        returnButton.setBounds((int)(getWidth() / 2.3), (int)(getHeight() / 1.155), getWidth() / 8, getHeight() / 15);
        backwardButton.setBounds(getWidth() / 10, (int)(getHeight() / 1.125), getWidth() / 10, getHeight() / 20);
        forwardButton.setBounds((int)(getWidth() / 1.3), (int)(getHeight() / 1.125), getWidth() / 10, getHeight() / 20);
    }
}
