
public abstract class CompactnessMetric extends Metric {

   /**
     * Method is used to calculate Compatness of the states
     * @param State object. Please find the skeleton for the state object class.
     * @param Year 
     * @return the result value. This is a state metric object with appropriate score for each district
     */
    protected StateMetricResult calculate(State state, Year year) {
        List<District> districts = state.getDistricts(year);
        if (districts.isEmpty()) {
            throw new DataNotFoundException();
        } else if (districts.size() == 1) {
            throw new NotEnoughDistrictsException(MetricExceptionMessages.NOT_ENOUGH_DISTRICTS);
        }
        // Map District number -> Compactness score.
        List<DistrictMetricResult> districtResults = new ArrayList<>();
        for (District d : districts) {
            DistrictMetricResult districtResult = calculate(d, year);
            districtResults.add(districtResult);
        }
        OptionalDouble averageScore = districtResults
                .stream()
                .mapToDouble(r -> (Double) r.getScore())
                .average();
        StateMetricResult result = new StateMetricResult(state, year);
        result.setScore(averageScore.getAsDouble());
        result.setDistrictResults(districtResults);
        return result;
    }

    /**
	 * @param district object. Please find the skeleton for the district object class.
     * @param Year 
     * @return MetricResult object containing SCORE field and value for the district.
     */
    protected DistrictMetricResult calculate(District district, Year year){
        double districtArea = district.getArea();
        double districtPerimeter = district.getPerimeter();
        double score = calculate(districtArea, districtPerimeter);
        DistrictMetricResult result = new DistrictMetricResult(district);
        result.setScore(score);
        return result;
    }

    public abstract Double calculate(double area, double perimeter);

}
