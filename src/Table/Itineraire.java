package Table;

public class Itineraire {
    private String id;
    private String numItineraire;
    private String villeDepart;
    private String villeArrive;
    private int frais;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumItineraire() {
        return numItineraire;
    }

    public void setNumItineraire(String numItineraire) {
        this.numItineraire = numItineraire;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleArrive() {
        return villeArrive;
    }

    public void setVilleArrive(String villeArrive) {
        this.villeArrive = villeArrive;
    }

    public int getFrais() {
        return frais;
    }

    public void setFrais(int frais) {
        this.frais = frais;
    }
}
