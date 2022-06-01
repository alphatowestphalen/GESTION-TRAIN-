package Table;

public class Place {
    private String id;
    private Train train;
    private String numTrain;
    private String numPlace;
    private String occupation;

    public String getNumTrain() {
        return numTrain;
    }

    public void setNumTrain(String numTrain) {
        this.numTrain = numTrain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public String getNumPlace() {
        return numPlace;
    }

    public void setNumPlace(String numPlace) {
        this.numPlace = numPlace;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
