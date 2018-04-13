public abstract class HistoricalElectionMetric extends Metric {

    private static final String WINNING_PARTY = "Winning Party";
  /**
     * Method is used to calculate HistoricalElectionMetric metric of the states
     * @param State object. Please find the skeleton for the state object class.
     * @param Year 
     * @return the result value. This is a state metric object with appropriate score for each district
     */
    protected StateMetricResult calculate(State state, Year electionYear) {
        Year districtYear = getDistrictYear(state, electionYear);
        if(districtYear == null) {
            throw new DataNotFoundException(state, electionYear);
        }
        IElection election = getElection(state, districtYear);
        List<District> districts = state.getDistricts(districtYear);
        // TODO : i think this is covered with getDistrictYear
        if (election == null || districts.isEmpty()) {
            throw new DataNotFoundException(state, districtYear);
        }
        StateMetricResult result = new StateMetricResult(state, districtYear);
        List<DistrictMetricResult> districtResults = new ArrayList<>();
        for (District district : districts) {
            districtResults.add(calculate(district, electionYear));
        }
        result.setDistrictResults(districtResults);
        for (Party party : Party.values()) {
            result.addProperty(party.toString(), election.getVotes(party));
        }
        result.setScore(election.getWinMargin());
        result.addProperty(WINNING_PARTY, election.getWinner().toString());
        return result;
    }
    /**
	 * @param district object. Please find the skeleton for the district object class.
     * @param Year 
     * @return MetricResult object containing SCORE field and value for the district.
     */
    protected DistrictMetricResult calculate(District district, Year year) {
        IElection election = getElection(district, year);
        if (election == null) {
            throw new DataNotFoundException(district, year);
        }
        DistrictMetricResult result = new DistrictMetricResult(district);
        for (Party party : Party.values()) {
            result.addProperty(party.toString(), election.getVotes(party));
        }
        result.setScore(election.getWinMargin()); // Score is margin by which majority party won against next highest.
        result.addProperty(WINNING_PARTY, election.getWinner().toString());
        return result;
    }

    protected abstract IElection getElection(PoliticalBoundary entity, Year year);

    /**
     * Presidential and Congressional elections map to district years differently.
     * Find district year that contains election results for the requested year.
     */
    protected abstract Year getDistrictYear(State state, Year year);
}
