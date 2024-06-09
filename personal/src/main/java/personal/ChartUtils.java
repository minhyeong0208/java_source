package personal;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ChartUtils {
    public static String generateBarChart(String filePath) throws IOException {
        // 데이터셋 생성
        double[] xData = new double[] {1.0, 2.0, 3.0};
        double[] yData = new double[] {Math.random() * 10, Math.random() * 10, Math.random() * 10};

        // 차트 생성
        CategoryChart chart = new CategoryChartBuilder()
                .width(400)
                .height(300)
                .title("Sample Bar Chart")
                .xAxisTitle("Category")
                .yAxisTitle("Value")
                .build();

        // Series 추가
        chart.addSeries("Series1", xData, yData);

        // 차트를 바이트 배열로 변환
        byte[] imageBytes = BitmapEncoder.getBitmapBytes(chart, BitmapFormat.PNG);

        // 바이트 배열을 파일로 저장
        try (FileOutputStream fos = new FileOutputStream(new File(filePath))) {
            fos.write(imageBytes);
        }

        return filePath;
    }
}
