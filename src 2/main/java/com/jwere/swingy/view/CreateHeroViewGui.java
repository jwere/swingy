package com.jwere.swingy.view;

//import com.jwere.swingy.Main;
import com.jwere.swingy.controller.CreateHeroController;
import com.jwere.swingy.view.GameViewGui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateHeroViewGui extends JPanel implements CreateHeroView {

    private static final long serialVersionUID = 1L;
    private JLabel heroNameLabel = new JLabel("Hero name:");
    private JTextField heroNameField = new JTextField(10);
    private JButton createHeroButton = new JButton("Create Hero");
    private String[] heroClasses = {"Warrior", "Shaman", "Priest", "Paladin", "Mage", "Hunter"};
    private JLabel heroClass = new JLabel("Class:");
    private JComboBox<String> classesComboBox = new JComboBox<>(heroClasses);
    private JEditorPane infoPane = new JEditorPane();

    private CreateHeroController controller;

    @Override
    public void start() {
        controller = new CreateHeroController(this);

        buildUI();
    }

    private void buildUI() {
        Helper.getFrame().setTitle("Create Hero");
        this.setLayout(new GridBagLayout());
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JPanel createHeroPanel = new JPanel();
        createHeroPanel.add(heroNameLabel);
        createHeroPanel.add(heroNameField);
        createHeroPanel.setVisible(true);
        this.add(createHeroPanel, gbc);

        JPanel classPannel = new JPanel();
        classPannel.add(heroClass);
        classesComboBox.setSelectedIndex(0);
        classPannel.add(classesComboBox);
        classPannel.setVisible(true);
        this.add(classPannel, gbc);

        infoPane.setEditable(false);
        infoPane.setFont(new Font("monospaced", Font.PLAIN, 12));
        infoPane.setText("         attack  defense   hp\n" +
                "Warrior    40      20      100\n" +
                "Shaman     30      15      90\n" +
                "Priest     25      25      90\n" +
                "Paladin    40      30      120\n" +
                "Mage       45      10      80\n" +
                "Hunter     25      20      110\n");
        infoPane.setPreferredSize(new Dimension(200, 120));
        infoPane.setMinimumSize(new Dimension(200, 120));
        this.add(infoPane, gbc);

        this.add(createHeroButton, gbc);
        this.setVisible(true);

        Helper.getFrame().setContentPane(this);
        Helper.getFrame().revalidate();
        Helper.showFrame();

        createHeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.onCreateButtonPressed(heroNameField.getText(), (String) classesComboBox.getSelectedItem());
            }
        });
    }

    @Override
    public void getUserInput() {
    }

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(Helper.getFrame(), message);
    }

    @Override
    public void openGame() {
        this.setVisible(false);
        new GameViewGui().start();
    }
}