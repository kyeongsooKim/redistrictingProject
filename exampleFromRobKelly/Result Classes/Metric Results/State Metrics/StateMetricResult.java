
public class StateMetricResult extends MetricResult{

    public StateMetricResult(State state, Year year) {
        super();
        addField(STATE_NAME, state.getName());
        addField(STATE_FIPS, state.getId());
        if(year != null)
            addField(MAP_YEAR, year.getValue());
    }

    public StateMetricResult(MetricException e, String name) {
        super(e);
        addField(STATE_NAME, name);
    }

    public void setDistrictResults(List<DistrictMetricResult> results) {
        addField(DISTRICTS, results);
    }

    public List<DistrictMetricResult> getDistrictResults() { return (List<DistrictMetricResult>) getValue(DISTRICTS); }

    public String getStateName() { return (String) getValue(STATE_NAME); }

    public void removeDistrictResults(){
        removeField(DISTRICTS);
    }

}
