package ArbolBinarioBusquedaAVL;

import java.util.ArrayList;
import ListaLigadaBusquedaNormal.NodoBinarioBusqueda;

/**
 *
 * @author user
 * @param <T>
 *
 */
public class NodoAVL<T extends Comparable> extends NodoBinarioBusqueda {

    private int fB;
    private ArbolAVL sinonimo;
    private ArbolAVL antonimo;
    private String significado;

    public NodoAVL(T dato) {
        super(dato);
    }

    public int getfB() {
        return fB;
    }

    public void setfB(int fB) {
        this.fB = fB;
    }

    public void setSignificado(String d) {
        significado = d;

    }

    public String getSignificado() {
        return significado;
    }

    public ArbolAVL getSinonimo() {
        return sinonimo;
    }

    public void setSinonimo(NodoAVL sinonimo) {
        this.sinonimo=new ArbolAVL(sinonimo);
    }

    public ArbolAVL getAntonimo() {
        return antonimo;
    }

    public void setAntonimo(NodoAVL antonimo) {
        this.antonimo = new ArbolAVL(antonimo);
    }
    
    
}
