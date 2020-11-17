package ListaLigadaBusquedaNormal;

import ListaLigadaNormal.NodoBinarioGenerico;

/**
 *
 * 
 * @param <T>
 */
public class NodoBinarioBusqueda<T extends Comparable> extends NodoBinarioGenerico<T> {

    public NodoBinarioBusqueda(T dato) {
        super(dato);
    }

    @Override
    public T getDato() {
        return dato;
    }
    
    
    
    
    
}
