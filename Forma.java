package org.example.Entitati.Shapes;

public abstract class Forma{

    protected Double lungime;
    protected Double latine;

    protected abstract Double calculArie();
    protected abstract double calculPerimetru();

    public Double getLungime() {
        return lungime;
    }

    public void setLungime(Double lungime) {
        this.lungime = lungime;
    }

    public Double getLatine() {
        return latine;
    }

    public void setLatine(Double latine) {
        this.latine = latine;
    }

//    public Forma(Double lungime, Double latine) {
//        this.lungime = lungime;
//        this.latine = latine;
//    }
}
