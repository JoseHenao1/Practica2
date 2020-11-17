package practica2;

import ArbolBinarioBusquedaAVL.ArbolAVL;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTextArea;
/**
 *
 * @author user
 */
public class Diccionario extends ArbolAVL {

    private int cantidad;
    private final JTextArea textAResultado;

    Diccionario(JTextArea jTextAreaResultado) throws IOException, Exception {
        this.cantidad = 0;
        this.textAResultado = jTextAreaResultado;
        load();
    }

    public void insertar(Comparable dato) throws Exception {
        //if (super.buscar(dato) == null) {
        if (super.buscarComplejo(dato,0) == null) {
            if (super.insertarPalabraS(dato) == null) {
                throw new Exception("Error al insertar la palabra " + dato);
            }
        }
    }

    public int getCantidad() {
        return cantidad;
    }

    public final void load() throws FileNotFoundException, IOException, Exception {
        BufferedReader in = new BufferedReader(new FileReader("importar.txt"));
        String linea;
        while ((linea = in.readLine()) != null) {
            linea = (linea.split("\\/"))[0];
            insertar(linea);
        }
    }

    public void download()throws FileNotFoundException, IOException, Exception {
        BufferedWriter out = new BufferedWriter(new FileWriter("importar.txt"));
        out.write(this.Mostrar(0)+"si llegue");
        out.close();
    }

    public void buscar(String dato) {
        //if (super.buscar((Comparable) dato) == null) {
        if (super.buscarComplejo((Comparable) dato,0) == null) {
            textAResultado.setText(" La palabra " + dato + " No se encuentra");
        } else {
            textAResultado.setText(" Urra!!! Palabra encontrada ");
        }

    }
}
