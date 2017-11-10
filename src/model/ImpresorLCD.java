import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImpresorLCD {

    static final String CARACTER_VERTICAL = "|";
    static final String CARACTER_HORIZONTAL = "-";
    static final String POSICION_X = "X";
    static final String POSICION_Y = "Y";


    public ImpresorLCD() {

    }

    /**
     *
     * Metodo encargado de añadir una linea a la matriz de Impresion
     *
     * @param matriz Matriz Impresion
     * @param punto Punto Pivote
     * @param posFija Posicion Fija
     * @param size Tamaño Segmento
     * @param caracter Caracter Segmento
     */    
    private void adicionarLinea(String[][] matriz, int[] punto, String posFija,
            int size, String caracter) {

        if (posFija.equalsIgnoreCase(POSICION_X)) 
        {
            for (int y = 1; y <= size; y++) 
            {
                int valor = punto[1] + y;
                matriz[punto[0]][valor] = caracter;
            }
        } 
        else 
        {
            for (int i = 1; i <= size; i++) 
            {
                int valor = punto[0] + i;
                matriz[valor][punto[1]] = caracter;
            }
        }
    }

    /**
     *
     * Metodo encargado de un segmento a la matriz de Impresion
     *
     * @param segmento Segmento a adicionar
     */  
    private void adicionarSegmento(int segmento) {
    }

    /**
     *
     * Metodo encargado de definir los segmentos que componen un digito y
     * a partir de los segmentos adicionar la representacion del digito a la matriz
     *
     * @param numero Digito
     */
    private void adicionarDigito(int numero) {
    }

    /**
     *
     * Metodo encargado de imprimir un numero
     *
     * @param size Tamaño Segmento Digitos
     * @param numeroImp Numero a Imprimir
     * @param espacio Espacio Entre digitos
     */    
    private String imprimirNumero(int size, String numeroImp, int espacio) 
    {
        String rta="";
        int columnas=sizeColumnas(size),filas=sizeFilas(size);
        char[][] matriz= new char[filas][columnas];
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j]=' ';
            }
        }
        
        return rta;
    }
    
    /**
     * REtorna el numero de columnas que debe ocupar un numero
     * @param size El tamanio del numero
     * @return numero de columnas a ocupar
     */
    
    public int sizeColumnas(int size)
    {
        return size+2;
    }

     /**
     * REtorna el numero de filas que debe ocupar un numero
     * @param size El tamanio del numero
     * @return numero de filas a ocupar
     */
    
    public int sizeFilas(int size)
    {
        return 2*size+3;
    }
    
     /**
     *
     * Metodo encargado de procesar la entrada que contiene el size del segmento
     * de los digitos y los digitos a imprimir
     *
     * @param comando Entrada que contiene el size del segmento de los digito
     * y el numero a imprimir
     * @param espacioDig Espacio Entre digitos
     */  
    public String procesar(String comando, int espacioDig) {
        
        String[] parametros;
        
        int tam;

        if (!comando.contains(",")) {
            throw new IllegalArgumentException("Cadena " + comando
                    + " no contiene caracter ,");
        }
        
        //Se hace el split de la cadena
        parametros = comando.split(",");
        
        //Valida la cantidad de parametros
        if(parametros.length>2)
        {
           throw new IllegalArgumentException("Cadena " + comando
                    + " contiene mas caracter ,"); 
        }
        
        //Valida la cantidad de parametros
        if(parametros.length<2)
        {
           throw new IllegalArgumentException("Cadena " + comando
                    + " no contiene los parametros requeridos"); 
        }
        
        //Valida que el parametro size sea un numerico
        if(isNumeric(parametros[0]))
        {
            tam = Integer.parseInt(parametros[0]);
            
            // se valida que el size este entre 1 y 10
            if(tam <1 || tam >10)
            {
                throw new IllegalArgumentException("El parametro size ["+tam
                        + "] debe estar entre 1 y 10");
            }
        }
        else
        {
            throw new IllegalArgumentException("Parametro Size [" + parametros[0]
                    + "] no es un numero");
        }

        // Realiza la impresion del numero
        return imprimirNumero(tam, parametros[1],espacioDig);

    }

    /**
     *
     * Metodo encargado de validar si una cadena es numerica
     *
     * @param cadena Cadena
     */  
    static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

}
