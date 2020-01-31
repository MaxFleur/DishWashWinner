/**
 * Handle all persons eating.
 * Code by MaxFleur
 * https://github.com/MaxFleur
 */

package com.codebind;

import javax.swing.DefaultListModel;

/**
 * This class handles the eating persons.
 */
public class EatingPersonsHandler {

    // List to store all persons
    private DefaultListModel<String> eatingPersons = new DefaultListModel<String>();

    // Add another person, make sure the string is not empty
    public void addPerson(String name) {
        if(!name.isEmpty()) {
            // If the user accidentally uses whitespaces, remove all of them
            // But don't use two or more names, they will be merged otherwise :-)
            eatingPersons.addElement(name.replaceAll("\\s+", ""));
        }
    }

    // Remove a person, make sure there is no false index
    public void removePerson(int index) {
        if(index >= 0) {
            eatingPersons.remove(index);
        }
    }

    // Return the persons
    public DefaultListModel<String> getEatingPersons() {
        return eatingPersons;
    }
}
