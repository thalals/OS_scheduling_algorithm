

import java.util.Calendar;
import java.util.Date;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class GanttChart extends ApplicationFrame {

    /**
     * Creates a new demo.
     *
     * @param title  the frame title.
     */
    public GanttChart(final String title) {

        super(title);

        final IntervalCategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(912,585));
        setContentPane(chartPanel);

    }

    private static IntervalCategoryDataset createDataset() {
    	//task.setPercentComplete(1.0D);
    	
        final TaskSeries task = new TaskSeries("P1");
        task.add(new TaskNumeric("CPU Time", 0, 3));
        task.add(new TaskNumeric("ReadyQueue Time", 3, 8));
        task.add(new TaskNumeric("CPU Time", 8, 10));

        final TaskSeries task2 = new TaskSeries("P2");
        task2.add(new TaskNumeric("ReadyQueue Time", 0, 3));
        task2.add(new TaskNumeric("CPU Time", 3, 5));
        task2.add(new TaskNumeric("ReadyQueue Time", 5, 6));
        task2.add(new TaskNumeric("CPU Time", 6, 8));
        
        final TaskSeries task3 = new TaskSeries("P3");
        task3.add(new TaskNumeric("ReadyQueue Time", 0, 5));
        task3.add(new TaskNumeric("CPU Time", 5, 6));
        task3.add(new TaskNumeric("ReadyQueue Time", 6, 10));

        TaskSeriesCollection collection = new TaskSeriesCollection();
        collection.add(task);
        collection.add(task2);
        collection.add(task3);
        return collection;
    }

    private JFreeChart createChart(final IntervalCategoryDataset dataset) {
        final JFreeChart chart = GanttChartFactory.createGanttChart(
            "OS scheduling",  // chart title
            "Process",              // domain axis label
            "Time",              // range axis label
            dataset,             // data
            true,                // include legend
            true,                // tooltips
            false                // urls
        );    
//        chart.getCategoryPlot().getDomainAxis().setMaxCategoryLabelWidthRatio(10.0f);
        return chart;    
    }
    

    public static void main(final String[] args) {

        final GanttChart demo = new GanttChart("OS scheduling");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
        demo.setResizable(false);
    }
    

}
