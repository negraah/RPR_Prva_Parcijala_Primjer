package ba.unsa.etf.rpr;

import java.util.*;
import java.util.function.Function;

public class Preduzece {
    private int osnovica;
    ArrayList<RadnoMjesto> mjesto = new ArrayList<RadnoMjesto>();

    public Preduzece(int osnovica) throws NeispravnaOsnovica {
        if(osnovica<0 || osnovica==0){
            throw new NeispravnaOsnovica("Neispravna osnovica"+osnovica);
        }
        this.osnovica = osnovica;
    }

    public int dajOsnovicu() {
        return osnovica;
    }

    public void postaviOsnovicu(int osnovica) throws NeispravnaOsnovica {
        if(osnovica<0 || osnovica==0){
            throw new NeispravnaOsnovica("Neispravna osnovica"+osnovica);
        }
        this.osnovica = osnovica;

    }

    public void novoRadnoMjesto(RadnoMjesto rm) {
        mjesto.add(rm);
    }

    public Map<RadnoMjesto, Integer> sistematizacija() {
        Map<RadnoMjesto, Integer> mapa = new HashMap<>();

        for (RadnoMjesto radnoMjesto : mjesto) {
            if(!mapa.containsKey(radnoMjesto)){
                mapa.put(radnoMjesto, 1);
            }else{
                mapa.put(radnoMjesto, mapa.get(radnoMjesto)+1);
            }
        }
        return mapa;
    }

    public void zaposli(Radnik r, String naziv_mjesta) {
        for (RadnoMjesto radnoMjesto : mjesto) {
            if(radnoMjesto.getNaziv().equals(naziv_mjesta) && radnoMjesto.jelslobodno() ){
                radnoMjesto.setRadnik(r);
                return;
            }
        }
    throw new IllegalStateException("Nijedno radno mjesto tog tipa nije slobodno");
    }

    public Set<Radnik> radnici() {
        Set<Radnik> setic = new TreeSet<Radnik>();
        for (RadnoMjesto radnoMjesto : mjesto) {
            if(radnoMjesto.getRadnik()!=null) {
                setic.add(radnoMjesto.getRadnik());
            }
        }
        return setic;
    }

    public double iznosPlate() {
        double suma_plata=0;
        for (RadnoMjesto radnoMjesto : mjesto) {
            if(radnoMjesto.getRadnik()!=null){
                Radnik radnik = radnoMjesto.getRadnik();
                double koef = radnoMjesto.getKoeficijent();
                suma_plata += koef*osnovica;
            }
        }
        return suma_plata;
    }

    public void obracunajPlatu() {

        for (RadnoMjesto radnoMjesto : mjesto) {
            if(radnoMjesto.getRadnik()!=null){
                Radnik radnik = radnoMjesto.getRadnik();
                double koef = radnoMjesto.getKoeficijent();
                radnik.dodajPlatu(osnovica*koef);
            }
        }
    }

    public List<Radnik> filterRadnici(Function<Radnik, Boolean> f) {
        List<Radnik> lista = new ArrayList<>();
        for (RadnoMjesto radnoMjesto : mjesto) {
            if(radnoMjesto.getRadnik()!=null && f.apply(radnoMjesto.getRadnik()) ){
                lista.add(radnoMjesto.getRadnik());
            }
        }
    return lista;
    }

    public List<Radnik> vecaProsjecnaPlata(double plata) {
        return filterRadnici(radnik->radnik.prosjecnaPlata()>plata);
    }
}
