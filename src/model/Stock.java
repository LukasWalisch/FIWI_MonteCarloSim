package model;

/**
 * Created by lukas on 05.01.2016.
 */
public class Stock {
    private double price;
    private double mu;
    private double sigma;
    private int amount;
    private double startValue;
    private double weight;
    public Stock(){}

    public void setAmountComputeStartValue(int amount)
    {
        this.amount = amount;
        startValue = this.amount * price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getMu() {
        return mu;
    }

    public void setMu(double mu) {
        this.mu = mu;
    }

    public double getSigma() {
        return sigma;
    }

    public void setSigma(double sigma) {
        this.sigma = sigma;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getStartValue() {
        return startValue;
    }

    public void setStartValue(double startValue) {
        this.startValue = startValue;
    }

}
