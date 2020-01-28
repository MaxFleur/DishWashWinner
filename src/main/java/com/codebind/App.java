/**
 * DishWash App, to determine a person doing dishwashes and store so nobody gets selected twice in a row
 * Code by MaxFleur
 * https://github.com/MaxFleur
 */

package com.codebind;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.codebind.EatingPersonsHandler;

/**
 * This class handles the GUI buildup and runs the main app.
 */
public class App extends JFrame {
    // background of the gui
    private JPanel background;

    private JTextField tfAddEatingPerson;

    private JScrollPane sPEatingPersons;
    private JScrollPane sPStoredDishwashers;

    private EatingPersonsHandler m_ePH ;

    /**
     * Constructor, handles the GUI building
     */
    public App() {

        m_ePH = new EatingPersonsHandler();
        // Build background and set the app to a fixed size
        setTitle("Abwaschbestimmer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 300, 470, 540);
        setResizable(false);
        background = new JPanel();
        background.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(background);
        background.setLayout(null);

        // label to add a name of a person to the list of eaters
        JLabel lblAddName = new JLabel("Namen eingeben:");
        lblAddName.setBounds(15, 15, 150, 15);
        background.add(lblAddName);

        // Field to add a person
        tfAddEatingPerson = new JTextField();
        tfAddEatingPerson.setBounds(15, 35, 150,30);
        // Option to add another person by typing a text and hitting Enter
        tfAddEatingPerson.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                m_ePH.addPerson(tfAddEatingPerson.getText());
                tfAddEatingPerson.requestFocus();
                // Reset text after adding
                tfAddEatingPerson.setText("");
            }
        });
        background.add(tfAddEatingPerson);
        tfAddEatingPerson.setColumns(20);

        // Trigger person adding
        JButton btnAddName = new JButton("Hinzufügen");
        btnAddName.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                m_ePH.addPerson(tfAddEatingPerson.getText());
                tfAddEatingPerson.requestFocus();
                // Rest text after adding
                tfAddEatingPerson.setText("");
            }
        });
        btnAddName.setBounds(305, 35, 100, 30);
        background.add(btnAddName);

        // label of all eating persons
        JLabel lblEatingPersons = new JLabel("Essende Supporter:");
        lblEatingPersons.setBounds(15, 90, 150, 15);
        background.add(lblEatingPersons);

        // This scroll pane shows all eating persons
        sPEatingPersons = new JScrollPane();
        sPEatingPersons.setBounds(15, 110, 200, 300);
        background.add(sPEatingPersons);

        JList<String> listEatingPersons = new JList<String>();
        listEatingPersons.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sPEatingPersons.setViewportView(listEatingPersons);

        // Delete the name of a single eating person
        JButton btnDeleteSingleEatingPerson = new JButton("Einzelne Person löschen");
        btnDeleteSingleEatingPerson.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                m_ePH.removePerson(listEatingPersons.getSelectedIndex());
                tfAddEatingPerson.requestFocus();
            }
        });
        btnDeleteSingleEatingPerson.setBounds(25, 415, 180, 30);
        background.add(btnDeleteSingleEatingPerson);

        // Label of the persons who already did the dishwashing
        JLabel lblStoredDishWashers = new JLabel("War schon mal dran:");
        lblStoredDishWashers.setBounds(255, 90, 150, 15);
        background.add(lblStoredDishWashers);

        // This scroll pane shows all persons who dishwashed already
        sPStoredDishwashers = new JScrollPane();
        sPStoredDishwashers.setBounds(255, 110, 200, 300);
        background.add(sPStoredDishwashers);

        // Delete the list of the oast dishwashers
        JButton btnDeleteAllStored = new JButton("Liste leeren");
        btnDeleteAllStored.setBounds(305, 415, 100, 30);
        background.add(btnDeleteAllStored);

        // Determine the next person to do the dishes
        JButton btnCrownWinner = new JButton("Gewinner bestimmen");
        btnCrownWinner.setBounds(150, 460, 170, 30);
        background.add(btnCrownWinner);

        // Set the scroll pane of the eating persons to the list of EatingPersonsHandler
        listEatingPersons.setModel(m_ePH.getEatingPersons());
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