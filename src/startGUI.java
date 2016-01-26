import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import model.Basket;
import model.MonteCarlo;
import model.Stock;
import org.jfree.data.category.CategoryDataset;

import javax.swing.*;
import java.awt.event.*;


public class startGUI extends JFrame {


    private JButton button1;
    private JPanel root;
    private JButton berechnenButton;
    private JRadioButton callRadi;
    private JRadioButton putRadi;
    private JTextField daysInput;
    private JTextField pathsInput;
    private JTextPane metaData;
    private JTextPane addedStocks;
    private JButton setStrike;
    private JTextPane value;
    private Basket basket;

    /**
     *      Lists all the added Stocks in the addedStocks JTextPane.
     */
    private void showStocks()
    {
        String list = "";
        for (Stock s : basket.getStocks())
        {
            String stockName = s.getName();
            stockName = stockName.substring(0,stockName.length()-4);
            list = list + stockName + '\n';
        }
        addedStocks.setText(list);
    }

    public startGUI(){

        setContentPane(root);
        pack();
        basket = new Basket(root);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpenFile openFile = new OpenFile();
                Stock ans=openFile.importStockFromCSV();
                String amountString = (String)JOptionPane.showInputDialog(root,"StockAmount",0);
                ans.setAmountComputeStartValue(Integer.parseInt(amountString));
                basket.addStock(ans);
                String metaDataString = new String("Current µ: " + basket.getComputedMu() + "\nCurrent Sigma: " + basket.getComputedSigma() + "\nCurrent StartValue: " + basket.getStartValue());
                metaData.setText(metaDataString);
                showStocks();
                //label.setText(String.valueOf(ans));
            }

        });
        setVisible(true);
        berechnenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Checks if everything is set properly.
                if (daysInput.getText().equals("") || pathsInput.getText().equals("")) return;
                if (!callRadi.isSelected() && !putRadi.isSelected()) {return;}
                if (callRadi.isSelected() && putRadi.isSelected()) {return;}
                if (basket.getStrikePrice() < 0) return;
                try
                {
                    //Sets the BasketData for the MonteCarlo Simulation.
                    basket.setDays(Integer.parseInt(daysInput.getText()));
                    basket.setPaths(Integer.parseInt(pathsInput.getText()));
                    if (callRadi.isSelected()) basket.setItsACall(true);
                    if (putRadi.isSelected()) basket.setItsACall(false);
                }catch(Exception r){System.out.println("wrong format");return;}
                if (basket.getStocks().size() > 0)
                {
                    MonteCarlo monteCarlo = new MonteCarlo(basket,root);
                    CategoryDataset dataset = monteCarlo.alg(); //Durchführen der MonteCarlo Analyse.
                    double min = monteCarlo.getMin();
                    double max = monteCarlo.getMax();
                    value.setText(basket.getExpectedValue().toString()); //Get Data for the graphic.
                    LineChart lineChart = new LineChart("Monte Carlo Simulation", dataset, min, max);
                    Thread thread = new Thread(lineChart);
                    thread.start(); //Start Graphic in new Thread so the JPanel wont be overwritten.
                }
            }
        });


        setStrike.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try
                {
                    String strikePrice = (String)JOptionPane.showInputDialog(root,"Set StrikePrice: ",0);
                    basket.setStrikePrice(Double.parseDouble(strikePrice));
                }catch (Exception f){return;}

            }
        });
    }

}
