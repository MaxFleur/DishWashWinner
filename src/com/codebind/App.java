package com.codebind;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {

    private JPanel background;
    private JTextField tfAddEatingPersons;
    private JTextField tfEatingPersons;
    private JTextField tfStoredDishwashers;

    private JButton addName;

    public App() {
        setTitle("Abwaschbestimmer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 300, 470, 540);
        setResizable(false);
        background = new JPanel();
        background.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(background);
        background.setLayout(null);

        JLabel lblAddName = new JLabel("Namen eingeben:");
        lblAddName.setBounds(15, 15, 150, 15);
        background.add(lblAddName);

        tfAddEatingPersons = new JTextField();
        tfAddEatingPersons.setBounds(15, 35, 150,30);
        background.add(tfAddEatingPersons);
        tfAddEatingPersons.setColumns(20);

        JButton btnAddName = new JButton("Hinzufügen");
        btnAddName.setBounds(305, 35, 100, 30);
        background.add(btnAddName);

        JLabel lblEatingPersons = new JLabel("Essende Supporter:");
        lblEatingPersons.setBounds(15, 90, 150, 15);
        background.add(lblEatingPersons);

        tfEatingPersons = new JTextField();
        tfEatingPersons.setBounds(15, 110, 200, 300);
        background.add(tfEatingPersons);
        tfEatingPersons.setColumns(20);

        JButton btnDeleteSingleEatingPerson = new JButton("Einzelne Person löschen");
        btnDeleteSingleEatingPerson.setBounds(25, 415, 180, 30);
        background.add(btnDeleteSingleEatingPerson);

        JLabel lblStoredDishWashers = new JLabel("War schon mal dran:");
        lblStoredDishWashers.setBounds(255, 90, 150, 15);
        background.add(lblStoredDishWashers);
        
        tfStoredDishwashers = new JTextField();
        tfStoredDishwashers.setBounds(255, 110, 200, 300);
        background.add(tfStoredDishwashers);
        tfEatingPersons.setColumns(20);

        JButton btnDeleteAllStored = new JButton("Liste leeren");
        btnDeleteAllStored.setBounds(305, 415, 100, 30);
        background.add(btnDeleteAllStored);

        JButton btnCrownWinner = new JButton("Gewinner bestimmen");
        btnCrownWinner.setBounds(150, 460, 170, 30);
        background.add(btnCrownWinner);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    App m_app = new App();
                    m_app.setVisible(true);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}