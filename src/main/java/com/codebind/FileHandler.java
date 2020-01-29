package com.codebind;

import javax.swing.DefaultListModel;
import java.io.*;

public class FileHandler {

    private String fileName;
    private DefaultListModel<String> storedNames;

    public FileHandler() throws IOException {
        fileName = "." + File.separator + "stored.dat";
        storedNames = new DefaultListModel<String>();

        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        } else {
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

    public void writeStoredNamesToDisc() throws IOException {
        if(storedNames.size() > 0) {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
            for (int i = 0; i < storedNames.size(); i++) {
                out.write(storedNames.get(i).toString());
                out.newLine();
            }
            out.close();
        }
    }

    public void deleteNamesFromFile() throws IOException {
        storedNames.clear();
        BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
        out.write("");
        out.close();
    }

    public DefaultListModel<String> getStoredNames() {
        return storedNames;
    }
}
