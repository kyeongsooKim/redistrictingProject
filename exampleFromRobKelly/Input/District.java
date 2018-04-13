

public class District {
    private static final String DISTRICT_HEADER = "District No";
    private Integer districtNumber;
    private Year year;
    private State state;
    public District(Integer id, Integer districtNumber, Year year, String boundary, double area, double perimeter) {
        this.year = year;
        this.districtNumber = districtNumber;
    }

    public String getGeoJsonDistrictLevel() {
        return this.getBoundary();
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Year getYear() {
        return year;
    }

    public Integer getDistrictNumber() {
        return this.districtNumber;
    }


    public List<String> getHeaders() {
        List<String> header = new ArrayList();
        header.add(DISTRICT_HEADER);
        return header;
    }


    public List<Object> getRow() {
        List<Object> row = new ArrayList();
        row.add(this.getDistrictNumber());
        return row;
    }

    public String toString() {
        return state.toString() + " Congressional District " + String.valueOf(districtNumber);
    }

}
