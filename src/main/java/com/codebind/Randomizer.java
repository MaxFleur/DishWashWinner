/**
 * Get a random winner of a list with eating persons.
 * Code by MaxFleur
 * https://github.com/MaxFleur
 */

package com.codebind;

import javax.swing.DefaultListModel;
import java.util.Random;

/**
 * This class determines a random winner for dishwashes, based on a list with all eating persons
 */
public class Randomizer {

    private DefaultListModel<String> eatingPersons;
    private DefaultListModel<String> storedWashers;

    private String winner;

    public Randomizer() {
        eatingPersons = new DefaultListModel<String>();
        storedWashers = new DefaultListModel<String>();
        winner = new String();
    }

    /**
     * Name dropper... determine the winner :-)
     */
    public void determineWinner() {
        // Don't do anything if there are no eating persons
        if(eatingPersons.size() > 0) {
            // New list with all candidates
            DefaultListModel<String> candidates = new DefaultListModel<String>();
            // If the person hasn't dishwashed yet, add it to the candidates
            for (int i = 0; i < eatingPersons.size(); i++) {
                if (!storedWashers.contains(eatingPersons.elementAt(i))) {
                    candidates.addElement(eatingPersons.elementAt(i));
                }
            }
            Random r = new Random();
            // Now determine a wandom winner out of candidates
            if(candidates.size() > 0) {
                winner = candidates.get(r.nextInt(candidates.size()));
                // If there are no candidates (or if all eating persons dishwashed already)
                // get a random entry of the list with eating persons
            } else {
                winner = eatingPersons.get(r.nextInt(eatingPersons.size()));
            }
        }
    }

    /**
     * Add the winner to the stored list if his or her name is not added yet
     */
    public void addWinnerToStored(String name) {
        if(!storedWashers.contains(name)) {
            storedWashers.addElement(name);
        }
    }



    /**
     * Clear the list of stored washers
     */
    public void clearStoredWashers() {
        storedWashers.clear();
    }

    /**
     * @return eating persons
     */
    public DefaultListModel<String> getEatingPersons() {
        return eatingPersons;
    }

    /**
     * @return stored washers
     */
    public DefaultListModel<String> getStoredWashers() {
        return storedWashers;
    }

    /**
     * @return the winner
     */
    public String getWinner() {
        return winner;
    }

    /**
     * Reset the list of eating persons
     * @param eatingPersons eatingPersons stored in a file
     */
    public void setEatingPersons(DefaultListModel<String> eatingPersons) {
        this.eatingPersons = eatingPersons;
    }
}
