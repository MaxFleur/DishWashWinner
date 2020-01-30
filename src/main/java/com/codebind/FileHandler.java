package com.codebind;

import javax.swing.DefaultListModel;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;

public class FileHandler {

    // name of the file, including it's direction
    private String fileName;
    // The names to be stored
    private DefaultListModel<String> storedNames;

    /**
     * Constructor handles file dir and the file content
     * @throws IOException
     */
    public FileHandler() throws IOException {
        // Use File.separator to handle both Linux and Windows
        fileName = "." + File.separator + "stored.dat";
        storedNames = new DefaultListModel<String>();
        // A new File to handle the stored data
        File file = new File(fileName);
        // Create the file if it's not there
        if (!file.exists()) {
            file.createNewFile();
        } else {
            // Read the file content into the list
            String adressRow;
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            while ((adressRow = in.readLine()) != null) {
                storedNames.addElement(adressRow);
            }
            if (in != null) {
                in.close();
            }
        }
    }

    /**
     * Writes stored data back to the file
     * @throws IOException
     */
    public void writeStoredNamesToFile() throws IOException {
        // Check if list is not empty
        if(storedNames.size() > 0) {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            // Write every name onto disc, separate with new line
            for (int i = 0; i < storedNames.size(); i++) {
                out.write(storedNames.get(i).toString());
                out.newLine();
            }
            out.close();
        }
    }

    /**
     * Delete the whole list
     * @throws IOException
     */
    public void deleteNamesFromFile() throws IOException {
        // Empty string and that's it
        storedNames.clear();
        BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
        out.write("");
        out.close();
    }

    /**
     * @return the stored names
     */
    public DefaultListModel<String> getStoredNames() {
        return storedNames;
    }
}
