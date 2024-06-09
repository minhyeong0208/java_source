package Personal;

import java.util.function.IntPredicate;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

public class test {
	
	public static void main(String[] args) {
		double[] xData = new double[] {0.0,1.0,2.0};
		double[] yData = new double[] {2.0,1.0,0.0};

		XYChart chart = QuickChart.getChart("Sample Chart", "xData", "yData", "y(x)", xData, yData);
		new SwingWrapper(chart).displayChart();
	}

}