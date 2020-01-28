package com.codebind;

import javax.swing.DefaultListModel;
import java.util.Random;

public class Randomizer {

    private DefaultListModel<String> eatingPersons;
    private DefaultListModel<String> storedWashers;

    private String winner;

    public Randomizer() {
        eatingPersons = new DefaultListModel<String>();
        storedWashers = new DefaultListModel<String>();
        winner = new String();
    }

    public void determineWinner() {
        if(eatingPersons.size() > 0) {

            DefaultListModel<String> candidates = new DefaultListModel<String>();
            for (int i = 0; i < eatingPersons.size(); i++) {
                if (!storedWashers.contains(eatingPersons.elementAt(i))) {
                    candidates.addElement(eatingPersons.elementAt(i));
                }
            }
            if(candidates.size() > 0) {
                Random r = new Random();
                winner = candidates.get(r.nextInt(candidates.size()));
            }
        }
    }
    
    public void addWinnerToStored(String name) {
        storedWashers.addElement(name);
    }

    public void clearStoredWashers() {
        storedWashers.clear();
    }

    public DefaultListModel<String> getEatingPersons() {
        return eatingPersons;
    }

    public DefaultListModel<String> getStoredWashers() {
        return storedWashers;
    }

    public String getWinner() {
        return winner;
    }

    public void setEatingPersons(DefaultListModel<String> eatingPersons) {
        this.eatingPersons = eatingPersons;
    }
}
