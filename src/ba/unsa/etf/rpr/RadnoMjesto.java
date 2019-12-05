package ba.unsa.etf.rpr;

import java.util.Objects;

public class RadnoMjesto {
    private String naziv;
    private double koeficijent;
    private Radnik radnik;

    public RadnoMjesto(String naziv, double koeficijent, Radnik radnik) {
        this.naziv = naziv;
        this.koeficijent = koeficijent;
        this.radnik = radnik;
    }

    public RadnoMjesto() {
        naziv = "";
        koeficijent = 0;
        radnik = null;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getKoeficijent() {
        return koeficijent;
    }

    public void setKoeficijent(double koeficijent) {
        this.koeficijent = koeficijent;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }
    public boolean jelslobodno(){
        if(radnik==null){
            return true;
        }
        return false;
    }

      @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RadnoMjesto that = (RadnoMjesto) o;
        return Double.compare(that.koeficijent, koeficijent) == 0 &&
                Objects.equals(naziv, that.naziv);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv, koeficijent);
    }
}
