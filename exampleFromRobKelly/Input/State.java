
public class State{
    private static final String STATE = "STATE";
    private String name;
    private Map<Year, List<District>> districts;

    public State(int id, String name, String boundary, double area, double perimeter) {
        this.name = name;
    }

    public void setDistricts(Map<Year, List<District>> districts){
        this.districts = districts;
    }

    public List<District> getDistricts(Year year){
        return new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Year> getDistrictDataYears(){
        return districts.keySet().stream().sorted().collect(Collectors.toList());
    }

    public List<String> getHeaders() {
        List<String> header = new ArrayList<>();
        header.add(STATE);
        return header;
    }

    public List<Object> getRow() {
        List<Object> row = new ArrayList<>();
        row.add(this.getName());
        return row;
    }
    public String toString(){
        return name;
    }

}
