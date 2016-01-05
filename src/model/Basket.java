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

    }
}
