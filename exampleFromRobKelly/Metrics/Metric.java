
public abstract class Metric implements BiFunction<PoliticalBoundary, Year, MetricResult> {

    public MetricResult apply(PoliticalBoundary politicalBoundary, Year year) {
        return this.calculateMetric(politicalBoundary, year);
    }


    protected MetricResult calculateMetric(PoliticalBoundary entity, Year year) {
        if (entity instanceof Country) {
            return calculate((Country) entity, year);
        } else if (entity instanceof State) {
            return calculate((State) entity, year);
        } else if (entity instanceof District) {
            return calculate((District) entity, year);
        }
        return null;
    }

    protected CountryMetricResult calculate(Country country, Year year) {
        CountryMetricResult result = new CountryMetricResult();
        List<StateMetricResult> stateResults = new ArrayList<>();
        for (State s : country.getStates()) {
            StateMetricResult stateResult;
            try {
                stateResult = calculate(s, year);
            } catch (MetricException ex) {
                stateResult = new StateMetricResult(s, year);
                stateResult.setError(ex.getMessage());
            }
            stateResults.add(stateResult);
        }
        result.setStateResults(stateResults);
        return result;
    }

    protected abstract StateMetricResult calculate(State state, Year year);

    protected abstract DistrictMetricResult calculate(District district, Year year);

    public String toString() {
        return this.getClass().getName();
    }

}
