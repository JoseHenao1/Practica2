package ListaLigadaNormal;

import java.util.*;
import ListaGeneralizada.NodoNario;

/**
 *
 * @author user
 * @param <E>
 */
public class ArbolBinarioListaLigada<E> {
    protected NodoBinarioGenerico<E> raiz;

    public ArbolBinarioListaLigada() {
    }

    public ArbolBinarioListaLigada(NodoBinarioGenerico<E> raiz) {
        this.setRaiz(raiz);
    }

    public void setRaiz(NodoBinarioGenerico<E> raiz) {
        this.raiz = raiz;
    }
    
    public static void inorden(NodoBinarioGenerico r) {
        if (r != null) {
            inorden(r.getLi());
            System.out.print(r.getDato());
            inorden(r.getLd());
        }
    }
    
    public static void preorden(NodoBinarioGenerico r) {
        if (r != null) {
            System.out.print(r.getDato());
            preorden(r.getLi());
            preorden(r.getLd());
        }
    }
    
    public void recorrido1() {
        Queue<NodoBinarioGenerico> queue = new LinkedList<>();
        if (raiz != null) {
            queue.add(raiz);
            NodoBinarioGenerico a;
            while (!queue.isEmpty()) {
                a = queue.poll();
                System.out.print(a.getDato() + ",");
                if (a.getLi() != null) {
                    queue.add(a.getLi());
                }
                if (a.getLd() != null) {
                    queue.add(a.getLd());
                }
            }
        }
    }
}
