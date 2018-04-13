
public class LopsidedWins extends Metric {

    private static final Logger LOGGER = Logger.getLogger(LopsidedWins.class);
  /**
     * Method is used to calculate LopsidedWins of the states
     * @param State Object, Year
     * @return the score value. 
     */
    protected StateMetricResult calculate(State state, Year year) {
        Collection<Double> demWinPercentages = new ArrayList<>();
        Collection<Double> repWinPercentages = new ArrayList<>();
        List<District> districts = state.getDistricts(year);
        if (districts.isEmpty()) {
            throw new DataNotFoundException(state);
        }
        if(districts.size() < 4){
            throw new NotEnoughDistrictsException(TWO_WINS_REQUIRED);
        }
        for(District district : districts) {
            if(district.getCongressionalElection(year) == null){
                LOGGER.warn("No election data for District id: "+ district.getId());
                continue;
            }
            double demPercent = getPercentage(district, year, Party.DEMOCRATIC);
            double repPercent = getPercentage(district, year, Party.REPUBLICAN);
            if(demPercent > repPercent)
                demWinPercentages.add(demPercent);
            else if(repPercent > demPercent)
                repWinPercentages.add(repPercent);
        }
            /* check if there are at least two wins for each side  */
        if(demWinPercentages.size() < 2 || repWinPercentages.size() < 2) {
            throw new NotEnoughDistrictsException(TWO_WINS_REQUIRED);
        }
        TTest tTest = new TTest();
        double[] demWins = demWinPercentages.stream().mapToDouble(Double::doubleValue).toArray();
        double[] repWins = repWinPercentages.stream().mapToDouble(Double::doubleValue).toArray();
        double prob = tTest.homoscedasticTTest(demWins, repWins);
        prob = prob / 2; /* return 1 tailed t test value */
        StateMetricResult result = new StateMetricResult(state, year);
        result.setScore(prob);
        List<DistrictMetricResult> districtResults = new ArrayList<>();
        for (District d : districts) {
            DistrictMetricResult districtResult = new DistrictMetricResult(d);
            districtResults.add(districtResult);
        }
        result.setDistrictResults(districtResults);
        return result;
    }

    protected DistrictMetricResult calculate(District district, Year year) {
        /* The Lopsided wins test only applies at a state level */
        throw new MetricUnsupportedException(this.getClass().getSimpleName());
    }

    /* get the percentage of total votes for a party */
    private double getPercentage(District district, Year year, Party party) {
        CongressionalElection electResult = district.getCongressionalElection(year);
        int totalVotesCast = Arrays.stream(Party.values())
                .mapToInt( p -> electResult.getVotes(p))
                .sum();
        return ((double) electResult.getVotes(party)) / totalVotesCast;
    }
}
