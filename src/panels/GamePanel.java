package panels;

import javax.swing.JButton;
import javax.swing.JPanel;

import game.Constants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener{

    private CardLayout cl;
    private JButton returnStart;

    public GamePanel(CardLayout cl) {
        this.cl = cl;
        setUpButtons();
    }

    private void setUpButtons() {
        returnStart = new JButton("Return to Start Screen");

        add(returnStart, BorderLayout.LINE_END);

        returnStart.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(returnStart)){
            cl.show(Constants.PANEL_CONT, Constants.START_PANEL);
        }
        
    }


}