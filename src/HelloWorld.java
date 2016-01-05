import model.Basket;
import model.MonteCarlo;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.ArrayList;

/**
 * Created by lukas on 05.01.2016.
 */
public class HelloWorld {

    public static void main(String[] args) {
        //    startGUI startGUI = new startGUI();
        Basket basket=new Basket();
        basket.setStartValue(1000.0);
        basket.setComputedMu(0.05);
        basket.setComputedSigma(0.15);
        basket.setDays(100);
        MonteCarlo monteCarlo=new MonteCarlo(basket);
        monteCarlo.alg();
        DefaultCategoryDataset dataset= new DefaultCategoryDataset();
        int j=0;
        for (ArrayList<Double> arr:monteCarlo.getArrayLists()) {

            for (int i=0;i<arr.size();i++)
                dataset.addValue(arr.get(i), Integer.toString(j+1),Integer.toString(i+1));
            j++;
        }
        System.out.print("lala");
    }
}

