
public class HistoricalCongressionalElection extends HistoricalElectionMetric {
    protected IElection getElection(PoliticalBoundary entity, Year year) {
        return entity.getCongressionalElection(year);
    }

    protected Year getDistrictYear(State state, Year year) {
        // Mapping District -> Congressional Election is already standardized.
        return year;
    }
}
