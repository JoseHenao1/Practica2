package ArbolBinarioBusquedaAVL;

import ListaLigadaBusquedaNormal.ArbolBinarioBusquedaGenerico;
import javax.swing.JOptionPane;
import ListaLigadaNormal.ArbolBinarioListaLigada;
import ListaLigadaNormal.NodoBinarioGenerico;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author user
 * @param <T>
 *
 */
public class ArbolAVL<T extends Comparable> {

    NodoAVL raiz, raizS, raizA;
    NodoAVL ult;
    NodoAVL x;

    private int cantidadp = 0;

    public ArbolAVL(NodoAVL raiz) {
        this.raiz = raiz;
    }

    public ArbolAVL(String raiz) {
        this.insertarPalabraS((T) raiz);
    }

    public ArbolAVL() {
    }

    public int getCantidadp() {
        return cantidadp;
    }

    public void setCantidadp(int cantidadp) {
        this.cantidadp = cantidadp;
    }

    public NodoAVL crearArbolSn(T dato1) {
        NodoAVL nodoSin = new NodoAVL(dato1);
        if (raizS == null) {
            raizS = nodoSin;
            return raizS;
        }
        NodoAVL nodoRecorrido = raizS; // Registro que uso para buscarComplejo
        NodoAVL padreNodoRecorridoX = null;
        NodoAVL pivote = raizS; // Nodo que se puede desvalancear
        NodoAVL padrePivote = null;
        NodoAVL nodoRecorridoParaFB;
        NodoAVL q;

        while (nodoRecorrido != null) {
            // Validar si el nodo x esta en riesgo de desbalanceo
            if (nodoRecorrido.getfB() != 0) {
                pivote = nodoRecorrido;
                padrePivote = padreNodoRecorridoX;
            }
            // 
            int comparacion = nodoSin.getDato().compareTo(nodoRecorrido.getDato());
            if (comparacion == 0) {
                //es un dato existente
                return nodoRecorrido;
            } else if (comparacion < 0) {
                // n es menor
                padreNodoRecorridoX = nodoRecorrido;
                nodoRecorrido = (NodoAVL) nodoRecorrido.getLi();
            } else {
                //n es mayor
                padreNodoRecorridoX = nodoRecorrido;
                nodoRecorrido = (NodoAVL) nodoRecorrido.getLd();
            }
        }

        /**
         * Insertar el dato
         */
        if (nodoSin.getDato().compareTo(padreNodoRecorridoX.getDato()) > 0) {
            padreNodoRecorridoX.setLd(nodoSin);
        } else if (nodoSin.getDato().compareTo(padreNodoRecorridoX.getDato()) < 0) {
            padreNodoRecorridoX.setLi(nodoSin);
        }

        /**
         * Calcular los nuevos factores de balance de todos los ancestros del
         * nodo insertado
         */
        if (nodoSin.getDato().compareTo(pivote.getDato()) > 0) {
            pivote.setfB(pivote.getfB() - 1);
            nodoRecorridoParaFB = (NodoAVL) pivote.getLd();
        } else {
            pivote.setfB(pivote.getfB() + 1);
            nodoRecorridoParaFB = (NodoAVL) pivote.getLi();
        }
        q = nodoRecorridoParaFB;

        while (nodoRecorridoParaFB != nodoSin) {
            if (nodoSin.getDato().compareTo(nodoRecorridoParaFB.getDato()) > 0) {
                nodoRecorridoParaFB.setfB(nodoRecorridoParaFB.getfB() - 1);
                nodoRecorridoParaFB = (NodoAVL) nodoRecorridoParaFB.getLd();
            } else {
                nodoRecorridoParaFB.setfB(nodoRecorridoParaFB.getfB() + 1);
                nodoRecorridoParaFB = (NodoAVL) nodoRecorridoParaFB.getLi();
            }
        }

        /**
         * Rebalancear
         */
        if (!((pivote.getfB() == -2) || (pivote.getfB() == 2))) {
            return nodoSin;
        }

        // Estamos tentados a cambiar de raiz
        NodoAVL nuevaRaizSubArbol = null;
        if (pivote.getfB() == +2) {
            if (q.getfB() == +1) {
                System.out.println("rotacionDerecha");
                nuevaRaizSubArbol = rotacionDerecha(pivote, q);
            } else {
                System.out.println("dobleRotacionDerecha");
                nuevaRaizSubArbol = dobleRotacionDerecha(pivote, q);
            }
        } else if (pivote.getfB() == -2) {
            if (q.getfB() == -1) {
                System.out.println("rotacionIzquierda");
                nuevaRaizSubArbol = rotacionIzquierda(pivote, q);

            } else {
                System.out.println("dobleRotacionIzquierda");
                nuevaRaizSubArbol = dobleRotacionIzquierda(pivote, q);
            }
        }

        /**
         * Consecuencias de rebalancear, validación de que pivote no fuera la
         * raíz
         */
        if (padrePivote == null) {
            raizS = nuevaRaizSubArbol;
            return nodoSin;
        }

        /**
         * Liga el padre del pivote con la nueva raiz
         */
        if (padrePivote.getLi() == pivote) {
            padrePivote.setLi(nuevaRaizSubArbol);
        } else {
            padrePivote.setLd(nuevaRaizSubArbol);
        }
        return nodoSin;
    }

    public NodoAVL crearArbolAn(T dato) {
        NodoAVL NodoAn = new NodoAVL(dato);
        if (raizA == null) {
            raizA = NodoAn;
            return raizA;
        }
        NodoAVL nodoRecorrido = raizA; // Registro que uso para buscarComplejo
        NodoAVL padreNodoRecorridoX = null;
        NodoAVL pivote = raizA; // Nodo que se puede desvalancear
        NodoAVL padrePivote = null;
        NodoAVL nodoRecorridoParaFB;
        NodoAVL q;

        while (nodoRecorrido != null) {
            // Validar si el nodo x esta en riesgo de desbalanceo
            if (nodoRecorrido.getfB() != 0) {
                pivote = nodoRecorrido;
                padrePivote = padreNodoRecorridoX;
            }
            // 
            int comparacion = NodoAn.getDato().compareTo(nodoRecorrido.getDato());
            if (comparacion == 0) {
                //es un dato existente
                return nodoRecorrido;
            } else if (comparacion < 0) {
                // n es menor
                padreNodoRecorridoX = nodoRecorrido;
                nodoRecorrido = (NodoAVL) nodoRecorrido.getLi();
            } else {
                //n es mayor
                padreNodoRecorridoX = nodoRecorrido;
                nodoRecorrido = (NodoAVL) nodoRecorrido.getLd();
            }
        }

        /**
         * Insertar el dato
         */
        if (NodoAn.getDato().compareTo(padreNodoRecorridoX.getDato()) > 0) {
            padreNodoRecorridoX.setLd(NodoAn);
        } else if (NodoAn.getDato().compareTo(padreNodoRecorridoX.getDato()) < 0) {
            padreNodoRecorridoX.setLi(NodoAn);
        }

        /**
         * Calcular los nuevos factores de balance de todos los ancestros del
         * nodo insertado
         */
        if (NodoAn.getDato().compareTo(pivote.getDato()) > 0) {
            pivote.setfB(pivote.getfB() - 1);
            nodoRecorridoParaFB = (NodoAVL) pivote.getLd();
        } else {
            pivote.setfB(pivote.getfB() + 1);
            nodoRecorridoParaFB = (NodoAVL) pivote.getLi();
        }
        q = nodoRecorridoParaFB;

        while (nodoRecorridoParaFB != NodoAn) {
            if (NodoAn.getDato().compareTo(nodoRecorridoParaFB.getDato()) > 0) {
                nodoRecorridoParaFB.setfB(nodoRecorridoParaFB.getfB() - 1);
                nodoRecorridoParaFB = (NodoAVL) nodoRecorridoParaFB.getLd();
            } else {
                nodoRecorridoParaFB.setfB(nodoRecorridoParaFB.getfB() + 1);
                nodoRecorridoParaFB = (NodoAVL) nodoRecorridoParaFB.getLi();
            }
        }

        /**
         * Rebalancear
         */
        if (!((pivote.getfB() == -2) || (pivote.getfB() == 2))) {
            return NodoAn;
        }

        // Estamos tentados a cambiar de raiz
        NodoAVL nuevaRaizSubArbol = null;
        if (pivote.getfB() == +2) {
            if (q.getfB() == +1) {
                System.out.println("rotacionDerecha");
                nuevaRaizSubArbol = rotacionDerecha(pivote, q);
            } else {
                System.out.println("dobleRotacionDerecha");
                nuevaRaizSubArbol = dobleRotacionDerecha(pivote, q);
            }
        } else if (pivote.getfB() == -2) {
            if (q.getfB() == -1) {
                System.out.println("rotacionIzquierda");
                nuevaRaizSubArbol = rotacionIzquierda(pivote, q);

            } else {
                System.out.println("dobleRotacionIzquierda");
                nuevaRaizSubArbol = dobleRotacionIzquierda(pivote, q);
            }
        }

        /**
         * Consecuencias de rebalancear, validación de que pivote no fuera la
         * raíz
         */
        if (padrePivote == null) {
            raizA = nuevaRaizSubArbol;
            return NodoAn;
        }

        /**
         * Liga el padre del pivote con la nueva raiz
         */
        if (padrePivote.getLi() == pivote) {
            padrePivote.setLi(nuevaRaizSubArbol);
        } else {
            padrePivote.setLd(nuevaRaizSubArbol);
        }
        return NodoAn;
    }

    /**
     * Retorna el nodo si se encuentra o null en caso de no encontrarse
     *
     * @param dato
     * @return
     */
    public NodoAVL buscar(Comparable dato) {
        NodoAVL aux = null;
        aux = raiz;

        while (aux != null) {

            if (aux.getDato().compareTo(dato) == 0) {
                return aux;
            } else if (aux.getDato().compareTo(dato) < 0) {
                if (aux.getLd() != null) {
                    aux = (NodoAVL) aux.getLd();
                } else {
                    return null;
                }
            } else {
                if (aux.getLi() != null) {
                    aux = (NodoAVL) aux.getLi();
                } else {
                    return null;
                }
            }
        }
        return aux;
    }

    public NodoAVL buscarComplejo(Comparable dato, int x) {
        NodoAVL aux = null;
        if (x == 0) {
            aux = raiz;
        } else if (x == 1) {
            aux = raizS;
        } else if (x == 2) {
            aux = raizA;
        } else {
            System.out.println("ERROR");
        }

        while (aux != null) {

            if (aux.getDato().compareTo(dato) == 0) {
                return aux;
            } else if (aux.getDato().compareTo(dato) < 0) {
                if (aux.getLd() != null) {
                    aux = (NodoAVL) aux.getLd();
                } else {
                    return null;
                }
            } else {
                if (aux.getLi() != null) {
                    aux = (NodoAVL) aux.getLi();
                } else {
                    return null;
                }
            }
        }
        return aux;
    }

    public void InsertarPalabraC(NodoAVL dato) {
        boolean bandera = true;
        String respuesta = "", si = "si", no = "no";
        String significado;
        NodoAVL aux, auxS, auxA;
        T sinonimo = null;
        T antonimo = null;

        if (dato.getSignificado() == null || dato.getSignificado() == "") {
            significado = JOptionPane.showInputDialog(null, "ingrese el significado: ");
            dato.setSignificado(significado);
        }

        respuesta = pregunta("Va a ingresar sinonimos?");
        if (respuesta == si) {
            sinonimo = (T) JOptionPane.showInputDialog(null, "ingrese el sinonimo ");
        } else if (respuesta == no) {
            bandera = false;
        }

        while (bandera) {
            aux = buscarComplejo(sinonimo, 0);
            if (aux == null && sinonimo != null) {
                this.insertarPalabraS(sinonimo);
                auxS = this.crearArbolSn(sinonimo);
                dato.setSinonimo(auxS);
            }
            respuesta = pregunta("va a seguir ingresando sinonimos?");
            if (respuesta.equals(si)) {
                sinonimo = (T) JOptionPane.showInputDialog(null, "ingrese el sinonimo ");
                bandera = true;
            } else if (respuesta.equals(no)) {
                bandera = false;
            }

        }

        bandera = true;
        respuesta = pregunta("Va a ingresar Antonimos?");
        if (respuesta == si) {
            antonimo = (T) JOptionPane.showInputDialog(null, "ingrese el antonimo ");
        } else if (respuesta == no) {
            bandera = false;
        }

        while (bandera) {
            aux = buscarComplejo(antonimo, 0);
            if (aux == null) {
                this.insertarPalabraS(antonimo);
                auxA = this.crearArbolAn(antonimo);
                dato.setAntonimo(auxA);
            }
            respuesta = pregunta("va a seguir ingresando antonimos?");
            if (respuesta.equals(si)) {
                antonimo = (T) JOptionPane.showInputDialog(null, "ingrese el antonimo ");
                bandera = true;
            } else if (respuesta.equals(no)) {
                bandera = false;
            }

        }

    }

    public NodoAVL insertarPalabraS(T dato) {
        this.setCantidadp(cantidadp += 1);
        System.out.println("dato " + dato);
        NodoAVL nodoAInsertar = new NodoAVL(dato);
        if (raiz == null) {
            raiz = nodoAInsertar;
            return raiz;
        }
        NodoAVL nodoRecorrido = raiz; // Registro que uso para buscarComplejo
        NodoAVL padreNodoRecorridoX = null;
        NodoAVL pivote = raiz; // Nodo que se puede desvalancear
        NodoAVL padrePivote = null;
        NodoAVL nodoRecorridoParaFB;
        NodoAVL q;

        while (nodoRecorrido != null) {
            // Validar si el nodo x esta en riesgo de desbalanceo
            if (nodoRecorrido.getfB() != 0) {
                pivote = nodoRecorrido;
                padrePivote = padreNodoRecorridoX;
            }
            // 
            int comparacion = nodoAInsertar.getDato().compareTo(nodoRecorrido.getDato());
            if (comparacion == 0) {
                //es un dato existente
                return nodoRecorrido;
            } else if (comparacion < 0) {
                // n es menor
                padreNodoRecorridoX = nodoRecorrido;
                nodoRecorrido = (NodoAVL) nodoRecorrido.getLi();
            } else {
                //n es mayor
                padreNodoRecorridoX = nodoRecorrido;
                nodoRecorrido = (NodoAVL) nodoRecorrido.getLd();
            }
        }

        /**
         * Insertar el dato
         */
        if (nodoAInsertar.getDato().compareTo(padreNodoRecorridoX.getDato()) > 0) {
            padreNodoRecorridoX.setLd(nodoAInsertar);
        } else if (nodoAInsertar.getDato().compareTo(padreNodoRecorridoX.getDato()) < 0) {
            padreNodoRecorridoX.setLi(nodoAInsertar);
        }

        /**
         * Calcular los nuevos factores de balance de todos los ancestros del
         * nodo insertado
         */
        if (nodoAInsertar.getDato().compareTo(pivote.getDato()) > 0) {
            pivote.setfB(pivote.getfB() - 1);
            nodoRecorridoParaFB = (NodoAVL) pivote.getLd();
        } else {
            pivote.setfB(pivote.getfB() + 1);
            nodoRecorridoParaFB = (NodoAVL) pivote.getLi();
        }
        q = nodoRecorridoParaFB;

        while (nodoRecorridoParaFB != nodoAInsertar) {
            if (nodoAInsertar.getDato().compareTo(nodoRecorridoParaFB.getDato()) > 0) {
                nodoRecorridoParaFB.setfB(nodoRecorridoParaFB.getfB() - 1);
                nodoRecorridoParaFB = (NodoAVL) nodoRecorridoParaFB.getLd();
            } else {
                nodoRecorridoParaFB.setfB(nodoRecorridoParaFB.getfB() + 1);
                nodoRecorridoParaFB = (NodoAVL) nodoRecorridoParaFB.getLi();
            }
        }

        /**
         * Rebalancear
         */
        if (!((pivote.getfB() == -2) || (pivote.getfB() == 2))) {
            return nodoAInsertar;
        }

        // Estamos tentados a cambiar de raiz
        NodoAVL nuevaRaizSubArbol = null;
        if (pivote.getfB() == +2) {
            if (q.getfB() == +1) {
                System.out.println("rotacionDerecha");
                nuevaRaizSubArbol = rotacionDerecha(pivote, q);
            } else {
                System.out.println("dobleRotacionDerecha");
                nuevaRaizSubArbol = dobleRotacionDerecha(pivote, q);
            }
        } else if (pivote.getfB() == -2) {
            if (q.getfB() == -1) {
                System.out.println("rotacionIzquierda");
                nuevaRaizSubArbol = rotacionIzquierda(pivote, q);

            } else {
                System.out.println("dobleRotacionIzquierda");
                nuevaRaizSubArbol = dobleRotacionIzquierda(pivote, q);
            }
        }

        /**
         * Consecuencias de rebalancear, validación de que pivote no fuera la
         * raíz
         */
        if (padrePivote == null) {
            raiz = nuevaRaizSubArbol;
            return nodoAInsertar;
        }

        /**
         * Liga el padre del pivote con la nueva raiz
         */
        if (padrePivote.getLi() == pivote) {
            padrePivote.setLi(nuevaRaizSubArbol);
        } else {
            padrePivote.setLd(nuevaRaizSubArbol);
        }
        return nodoAInsertar;
    }

    public void Modificar(NodoAVL borrable) {
        boolean cosa=this.Eliminar(borrable.getDato());
        if(cosa){
            JOptionPane.showMessageDialog(null,"Si se borro");
        }
        T palabra=(T) JOptionPane.showInputDialog(null,"Ingrese la palabra modificada");
        this.insertarPalabraS(palabra);
    }

    public boolean Eliminar(Comparable Dato) {
        NodoAVL aux = raiz;
        NodoAVL padre = raiz;
        boolean hijoIzquierdo = true;
        int i = Dato.compareTo(aux.getDato());
        while (aux.getDato() != Dato) {
            padre = aux;
            if (i < 0) {
                hijoIzquierdo = true;
                aux = (NodoAVL) aux.getLi();
            } else {
                hijoIzquierdo = false;
                aux = (NodoAVL) aux.getLd();
            }
            if (aux == null) {
                return false;
            }
        }
        if (aux.getLi() == null && aux.getLd() == null) {
            if (aux == raiz) {
                raiz = null;
            } else if (hijoIzquierdo) {
                padre.setLi(null);
            } else {
                padre.setLd(null);
            }
        } else if (aux.getLd() == null) {
            if (aux == raiz) {
                raiz = (NodoAVL) aux.getLi();
            } else if (hijoIzquierdo) {
                padre.setLi(aux.getLi());
            } else {
                padre.setLd(aux.getLi());
            }
        }else if(aux.getLi()==null){
            if (aux == raiz) {
                raiz = (NodoAVL) aux.getLd();
            } else if (hijoIzquierdo) {
                padre.setLi(aux.getLd());
            } else {
                padre.setLd(aux.getLi());
            }
        }else{
            NodoAVL reemplazo=Nodoreemplazo(aux);
            if(aux==raiz){
                raiz=reemplazo;
            }else if(hijoIzquierdo){
                padre.setLi(reemplazo);
            }else{
                padre.setLd(reemplazo);
            }
            reemplazo.setLi(aux.getLi());
        }
        return true;
    }

    public NodoAVL Nodoreemplazo(NodoAVL nodoR){
        NodoAVL Rpadre=nodoR;
        NodoAVL reemplazo=nodoR;
        NodoAVL aux=(NodoAVL) nodoR.getLd();
        if(reemplazo!=nodoR.getLd()){
            Rpadre.setLi(reemplazo.getLd());
            reemplazo.setLd(nodoR.getLd());
        }
        return reemplazo;
    }
    /**
     * 1. void unaRotacionALaDerecha(nodoAVL p, nodoAVL q) 2.
     * p.asignaLi(q.retornaLd()) 3. q.asignaLd(p) 4. p.asignaFb(0) 5.
     * q.asignaFb(0) 6. fin(unaRotacionALaDerecha)
     *
     * @param pivote
     * @param q
     */
    private NodoAVL rotacionDerecha(NodoAVL p, NodoAVL q) {
        p.setLi(q.getLd());
        q.setLd(p);
        p.setfB(0);
        q.setfB(0);
        return q;
    }

    /**
     * 1. void dobleRotacionALaDerecha(nodoAVL p, nodoAVL q) 2. r =
     * q.retornaLd() 3. q.asignaLd(r.retornaLi()) 4. r.asignaLi(q) 5.
     * p.asignaLi(r.retornaLd() 6. r.asignaLd(p) 7. casos de r.retornaFb() 8. 0:
     * p.asignaFb(0) 9. q.asignaFb(0) 10. break 11. 1: p.asignaFb(–1) 12.
     * q.asignaFb(0) 13. break 14. –1: p.asignaFb(0) 15. q.asignaFb(1) 16.
     * fin(casos) 17. r.asignaFb(0) 18. q = r 19. fin(dobleRotacionALaDerecha)
     *
     * @param pivote
     * @param q
     */
    private NodoAVL dobleRotacionDerecha(NodoAVL p, NodoAVL q) {
        NodoAVL r = (NodoAVL) q.getLd();
        q.setLd(r.getLi());
        r.setLi(q);
        p.setLi(r.getLd());
        r.setLd(p);
        switch (r.getfB()) {
            case 0:
                p.setfB(0);
                q.setfB(0);
                break;
            case 1:
                p.setfB(-1);
                q.setfB(0);
                break;
            case -1:
                p.setfB(0);
                q.setfB(1);
                break;
        }
        r.setfB(0);
        return r;
    }

    /**
     * 1. void unaRotacionALaIzquierda(nodoAVL p, nodoAVL q) 2.
     * p.asignaLd(q.retornaLi()) 3. q.asignaLi(p) 4. p.asignaFb(0) 5.
     * q.asignaFb(0) 6. fin(unaRotacionALaIzquierda)
     *
     * @param pivote
     * @param q
     */
    private NodoAVL rotacionIzquierda(NodoAVL p, NodoAVL q) {
        p.setLd(q.getLi());
        q.setLi(p);
        p.setfB(0);
        q.setfB(0);
        return q;
    }

    /**
     *
     * @param p
     * @param q
     */
    private NodoAVL dobleRotacionIzquierda(NodoAVL p, NodoAVL q) {
        NodoAVL r = (NodoAVL) q.getLi();
        q.setLi(r.getLd());
        r.setLd(q);

        p.setLd(r.getLi());
        r.setLi(p);
        switch (r.getfB()) {
            case 0:
                p.setfB(0);
                q.setfB(0);
                break;
            case -1:
                p.setfB(1);
                q.setfB(0);
                break;
            case 1:
                p.setfB(0);
                q.setfB(-1);
                break;
        }
        r.setfB(0);
        return r;
    }

    public NodoAVL getRaiz() {
        return raiz;
    }

    public NodoAVL getRaizS() {
        return raizS;
    }

    public NodoAVL getRaizA() {
        return raizA;
    }

    public String Mostrar(int i) {

        String Dato = "";

        if (i == 0) {
            Queue<NodoAVL> queue = new LinkedList<>();
            if (raiz != null) {
                queue.add(raiz);
                NodoAVL a;
                while (!queue.isEmpty()) {
                    a = queue.poll();
                    System.out.print(a.getDato() + ",");
                    Dato = Dato + "\n" + a.getDato();
                    if (a.getLi() != null) {
                        queue.add((NodoAVL) a.getLi());
                    }
                    if (a.getLd() != null) {
                        queue.add((NodoAVL) a.getLd());
                    }
                }
            }
        } else if (i == 1) {
            Queue<NodoAVL> queue = new LinkedList<>();
            if (raizS != null) {
                queue.add(raizS);
                NodoAVL a;
                while (!queue.isEmpty()) {
                    a = queue.poll();
                    System.out.print(a.getDato() + ",");
                    Dato = Dato + "\n" + a.getDato();
                    if (a.getLi() != null) {
                        queue.add((NodoAVL) a.getLi());
                    }
                    if (a.getLd() != null) {
                        queue.add((NodoAVL) a.getLd());
                    }
                }
            }
        } else if (i == 2) {
            Queue<NodoAVL> queue = new LinkedList<>();
            if (raizA != null) {
                queue.add(raizA);
                NodoAVL a;
                while (!queue.isEmpty()) {
                    a = queue.poll();
                    System.out.print(a.getDato() + ",");
                    Dato = Dato + "\n" + a.getDato();
                    if (a.getLi() != null) {
                        queue.add((NodoAVL) a.getLi());
                    }
                    if (a.getLd() != null) {
                        queue.add((NodoAVL) a.getLd());
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "ERROR,No se ha llenado el arbol");
        }

        return Dato;
    }

    public static String pregunta(String mensaje) {

        int seleccion = JOptionPane.showOptionDialog(null, mensaje, "Seleccione una opción", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"si", "no"}, "si");
        if (seleccion != -1) {
            if ((seleccion + 1) == 1) {
                return "si";
            } else {
                return "no";
            }
        }

        return null;
    }

}
