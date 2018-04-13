
public class DistrictMetricResult extends MetricResult{

    public DistrictMetricResult(District district, boolean stateLevelDetails){
        super();
        addField(DISTRICT_NUM, district.getDistrictNumber());
        if(stateLevelDetails){
            addField(MAP_YEAR, district.getYear());
            addField(STATE_FIPS, district.getState().getId());
            addField(STATE_NAME, district.getState().getName());
        }
    }

    public DistrictMetricResult(District district) {
        this(district, false);
    }

    /** Constructor for empty district result (error occurred ) */
    public DistrictMetricResult(MetricException e, Integer districtNum) {
        super(e);
        addField(DISTRICT_NUM, districtNum);
    }

    public String getStateName() { return (String) getValue(STATE_NAME); }

    public int getDistrictNum() { return (int) getValue(DISTRICT_NUM); }
}
