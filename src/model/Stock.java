package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukas on 05.01.2016.
 */
public class Stock {
    private double price;
    private double mu;
    private double sigma;

    public Stock(){}

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
}
