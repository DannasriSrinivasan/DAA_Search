package search;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class ChartGeneration extends JFrame {

	public ChartGeneration(ArrayList<GraphArray> finalOp) {
		super("XY Line Chart");
		
		JPanel chartPanel = createChartPanel(finalOp);
		add(chartPanel, BorderLayout.CENTER);
		
		setSize(750, 640);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	private JPanel createChartPanel(ArrayList<GraphArray> finalOp) {
		String chartTitle = "Search Algorithm Comparison";
		String xAxisLabel = "INPUT SIZE";
		String yAxisLabel = "TIME (SEC)";
		
		XYDataset dataset = createDataset(finalOp);
		
		JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, 
				xAxisLabel, yAxisLabel, dataset);
		
		customizeChart(chart);
		
		// saves the chart as an image files
		File imageFile = new File("SearchAlgoComparison.png");
		int width = 750;
		int height = 640;
		
		try {
			ChartUtilities.saveChartAsPNG(imageFile, chart, width, height);
		} catch (IOException ex) {
			System.err.println(ex);
		}
		
		return new ChartPanel(chart);
	}

	private XYDataset createDataset(ArrayList<GraphArray> finalOp) {
		XYSeriesCollection dataset = new XYSeriesCollection();
		XYSeries series1 = new XYSeries("Linear Search");
		XYSeries series2 = new XYSeries("Binary Search");
		XYSeries series3 = new XYSeries("Binary Search Tree");
		XYSeries series4 = new XYSeries("Red Black tree");
		for(GraphArray ga: finalOp) {
			if(ga.getAlgoValue().equalsIgnoreCase("linear")) {
				series1.add(ga.getSize(), ga.getSeconds());
				//series1.add(4.2, 6.0);
			}else if(ga.getAlgoValue().equalsIgnoreCase("binary")) {
				series2.add(ga.getSize(), ga.getSeconds());
			}else if(ga.getAlgoValue().equalsIgnoreCase("bst")) {
				series3.add(ga.getSize(), ga.getSeconds());
			}else {
				series4.add(ga.getSize(), ga.getSeconds());
			}
			
		}
		
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		dataset.addSeries(series3);
		dataset.addSeries(series4);
		
		return dataset;
	}
	
	private void customizeChart(JFreeChart chart) {
		XYPlot plot = chart.getXYPlot();
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

		// sets paint color for each series
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesPaint(1, Color.GREEN);
		renderer.setSeriesPaint(2, Color.YELLOW);
		renderer.setSeriesPaint(3, Color.BLUE);

		// sets thickness for series (using strokes)
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));
		renderer.setSeriesStroke(1, new BasicStroke(2.0f));
		renderer.setSeriesStroke(2, new BasicStroke(2.0f));
		renderer.setSeriesStroke(3, new BasicStroke(2.0f));
		
		// sets renderer for lines
		plot.setRenderer(renderer);
		
		// sets plot background
		plot.setBackgroundPaint(Color.WHITE);
		
		// sets paint color for the grid lines
		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.WHITE);
		
		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.WHITE);
		
	}
}