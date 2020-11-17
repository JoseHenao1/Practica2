/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ListaLigadaNormal;

/**
 *
 * @author user
 * @param <T>
 */
public class NodoBinarioGenerico<T> {
    protected final T dato;
    private NodoBinarioGenerico<T> Li;
    private NodoBinarioGenerico<T> Ld;
    private int Ancho;
    
    public NodoBinarioGenerico(T pDato){
        dato=pDato;
    }

    public NodoBinarioGenerico<T> getLi() {
        return Li;
    }

    public void setLi(NodoBinarioGenerico<T> Li) {
        this.Li = Li;
    }

    public NodoBinarioGenerico<T> getLd() {
        return Ld;
    }

    public void setLd(NodoBinarioGenerico<T> Ld) {
        this.Ld = Ld;
    }
    public T getDato(){
        return dato;
    }

    public int getAncho() {
        return Ancho;
    }
    
    public int recalcularAncho() {
        System.out.println("recalcular ancho");
        Ancho = 1;
        if (this.getLi() != null) {
            Ancho = Ancho + this.getLi().recalcularAncho();
        }
        if (this.getLd() != null) {
            Ancho = Ancho + this.getLd().recalcularAncho();
        }
        return Ancho;
    }

    @Override
    public String toString() {
        return dato.toString();
    }
    
}
