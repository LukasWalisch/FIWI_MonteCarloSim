package model;



import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Igor on 05.01.2016.
 */
public class MonteCarlo {

    private Basket basket;
    private ArrayList[] arrayLists;


    public MonteCarlo(Basket basket){
        this.basket=basket;
        arrayLists= new ArrayList[1000];
        for (int i=0;i<1000;i++){
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
        DefaultCategoryDataset dataset= new DefaultCategoryDataset();
        int j=0;
        for (ArrayList<Double> arr:arrayLists) {

            for (int i=0;i<arr.size();i++)
                dataset.addValue(arr.get(i), Integer.toString(j+1),Integer.toString(i+1));
            j++;
        }

        return dataset;
    }







}