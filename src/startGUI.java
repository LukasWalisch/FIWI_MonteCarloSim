import model.Stock;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class startGUI {

    public Stock importStockFromCSV() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory())
                    return true;
                else if (f.getName().endsWith(".csv"))
                    return true;
                else
                    return false;
            }

            @Override
            public String getDescription() {
                return "*.csv";
            }
        });
        try {
            fileChooser.showOpenDialog(null);
            File file = fileChooser.getSelectedFile();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            reader.readLine(); //to skip the first line
            Stock stock = new Stock();
            String line = "";
            while ((line = reader.readLine()) != null) {
                Double rendite = Double.parseDouble(line.split(",")[6]);
                stock.getRenditenList().add(rendite);
            }
            return stock;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
