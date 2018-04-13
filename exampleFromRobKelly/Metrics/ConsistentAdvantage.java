
public class ConsistentAdvantage extends Metric {

    private static final double CORRECTION_FACTOR = 0.5708;
     /**
     * Method is used to calculate Consistent Advantage of the states
     * @param State Object, Year
     * @return the score value. 
     */
    protected StateMetricResult calculate(State state, Year year) {
        List<District> districts = state.getDistricts(year);
        if (districts.isEmpty()) {
            throw new DataNotFoundException();
        }
        double[] democratVoteShares = districts
                .stream()
                .mapToDouble(x -> getPartyVoteShare(x, year, Party.DEMOCRATIC))
                .sorted()
                .toArray();
        double democratAvgShare = StatUtils.mean(democratVoteShares);
        double democratMedShare = StatUtils.percentile(democratVoteShares, 50);
        double democratShareVar = StatUtils.variance(democratVoteShares);
        double meanMedDiff = democratAvgShare - democratMedShare;
        double standardError = Math.sqrt(democratShareVar ) / Math.sqrt(democratVoteShares.length);
        double zScore = meanMedDiff / standardError;
        zScore = (zScore * CORRECTION_FACTOR);
            /* in order to get a p value from a z score, we'll have to use the normal distribution */
        double pValue = new NormalDistribution(0, 1)
                .cumulativeProbability(zScore);
        StateMetricResult result = new StateMetricResult(state, year);
        result.setScore(pValue/10.0);
        List<DistrictMetricResult> districtResults = new ArrayList<>();
        for (District d : districts) {
            DistrictMetricResult districtResult = new DistrictMetricResult(d);
            districtResults.add(districtResult);
        }
        result.setDistrictResults(districtResults);
        return result;
    }

    protected DistrictMetricResult calculate(District district, Year year) {
        throw new MetricUnsupportedException(this.getClass().getSimpleName());
    }

    private double getPartyVoteShare(District district, Year year, Party party) {
        CongressionalElection electResult = district.getCongressionalElection(year);
        int totalVotesCast = electResult.getVotes(Party.DEMOCRATIC) + electResult.getVotes(Party.REPUBLICAN);
        return ((double) electResult.getVotes(party)) / totalVotesCast;
    }

}
