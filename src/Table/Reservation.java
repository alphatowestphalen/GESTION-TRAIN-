package Table;

import java.time.LocalDate;

public class Reservation {
    private String id;
    private String numReservation;
    private Train train;
    private String NumTrain;
    private String NumPlace;
    private Place place;
    private String dateReservation;
    private String nomVoyageur;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumTrain() {
        return NumTrain;
    }

    public void setNumTrain(String numTrain) {
        NumTrain = numTrain;
    }

    public String getNumPlace() {
        return NumPlace;
    }

    public void setNumPlace(String numPlace) {
        NumPlace = numPlace;
    }

    public String getNumReservation() {
        return numReservation;
    }

    public void setNumReservation(String numReservation) {
        this.numReservation = numReservation;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getNomVoyageur() {
        return nomVoyageur;
    }

    public void setNomVoyageur(String nomVoyageur) {
        this.nomVoyageur = nomVoyageur;
    }
}
