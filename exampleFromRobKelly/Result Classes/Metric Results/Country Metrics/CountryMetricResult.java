
public class CountryMetricResult extends MetricResult{

    public CountryMetricResult() {
        super();
    }

    public void setStateResults(List<StateMetricResult> results){
        /* Default behavior when setting state results is to remove district level details */
        setStateResults(results, false);
    }

    public void setStateResults(List<StateMetricResult> results, boolean districtDetails) {
        if(!districtDetails){
            results.forEach(x -> x.removeDistrictResults());
        }
        addField(STATES, results);
    }

    public List<StateMetricResult> getStates() { return (List<StateMetricResult>) getValue(STATES); }

}
