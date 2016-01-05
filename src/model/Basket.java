package model;

import java.util.ArrayList;

/**
 * Created by Richard on 05.01.2016.
 */
public class Basket
{
    private ArrayList<Stock> stocks;
    private Integer days;
    private Double computedMu;
    private Double computedSigma;
    private Double startValue;

    public Basket()
    {
        days = 120;
        startValue = 0.;
        stocks = new ArrayList<>();
    }

    private void computeMuSigmaStart()
    {
        computedMu = 0.;
        computedSigma = 0.;
        for (Stock st : stocks)
        {
            Double weight = st.getStartValue()/startValue; //number between 0 and 1
            computedMu += st.getMu()*weight;
            computedSigma += Math.pow(st.getSigma(),2)*Math.pow(weight,2);
        }
        computedSigma = Math.sqrt(computedSigma);
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
}
