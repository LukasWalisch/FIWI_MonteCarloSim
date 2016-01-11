package model;



import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Igor on 05.01.2016.
 */
public class MonteCarlo {

    private Basket basket;
    private ArrayList[] arrayLists;
    private double min;
    private double max;
    private JPanel root;

    public MonteCarlo(Basket basket, JPanel root){
        this.root = root;
        this.basket=basket;
        arrayLists= new ArrayList[basket.getPaths()];
        for (int i=0;i<basket.getPaths();i++){
            arrayLists[i]=new ArrayList<Double>();
        }
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public ArrayList[] getArrayLists() {
        return arrayLists;
    }

    public void setArrayLists(ArrayList[] arrayLists) {
        this.arrayLists = arrayLists;
    }

    public CategoryDataset alg(){
        for (ArrayList<Double> arr:arrayLists) {
                arr.add(basket.getStartValue());
                Double dt = 1.0 / basket.getDays();
                Double drift = (basket.getComputedMu() - basket.getComputedSigma() * basket.getComputedSigma() / 2.0) * dt;
            Double vsqrt = basket.getComputedSigma() * Math.sqrt(dt);
            for (int i=0;i<basket.getDays();i++) {
                Random random = new Random();
                arr.add(arr.get(i)*Math.exp(drift+vsqrt*random.nextGaussian()));
            }
        }
        findmax();
        findmin();
        DefaultCategoryDataset dataset= new DefaultCategoryDataset();
        int j=0;
        for (ArrayList<Double> arr:arrayLists) {

            for (int i=0;i<arr.size();i++)
                dataset.addValue(arr.get(i), Integer.toString(j+1),Integer.toString(i+1));
            j++;
        }
        computeValue();
        return dataset;
    }

    private void computeValue()
    {
        ArrayList<Double> computedList = new ArrayList<>();
        ArrayList<Double> list = lastvalues();
        Double expectedValue = 0.;
        //Call = Summe - StrikePrice
        //Put = StrikePrice - Summe
        for (Double d : list)
        {
            Double computedDouble = 0.;
            if (basket.isItsACall())
            {
                computedDouble = d - basket.getStrikePrice();
            }else
            {
                computedDouble = basket.getStrikePrice() - d;
            }
            if (computedDouble < 0) computedDouble = 0.;
            computedList.add(computedDouble);
        }

        for (int i = 0; i < computedList.size();i++)
        {
            expectedValue += computedList.get(i);
        }
        expectedValue /= computedList.size();
        basket.setExpectedValue(expectedValue);
    }

    private void findmax(){
        max=Double.MIN_VALUE;
        for (ArrayList<Double> arr:arrayLists) {
            for (int i=0;i<arr.size();i++)
                if (arr.get(i)>max)
                    max=arr.get(i);
        }

    }

    private void findmin(){
        min =Double.MAX_VALUE;
        for (ArrayList<Double> arr:arrayLists) {
            for (int i=0;i<arr.size();i++)
                if (arr.get(i)<min)
                    min=arr.get(i);
        }
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public ArrayList<Double> lastvalues(){
        ArrayList<Double> ans = new ArrayList<>();
        for (ArrayList<Double> arr:arrayLists) {
            ans.add(arr.get(basket.getDays()));
        }
        return ans;
    }

}
