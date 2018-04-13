
public class PolsbyPopper extends CompactnessMetric {
  /**
	 * @param area,perimeter
     * @return score 
     */
    public Double calculate(double area, double perimeter) {
        // Formula: (4Pi * area) / (Perimeter^2)
        double polsbyPopperScore = (4 * Math.PI * area) / Math.pow(perimeter, 2);
        return polsbyPopperScore;
    }
}
