
public class RepresentationDiscrepancy extends Metric {
  /**
     * Method is used to calculate RepresentationDiscrepancy of the states
     * @param State Object, Year
     * @return the score value. 
     */
    protected StateMetricResult calculate(State state, Year year){
        List<District> districts = state.getDistricts(year);
        if(districts.isEmpty()){
            throw new DataNotFoundException();
        }
        if (districts.size() == 1){
            throw new NotEnoughDistrictsException(MetricExceptionMessages.NOT_ENOUGH_DISTRICTS);
        }
        double demTotalVotes = 0;
        double repTotalVotes = 0;
        double totalVotes = 0;
        double demWonSeats = 0;
        double repWonSeats = 0;
        List<DistrictMetricResult> districtResults = new ArrayList<>();
        for(District d : districts){
            DistrictMetricResult districtResult = calculate(d, year);
            districtResults.add(districtResult);
            CongressionalElection electResult = d.getCongressionalElection(year);
            demTotalVotes += electResult.getVotes(Party.DEMOCRATIC);
            repTotalVotes += electResult.getVotes(Party.REPUBLICAN);
            totalVotes += electResult.getVotes(Party.DEMOCRATIC) + electResult.getVotes(Party.REPUBLICAN) + electResult.getVotes(Party.OTHER);
            if(electResult.getWinner() == Party.REPUBLICAN){
                repWonSeats++;
            }else if(electResult.getWinner() == Party.DEMOCRATIC){
                demWonSeats++;
            }
        }
        StateMetricResult result = new StateMetricResult(state, year);
        double demDiscrepancy =  (demTotalVotes/totalVotes) - (demWonSeats/(double)districts.size());
        double repDiscrepancy =  (repTotalVotes/totalVotes) - (repWonSeats/(double)districts.size());
        result.addProperty(PERCENTAGE_DEMOCRATIC_SEATS,demWonSeats/(double)districts.size());
        result.addProperty(PERCENTAGE_REPUBLICAN_SEATS,repWonSeats/(double)districts.size());
        result.addProperty(PERCENTAGE_DEMOCRATIC_VOTES,demTotalVotes/totalVotes);
        result.addProperty(PERCENTAGE_REPUBLICAN_VOTES,repTotalVotes/totalVotes);
        if(demDiscrepancy > repDiscrepancy){
            result.setScore(demDiscrepancy);
            result.addProperty(DISADVANTAGED_PARTY, Party.DEMOCRATIC);
        }else{
            result.setScore(repDiscrepancy);
            result.addProperty(DISADVANTAGED_PARTY, Party.REPUBLICAN);
        }
        result.setDistrictResults(districtResults);
        return result;
    }
 /**
     * Method is used to calculate RepresentationDiscrepancy of the district
     * @param District Object, Year
     * @return the score value. 
     */
    protected DistrictMetricResult calculate(District district, Year year){
        CongressionalElection electResult = district.getCongressionalElection(year);
        if(electResult == null){
            throw new DataNotFoundException();
        }
        DistrictMetricResult result = new DistrictMetricResult(district);
        double totalVotes = electResult.getVotes(Party.DEMOCRATIC) + electResult.getVotes(Party.REPUBLICAN) + electResult.getVotes(Party.OTHER);
        if(electResult.getWinner() == Party.REPUBLICAN){
            result.setScore((double)electResult.getVotes(Party.DEMOCRATIC)/totalVotes);
            result.addProperty(DISADVANTAGED_PARTY, Party.DEMOCRATIC);
        }else{
            result.setScore((double)electResult.getVotes(Party.REPUBLICAN)/totalVotes);
            result.addProperty(DISADVANTAGED_PARTY, Party.REPUBLICAN);
        }
        result.addProperty(PERCENTAGE_DEMOCRATIC_VOTES,(double)electResult.getVotes(Party.DEMOCRATIC)/totalVotes);
        result.addProperty(PERCENTAGE_REPUBLICAN_VOTES,(double)electResult.getVotes(Party.REPUBLICAN)/totalVotes);
        return result;
    }

}
