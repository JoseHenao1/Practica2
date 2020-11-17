package practica2;

import ArbolBinarioBusquedaAVL.ArbolAVL;
import java.io.BufferedReader;
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
        if (super.buscar(dato) == null) {
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

    public void download() {
        try {
            FileWriter output = new FileWriter("importar.txt");
            for (int i = 0; i < this.getCantidadp() + getCantidad(); i++) {
                output.write(this.Mostrar(0));
            }
            output.close();
        } catch (IOException ex) {
            System.out.println("ERROR");
        }
    }

    public void buscar(String dato) {
        if (super.buscar((Comparable) dato) == null) {
            textAResultado.setText(" La palabra " + dato + " No se encuentra");
        } else {
            textAResultado.setText(" Urra!!! Palabra encontrada ");
        }

    }
}
