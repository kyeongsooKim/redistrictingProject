
public class RaceDemographics extends DemographicMetric {

  /**
     * Method is used to calculate RaceDemographics metric of the states
     * @param State object. Please find the skeleton for the state object class.
     * @param Year 
     * @return the result value. This is a state metric object with appropriate score for each district
     */
    protected StateMetricResult calculate(State state, Year year) {
        Demographics stateDemographics = state.getDemographics(year);
        List<District> districts = state.getDistricts(year);
        if (stateDemographics == null || districts.isEmpty()) {
            throw new DataNotFoundException(state, year);
        }
        StateMetricResult result = new StateMetricResult(state, year);
        List<DistrictMetricResult> districtResults = new ArrayList<>();
        for (District district : districts) {
            districtResults.add(calculate(district, year));
        }
        result.setDistrictResults(districtResults);
        for (Race race : Race.values()) {
            result.addProperty(race.toString(), state.getDemographics(year).getRaceDemographic(race));
        }
        result.addProperty(POPULATION, stateDemographics.getPopulation());
        return result;
    }
    /**
	 * @param district object. Please find the skeleton for the district object class.
     * @param Year 
     * @return MetricResult object containing SCORE field and value for the district.
     */
    protected DistrictMetricResult calculate(District district, Year year) {
        Demographics demographics = district.getDemographics(year);
        if (demographics == null)
            throw new DataNotFoundException(district, year);
        DistrictMetricResult result = new DistrictMetricResult(district);
        for (Race race : Race.values()) {
            result.addProperty(race.toString(), demographics.getRaceDemographic(race));
        }
        result.addProperty(POPULATION, demographics.getPopulation());
        return result;
    }
}
