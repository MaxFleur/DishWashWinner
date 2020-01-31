/**
 * DishWash App, to determine a person doing dishwashes and store so nobody gets selected twice in a row
 * Code by MaxFleur
 * https://github.com/MaxFleur
 */

package com.codebind;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
    private Randomizer m_Rand;
    private FileHandler m_fH;

    /**
     * Constructor, handles the GUI building
     */
    public App() throws IOException {

        m_ePH = new EatingPersonsHandler();
        m_Rand = new Randomizer();
        m_fH = new FileHandler();

        m_Rand.setStoredWashers(m_fH.getStoredNames());

        // Build background and set the app to a fixed size
        setTitle("Abwaschbestimmer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 300, 470, 600);
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

        JList<String> listStoredDishWashers = new JList<String>();
        listStoredDishWashers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sPStoredDishwashers.setViewportView(listStoredDishWashers);

        // Delete the list of the last dishwashers
        JButton btnDeleteAllStored = new JButton("Liste leeren");
        btnDeleteAllStored.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                // Delete from file
                try {
                    m_fH.deleteNamesFromFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnDeleteAllStored.setBounds(305, 415, 100, 30);
        background.add(btnDeleteAllStored);

        // label of the randomization winner, not visibile until winner is determined
        JLabel lblWinner = new JLabel();
        lblWinner.setBounds(50, 510, 200, 20);
        lblWinner.setVisible(false);
        background.add(lblWinner);

        // Button to store the winner, not visible until winner is determined
        JButton btnAddWinnerToStored = new JButton("Speichern / OK");
        btnAddWinnerToStored.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                // Add the winner to the stored ones and write the names to the disc
                m_Rand.addWinnerToStored(m_Rand.getWinner());
                m_fH.setStoredNames(m_Rand.getStoredWashers());

                try {
                    m_fH.writeStoredNamesToFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // Then reset the visibility of the label and button
                lblWinner.setVisible(false);
                btnAddWinnerToStored.setVisible(false);
            }
        });
        btnAddWinnerToStored.setBounds(290, 505, 130, 30);
        btnAddWinnerToStored.setVisible(false);
        background.add(btnAddWinnerToStored);

        // Determine the next person to do the dishes
        JButton btnCrownWinner = new JButton("Gewinner bestimmen");
        btnCrownWinner.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(m_ePH.getEatingPersons().size() > 0) {
                    m_Rand.setEatingPersons(m_ePH.getEatingPersons());
                    m_Rand.determineWinner();
                    // Now that the winner is determined, the button and label shall become visible
                    lblWinner.setText("Heutiger Gewinner: " + m_Rand.getWinner());
                    lblWinner.setVisible(true);
                    btnAddWinnerToStored.setVisible(true);
                }
            }
        });
        btnCrownWinner.setBounds(150, 460, 170, 30);
        background.add(btnCrownWinner);

        // Set the scroll pane of the eating persons to the list of EatingPersonsHandler
        listEatingPersons.setModel(m_ePH.getEatingPersons());
        listStoredDishWashers.setModel(m_fH.getStoredNames());
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