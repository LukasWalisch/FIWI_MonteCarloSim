import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.ApplicationFrame;

/**
 * Created by lukas on 05.01.2016.
 */
public class LineChart extends ApplicationFrame implements Runnable {
    public LineChart(String title, CategoryDataset dataset) {
        super(title);
        JFreeChart chart = ChartFactory.createLineChart("Monte Carlo Simulation", "Tage", "Aktienwert", dataset, PlotOrientation.VERTICAL, false, false, false);
        chart.getCategoryPlot().getRangeAxis().setRange(50.0,200.0); //TODO Max und Min Wert einsetzen
        ChartPanel panel = new ChartPanel(chart);
        this.setContentPane(panel);
    }

    @Override
    public void run() {
        this.pack();
        this.setVisible(true);
    }
}
