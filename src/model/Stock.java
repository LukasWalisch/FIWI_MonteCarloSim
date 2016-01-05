package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lukas on 05.01.2016.
 */
public class Stock {
    private List<Double> renditenList;

    public Stock(){
        renditenList = new ArrayList<Double>();
    }

    public Stock(List<Double> renditenList) {
        this.renditenList = renditenList;
    }

    public List<Double> getRenditenList() {
        return renditenList;
    }

    public void setRenditenList(List<Double> renditenList) {
        this.renditenList = renditenList;
    }
}
