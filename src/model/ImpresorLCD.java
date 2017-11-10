package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ImpresorLCD {

    /**
     * Caracter vertical
     */
    static final char CARACTER_VERTICAL = '|';
        /**
     * Caracter horizontal
     */
    static final char CARACTER_HORIZONTAL = '-';

        /**
     * Hash que mapea las lineas horizontales que tiene cada numero
     */
    private HashMap<Character,boolean[]> number_x;
    /**
     * Hash que mapea las lineas verticales que tiene cada numero
     */
    private HashMap<Character,boolean[]> number_y;
    
    /**
     * Constructor de la clase, pone en los mapas las lineas de  cada caracter
     * Existen tres lineas horizontales
     * Existen 4 lineas verticales, son identificados de izquierda a derecha y de arriba abajo. 
     */
    public ImpresorLCD() {
       number_x= new HashMap<Character,boolean[]>();
       boolean[] uno_x={false,false,false};
       number_x.put('1', uno_x);
       boolean[] dos_x={true,true,true};
        number_x.put('2', dos_x);
       boolean[] tres_x={true,true,true};
        number_x.put('3', tres_x);
       boolean[] cuatro_x={false,true,false};
        number_x.put('4', cuatro_x);
       boolean[] cinco_x={true,true,true};
        number_x.put('5', cinco_x);
       boolean[] seis_x={true,true,true};
        number_x.put('6', seis_x);
       boolean[] siete_x={true,false,false};
        number_x.put('7', siete_x);
       boolean[] ocho_x={true,true,true};
        number_x.put('8', ocho_x);
       boolean[] nueve_x={true,true,true};
        number_x.put('9', nueve_x);
       boolean[] cero_x={true,false,true};
        number_x.put('0', cero_x);
       
       
       number_y= new HashMap<Character,boolean[]>();
       boolean[] uno_y={false,true,false,true};
       number_y.put('1', uno_y);
       boolean[] dos_y={false,true,true,false};
       number_y.put('2', dos_y);
       boolean[] tres_y={false,true,false,true};
       number_y.put('3', tres_y);
       boolean[] cuatro_y={true,true,false,true};
       number_y.put('4', cuatro_y);
       boolean[] cinco_y={true,false,false,true};
       number_y.put('5', cinco_y);
       boolean[] seis_y={true,false,true,true};
       number_y.put('6', seis_y);
       boolean[] siete_y={false,true,false,true};
       number_y.put('7', siete_y);
       boolean[] ocho_y={true,true,true,true};
       number_y.put('8', ocho_y);
       boolean[] nueve_y={true,true,false,true};
       number_y.put('9', nueve_y);
       boolean[] cero_y={true,true,true,true};
       number_y.put('0', cero_y);
       
    }

    /**
     * Pone en la matriz un caracter
     * @param matriz LA matriz que se va a modificar
     * @param fila La fila de la posicion a modificar
     * @param columna La columna de la poisicion a modificar
     * @param caracter El caracter a cambiar
     */
    private void poner_caracter(char [][] matriz,int fila, int columna, char caracter)
    {
        matriz[fila][columna]=caracter;
    }
    /**
     * Adiciona de cero a tres lineas hoizontales en la matriz
     * @param matriz La matriz a modificar
     * @param pos_incial La columna inicial de las lineas
     * @param size Tamanio de la linea
     * @param lines Vector de lineas a crear
     */
    private void adicionarLineaEnX(char [][] matriz,int pos_incial,int size,boolean[] lines)
    {
        for (int i = 0; i < size; i++) {
            int columna=pos_incial+i+1;
            int fila;
            //Linea superior
            if (lines[0])
                {
                    fila=0;
                    poner_caracter(matriz,fila,columna,CARACTER_HORIZONTAL);
                }
            //Linea del medio
            if (lines[1])
                {
                    fila=1+size;
                    poner_caracter(matriz,fila,columna,CARACTER_HORIZONTAL);
                }
            //Linea inferior
            if (lines[2])
                {
                    fila=sizeFilas(size)-1;
                    poner_caracter(matriz,fila,columna,CARACTER_HORIZONTAL);
                }
        }
        
        
    }
    
    /**
     * Adiciona de cero a cuatro lineas verticales en la matriz
     * @param matriz La matriz a modifica
     * @param pos_incial La columna inicial de las lineas
     * @param size El tamanio de las lineas
     * @param lines Vector de las lineas a crear
     */
    private void adicionarLineaEnY(char [][] matriz,int pos_incial ,int size,boolean[] lines)
    {
        
        for (int i = 0; i < size; i++) {
            int fila;
            int columna;
            //Linea superior izquierda
            if(lines[0])
                {
                    columna=pos_incial;
                    fila=i+1;
                    poner_caracter(matriz,fila,columna,CARACTER_VERTICAL);
                }
            //Linea superior derecha
            if(lines[1])
                {
                    columna=pos_incial+sizeColumnas(size)-1;
                    fila=i+1;
                    poner_caracter(matriz,fila,columna,CARACTER_VERTICAL);
                }
            //Linea inferior izquierda
            if(lines[2])
                {
                    columna=pos_incial;
                    fila=i+size+2;
                    poner_caracter(matriz,fila,columna,CARACTER_VERTICAL);

                }
            //Linea inferior derecha
            if(lines[3])
                {
                    columna=pos_incial+sizeColumnas(size)-1;
                    fila=i+size+2;
                    poner_caracter(matriz,fila,columna,CARACTER_VERTICAL);
                }            
        }
        
    }

    /**
     * Adiciona un digito a la matriz
     * @param numero El numero a adicionar
     * @param matriz La matriz a modificar
     * @param pos_inicial La columna inicial del digito
     * @param size El tamanio de las lineas del digito
     * @return 
     */
    private int adicionarDigito(char numero,char [][] matriz,int pos_inicial,int size) {
        
        boolean[] segments_x=number_x.get(numero);
        boolean[] segments_y=number_y.get(numero);
        
        adicionarLineaEnX(matriz,pos_inicial,size,segments_x);
        adicionarLineaEnY(matriz,pos_inicial,size,segments_y);
        
        return sizeColumnas(size);
    }

    /**
     * Convierte una matriz de caracteres en el resultado a mostrar
     * @param matriz La matriz a convertir
     * @return Un String con las columnas de la matriz y saltos de linea cada vez que empieza una nueva fila
     */
    private String parse_matriz(char[][] matriz)
    {
        String rta="";
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {           
                rta+=matriz[i][j];
            }
                rta+="\n";
        }
        
        return rta;
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
        
        int len_numeros=numeroImp.length();
        int columnas=sizeColumnas(size)*len_numeros+espacio*(len_numeros-1);
        int filas=sizeFilas(size);
       
        char[][] matriz =init_matriz( filas,  columnas);
        
        char[] numeros = numeroImp.toCharArray();
        
        int pos_inicial=0;
        for( int i=0; i<numeros.length;i++)
        {
            char numero=numeros[i];
            pos_inicial+=adicionarDigito(numero,matriz,pos_inicial,size)+espacio;
        }
        return parse_matriz(matriz);
    }
    
    /**
     * Inicializa con espacios una matriz
     * @param filas EL numero de filsa de la matriz
     * @param columnas El numero de columnas de la matriz
     * @return  Una matriz de caracteres con el numero de filas y columnas dadas ppr
     * parametro
     */
    private char[][] init_matriz(int filas, int columnas)
    {
         char[][] matriz= new char[filas][columnas];
        
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matriz[i][j]=' ';
            }
        }
        return matriz;
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
    public static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

}
