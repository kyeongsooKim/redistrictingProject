
public class HistoricalPresidentialElection extends HistoricalElectionMetric {


    protected IElection getElection(PoliticalBoundary entity, Year year) {
        return entity.getPresidentialElection(year);
    }

    protected Year getDistrictYear(State state, Year year) {
        for(Year potentialDistrictYear : state.getDistrictDataYears()){
            if(state.getDistricts(potentialDistrictYear).get(0).getPresidentialElection(year) != null)
                return potentialDistrictYear;
        }
        return null;
    }
}
