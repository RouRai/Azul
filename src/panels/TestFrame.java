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
        add(Constants.PANEL_CONT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.WIDTH, Constants.HEIGHT);
        setVisible(true);
    }

    private void setUpPanels() {
        setUpButtons();

        // Add panels to HashMap
        panels.put(Constants.RULE_PANEL, new RulebookPanel(cl));
        panels.put(Constants.START_PANEL, new StartScreenPanel());

        // Set up layout 
        Constants.PANEL_CONT.setLayout(cl);

        // Set up buttons in the panels
        panels.get(Constants.START_PANEL).add(ruleButton, BorderLayout.LINE_END);

        // Add panels to panelCont
        Constants.PANEL_CONT.add(panels.get(Constants.START_PANEL), Constants.START_PANEL);
        Constants.PANEL_CONT.add(panels.get(Constants.RULE_PANEL), Constants.RULE_PANEL);

        // Show startScreen at beginning
        cl.show(Constants.PANEL_CONT, Constants.START_PANEL);

    }

    private void setUpButtons() {
        ruleButton = new JButton("Rules");

        ruleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(Constants.PANEL_CONT, Constants.RULE_PANEL);
            }
        });
    }


}
