package regression;

import smile.data.DataFrame;
import smile.data.vector.DoubleVector;
import smile.regression.OLS;
import smile.validation.RegressionMetrics;

public class SoccerPerformancePrediction {

    public static void main(String[] args) {
        // 데이터 준비
        double[][] features = {
            {10, 0, 1},  // 예시 데이터: 이전 경기 기록 (득점), 상대팀 강도, 홈 경기 여부
            {7, 1, 0},
            {5, 2, 1},
            // 추가 데이터...
        };

        double[] targets = {1, 0, 2}; // 실제 득점 수

        // OLS(Ordinary Least Squares) 회귀 모델 생성
        OLS ols = OLS.fit(features, targets);

        // 모델 평가
        double[] predicted = ols.predict(features);
        RegressionMetrics metrics = RegressionMetrics.of(targets, predicted);

        System.out.println("R2: " + metrics.r2);
        System.out.println("RMSE: " + metrics.rmse);
    }
}
