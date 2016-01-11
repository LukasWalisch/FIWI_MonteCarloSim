package model;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Richard on 05.01.2016.
 */
public class Basket
{
    private ArrayList<Stock> stocks;
    private Integer days;
    private boolean itsACall;
    private Integer paths;
    private Double computedMu;
    private Double computedSigma;
    private Double startValue;
    private ArrayList<Korellation> corellations;
    private JPanel root;
    private Double strikePrice;
    private Double expectedValue;

    public Basket(JPanel root)
    {
        strikePrice = -1.;
        this.root = root;
        days = 120;
        startValue = 0.;
        stocks = new ArrayList<>();
        corellations = new ArrayList<>();
    }

    private void computeMuSigmaStart()
    {
        computedMu = 0.;
        computedSigma = 0.;
        for (Stock st : stocks)
        {
            Double weight = st.getStartValue()/startValue; //number between 0 and 1
            st.setWeight(weight);
            computedMu += st.getMu()*weight;
            computedSigma += Math.pow(st.getSigma(),2)*Math.pow(weight,2);
        }
        if (!corellations.isEmpty())
        {
            for (Korellation st : corellations)
            {
                computedSigma += 2*st.getStockA().getWeight()*st.getStockB().getWeight()*st.getStockA().getSigma()*st.getStockB().getSigma()*st.getCorellation();
            }
        }
        computedSigma = Math.sqrt(computedSigma);
    }

    private void addCore(Stock addy)
    {
        for (Stock s : stocks)
        {
            boolean right = true;
            do
            {
                try {
                    right = true;
                    String corelInput = (String) JOptionPane.showInputDialog(root, "Set Correlation (-1 < x < 1) between " + s.getName() + " and " + addy.getName(), 0);
                    Korellation kore = new Korellation(s, addy, Float.parseFloat(corelInput));
                    if (kore.getCorellation() > 1 || kore.getCorellation() < -1) right = false;
                    corellations.add(kore);
                }catch(Exception e){right = false;}
            }while(!right);
        }
    }


    public void addStock(Stock stock)
    {
        if (stocks.isEmpty())
        {
            stocks.add(stock);
            computedMu = stock.getMu();
            computedSigma = stock.getSigma();
            startValue = stock.getStartValue();
            return;
        }
        if (stocks.size() > 0) addCore(stock);
        stocks.add(stock);
        startValue += stock.getStartValue();
        computeMuSigmaStart();

    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Double getComputedMu() {
        return computedMu;
    }

    public void setComputedMu(Double computedMu) {
        this.computedMu = computedMu;
    }

    public Double getComputedSigma() {
        return computedSigma;
    }

    public void setComputedSigma(Double computedSigma) {
        this.computedSigma = computedSigma;
    }

    public Double getStartValue() {
        return startValue;
    }

    public void setStartValue(Double startValue) {
        this.startValue = startValue;
    }

    public Integer getPaths() {
        return paths;
    }

    public void setPaths(Integer paths) {
        this.paths = paths;
    }

    public JPanel getRoot() {
        return root;
    }

    public void setRoot(JPanel root) {
        this.root = root;
    }

    public ArrayList<Korellation> getCorellations() {
        return corellations;
    }

    public void setCorellations(ArrayList<Korellation> corellations) {
        this.corellations = corellations;
    }

    public boolean isItsACall() {
        return itsACall;
    }

    public void setItsACall(boolean itsACall) {
        this.itsACall = itsACall;
    }

    public Double getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(Double strikePrice) {
        this.strikePrice = strikePrice;
    }

    public Double getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(Double expectedValue) {
        this.expectedValue = expectedValue;
    }
}
