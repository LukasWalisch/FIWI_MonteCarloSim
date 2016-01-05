import model.Basket;
import model.Stock;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class startGUI extends JFrame {


    private JButton button1;
    private JPanel root;
    private JButton berechnenButton;
    private JButton checkStock;
    private JList list1;
    private Basket basket = new Basket();
    public startGUI(){
        setContentPane(root);
        pack();
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpenFile openFile = new OpenFile();
                Stock ans=openFile.importStockFromCSV();
                System.out.println(ans.getMu() + " " + ans.getSigma() + " " + ans.getPrice());
                String amountString = (String)JOptionPane.showInputDialog(root,"StockAmount",0);
                ans.setAmountComputeStartValue(Integer.parseInt(amountString));
                basket.addStock(ans);
                System.out.println(ans.getAmount() + " " + ans.getStartValue());
                System.out.println(basket.getComputedMu() + " " + basket.getComputedSigma() + " " + basket.getStartValue());
                //label.setText(String.valueOf(ans));
            }

        });
        setVisible(true);
        berechnenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (basket.getStocks().size()>0){

                }
            }
        });
        checkStock.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
        });
    }

}
