package com.jwere.swingy.view;

import javax.swing.*;

import com.jwere.swingy.controller.StartViewController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartGuiView extends JPanel implements StartView{

    private static final long serialVersionUID = 1L;
    private JButton createHeroButton = new JButton("Create Hero");
    private JButton selectHeroButton = new JButton("Select Hero");
    private JButton switchViewButton = new JButton("Switch to Console");
    private StartViewController controller;

    public void start(){

        controller = new StartViewController(this);
        Helper.getFrame().setTitle("swingy");
        this.setLayout(new GridBagLayout());

        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        this.add(createHeroButton, gbc);
        this.add(selectHeroButton, gbc);
        this.add(switchViewButton, gbc);

        this.setVisible(true);
        this.setOpaque(false);
        Helper.getFrame().setContentPane(this);
        Helper.getFrame().revalidate();
        Helper.getFrame().setBackground(Color.ORANGE);
        Helper.showFrame();

        createHeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onCreateHeroButtonPressed();
            }
        });
        selectHeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onSelectHeroButtonPressed();
            }
        });
        switchViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onSwitchButtonPressed();
            }
        });
    }

    @Override
    public void openCreateHero() {
        this.setVisible(false);
        new CreateHeroViewGui().start();
    }

    @Override
    public void switchView() {
        Helper.hideFrame();
        new StartConsoleView().start();
    }

    @Override
    public void openSelectHero() {
        this.setVisible(false);
        new SelectHeroViewGui().start();
    }
}