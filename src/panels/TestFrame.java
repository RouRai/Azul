package panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import game.Constants;

public class TestFrame extends JFrame{

    private CardLayout cl;
    private HashMap<String,JPanel> panels;
    private JButton ruleButton;

    public TestFrame(String str){
        super(str);
        cl = new CardLayout();
        panels = new HashMap<>();
        setUpPanels();
        add(panels.get(Constants.PANEL_CONT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setVisible(true);
    }

    private void setUpPanels() {
        setUpButtons();

        // Add panels to HashMap
        panels.put(Constants.PANEL_CONT, new JPanel());
        panels.put(Constants.RULE_PANEL, new RulebookPanel());
        panels.put(Constants.START_PANEL, new StartScreenPanel());

        // Set up layout 
        panels.get(Constants.PANEL_CONT).setLayout(cl);

        // Set up buttons in the panels
        panels.get(Constants.START_PANEL).add(ruleButton, BorderLayout.LINE_END);

        // Add panels to panelCont
        panels.get(Constants.PANEL_CONT).add(panels.get(Constants.START_PANEL), Constants.START_PANEL);
        panels.get(Constants.PANEL_CONT).add(panels.get(Constants.RULE_PANEL), Constants.RULE_PANEL);

        // Show startScreen at beginning
        cl.show(panels.get(Constants.PANEL_CONT), Constants.START_PANEL);

    }

    private void setUpButtons() {
        ruleButton = new JButton("Instructions");

        ruleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panels.get(Constants.PANEL_CONT), Constants.RULE_PANEL);
            }
        });
    }


}
