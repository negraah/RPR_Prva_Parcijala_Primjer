package ba.unsa.etf.rpr;

public class Radnik implements Comparable {
    private String imePrezime;
    private String jmbg;
    private double[] plate= new double[1000];
    int velicina = 0;

    public Radnik(String imePrezime, String jmbg) {
        this.imePrezime = imePrezime;
        this.jmbg = jmbg;
       // plate = new double[1000];
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public void dodajPlatu(double plata) {
        if(velicina>=1000){
            throw new IllegalArgumentException("Ne možete registrovati više od 1000 plata za radnika" + getImePrezime());
        }
        plate[velicina++] = plata;

    }

    public double prosjecnaPlata() {
        if(velicina==0) return  0;
        double suma=0;
        for (Double plata : plate) {
            suma += plata;
        }
        return suma/velicina;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Radnik){
            Radnik radnik = (Radnik) o;
             int comp = Double.compare(radnik.prosjecnaPlata(),this.prosjecnaPlata());
            if(comp==0) {
                comp= radnik.getImePrezime().compareTo(getImePrezime());
            }
            return  comp;
        }
        return 0;
    }


}
