import model.Stock;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;


public class startGUI {

    public Stock importStockFromCSV() {

        // open csv file
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

            // read last column from csv file
            BufferedReader reader = new BufferedReader(new FileReader(file));
            reader.readLine(); //to skip the first line
            reader.readLine(); //to skip the second line
            String line = "";

            // get adjClose from CSV

            ArrayList<Double> adjCloseList = new ArrayList<Double>();
            while ((line = reader.readLine()) != null) {
                Double adjClose = Double.parseDouble(line.split(",")[6]);
                adjCloseList.add(adjClose);
            }

            //calculate rendites and mu
            double sum = 0;
            double rendite = 0;
            ArrayList<Double> renditenList = new ArrayList<Double>();
            for (int i = 1; i < adjCloseList.size(); ++i)
            {
                rendite =(adjCloseList.get(i)/adjCloseList.get(i-1))-1;
                renditenList.add(rendite);
                sum = sum + rendite;
            }

            Stock stock = new Stock();
            double mu = sum/renditenList.size();
            stock.setMu(mu);

            //calculate sigma
            double variance = 0;
            for(double a : renditenList){
                variance = variance + (mu-a)*(a-mu);
            }
            variance = variance/adjCloseList.size();
            double sigma = Math.sqrt(variance);
            stock.setSigma(sigma);

            //price is the last adjClose value
            stock.setPrice(adjCloseList.get(adjCloseList.size()-1));

            return stock;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
