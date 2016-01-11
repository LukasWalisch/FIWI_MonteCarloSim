import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import model.Basket;
import model.MonteCarlo;
import model.Stock;
import org.jfree.data.category.CategoryDataset;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


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
                if (daysInput.getText().equals("") || pathsInput.getText().equals("")) return;
                if (!callRadi.isSelected() && !putRadi.isSelected()) {System.out.println("Error in !!");return;}
                if (callRadi.isSelected() && putRadi.isSelected()) {System.out.println("Error in &&");return;}
                try
                {
                    basket.setDays(Integer.parseInt(daysInput.getText()));
                    basket.setPaths(Integer.parseInt(pathsInput.getText()));
                    if (callRadi.isSelected()) basket.setItsACall(true);
                    if (putRadi.isSelected()) basket.setItsACall(false);
                }catch(Exception r){System.out.println("wrong format");return;}
                if (basket.getStocks().size() > 0) {
                    //Hier müssen noch paths, days, knock in, knock out berücksichtigt werden.
                    //Zusätzlich zur Grafik soll auch ein Fenster erscheinen welchen den Mittelwert der Errechneten Werte  (max(wert des Basket;0)) darstellen soll.
                    //Dazu von jedem Pfad den letzten Wert hernehmen, bei einer Call Option (callRadi) diesen MINUS des StartWerts, bei einer Put Option den StartWert minus des letzten wertes.
                    //Sollte der resultierende Wert unter 0 liegen, so wird 0 angenommen. Aus allen Werten den Mittelwert errechnen, dieser stellt den Basketwert dar.
                    //Sollte Knock In oder Knock out eingefügt worden sein, muss berückisichtigt werden, dass  diese Werte bei der Mittelwert berechnung wegfallen.
                    MonteCarlo monteCarlo = new MonteCarlo(basket,root);
                    CategoryDataset dataset = monteCarlo.alg();
                    double min = monteCarlo.getMin();
                    double max = monteCarlo.getMax();
                    value.setText(basket.getExpectedValue().toString());
                    LineChart lineChart = new LineChart("Monte Carlo Simulation", dataset, min, max);
                    Thread thread = new Thread(lineChart);
                    thread.start();
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
