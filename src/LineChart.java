import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.ui.ApplicationFrame;

/**
 * Created by lukas on 05.01.2016.
 */
public class LineChart extends ApplicationFrame {
    public LineChart(String title, CategoryDataset dataset) {
        super(title);
        JFreeChart chart = ChartFactory.createLineChart("Test", "Tage", "Aktienwert", dataset, PlotOrientation.VERTICAL, false, false, false);
        ChartPanel panel = new ChartPanel(chart);
        this.setContentPane(panel);
    }
}
