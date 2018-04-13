
public class SocioeconomicDemographics extends DemographicMetric {
     /**
     * Method is used to calculate SocioeconomicDemographics of the states
     * @param State object. Please find the skeleton for the state object class.
     * @param Year 
     * @return the result value. This is a state metric object with appropriate score for each district
     */
    protected StateMetricResult calculate(State state, Year year) {
        Demographics stateDemographics = state.getDemographics(year);
        List<District> districts = state.getDistricts(year);
        if (stateDemographics == null || districts.isEmpty())
            throw new DataNotFoundException(state, year);
        StateMetricResult result = new StateMetricResult(state, year);
        List<DistrictMetricResult> districtResults = new ArrayList<>();
        for (District district : districts) {
            districtResults.add(calculate(district, year));
        }
        result.setDistrictResults(districtResults);
        result.setScore(stateDemographics.getMeanIncome());
        return result;
    }

    protected DistrictMetricResult calculate(District district, Year year) {
        Demographics districtDemographics = district.getDemographics(year);
        if (districtDemographics == null)
            throw new DataNotFoundException(district, year);
        DistrictMetricResult result = new DistrictMetricResult(district);
        result.setScore(districtDemographics.getMeanIncome());
        return result;
    }
}
