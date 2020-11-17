package ListaGeneralizada;

/**
 *
 * @author user
 */
public class NodoNario {
    private int sw;
    private Object dato;
    private NodoNario liga;
    
    public NodoNario(Object d){
        dato=d;
    }

    public int getSw() {
        return sw;
    }

    public void setSw(int sw) {
        this.sw = sw;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public NodoNario getLiga() {
        return liga;
    }

    public void setLiga(NodoNario liga) {
        this.liga = liga;
    }
    
    
}
