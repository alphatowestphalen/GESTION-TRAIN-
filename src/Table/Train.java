package Table;

public class Train {
    private String id;
    private String numTrain;
    private String nbrPlace;
    private String desing;
    private Itineraire itineraire;
    private String NumItineraire;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumTrain() {
        return numTrain;
    }

    public void setNumTrain(String numTrain) {
        this.numTrain = numTrain;
    }

    public String getDesing() {
        return desing;
    }

    public String setDesing(String desing) {
        this.desing = desing;
        return desing;
    }

    public String getNbrPlace() {
        return nbrPlace;
    }

    public void setNbrPlace(String nbrPlace) {
        this.nbrPlace = nbrPlace;
    }

    public Itineraire getItineraire() {
        return itineraire;
    }

    public void setIttineraire(Itineraire ittineraire) {
        this.itineraire = ittineraire;
    }

    public String getNumItineraire() {
        return NumItineraire;
    }

    public void setNumItineraire(String numItineraire) {
        NumItineraire = numItineraire;
    }
}
