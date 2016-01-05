import model.Basket;
import model.MonteCarlo;

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

    }
}

