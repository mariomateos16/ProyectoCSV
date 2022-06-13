package es.mariomateos.proyectocsv;

public class Datos {
    private String pais;
    private String codPais;
    private String anno;
    private String atentados;

    public Datos() {
    }
    
    public Datos(String pais, String codPais, String atentados) {
        this.pais = pais;
        this.codPais = codPais;
        this.atentados = atentados;
    }
    
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCodPais() {
        return codPais;
    }

    public void setCodPais(String codPais) {
        this.codPais = codPais;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public String getAtentados() {
        return atentados;
    }
    
    public void setAtentados(String atentados) {
        this.atentados = atentados;
    }

    @Override
    public String toString(){
        String r = "";
        r += "Pais: " + pais + "\n";
        r += "Codigo: " + codPais + "\n";
        r += "Año: " + anno + "\n";
        r += "Suicidios: " + atentados + "\n";
        return r;
    
    }
}