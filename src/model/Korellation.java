package model;

/**
 * Created by Richard on 07.01.2016.
 */
public class Korellation
{
    Stock stockA;
    Stock stockB;
    float corellation;

    public Korellation (Stock a, Stock b, float core)
    {
        stockA = a;
        stockB = b;
        corellation = core;
    }

    public float getCorellation() {
        return corellation;
    }

    public Stock getStockA() {
        return stockA;
    }

    public void setStockA(Stock stockA) {
        this.stockA = stockA;
    }

    public void setCorellation(float corellation) {
        this.corellation = corellation;
    }

    public Stock getStockB() {
        return stockB;
    }

    public void setStockB(Stock stockB) {
        this.stockB = stockB;
    }
}
