package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ImpresorLCD {

    static final char CARACTER_VERTICAL = '|';
    static final char CARACTER_HORIZONTAL = '-';
    static final String POSICION_X = "X";
    static final String POSICION_Y = "Y";
    
    private HashMap<Character,boolean[]> number_x;
    private HashMap<Character,boolean[]> number_y;
    
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


    private void poner_caracter(char [][] matriz,int fila, int columna, char caracter)
    {
        matriz[fila][columna]=caracter;
    }

    private void adicionarLineaEnX(char [][] matriz,int pos_incial,int size,boolean[] lines)
    {
        for (int i = 0; i < size; i++) {
            int columna=pos_incial+i+1;
            int fila;
            if (lines[0])
                {
                    fila=0;
                    poner_caracter(matriz,fila,columna,CARACTER_HORIZONTAL);
                }
            if (lines[1])
                {
                    fila=1+size;
                    poner_caracter(matriz,fila,columna,CARACTER_HORIZONTAL);
                }
            if (lines[2])
                {
                    fila=sizeFilas(size)-1;
                    poner_caracter(matriz,fila,columna,CARACTER_HORIZONTAL);
                }
        }
        
        
    }
    
    private void adicionarLineaEnY(char [][] matriz,int pos_incial ,int size,boolean[] lines)
    {
        
        for (int i = 0; i < size; i++) {
            int fila;
            int columna;
            if(lines[0])
                {
                    columna=pos_incial;
                    fila=i+1;
                    poner_caracter(matriz,fila,columna,CARACTER_VERTICAL);
                }
            if(lines[1])
                {
                    columna=pos_incial+sizeColumnas(size)-1;
                    fila=i+1;
                    poner_caracter(matriz,fila,columna,CARACTER_VERTICAL);
                }
            if(lines[2])
                {
                    columna=pos_incial;
                    fila=i+size+2;
                    poner_caracter(matriz,fila,columna,CARACTER_VERTICAL);

                }
            if(lines[3])
                {
                    columna=pos_incial+sizeColumnas(size)-1;
                    fila=i+size+2;
                    poner_caracter(matriz,fila,columna,CARACTER_VERTICAL);
                }            
        }
        
    }

    private int adicionarDigito(char numero,char [][] matriz,int pos_inicial,int size) {
        
        boolean[] segments_x=number_x.get(numero);
        boolean[] segments_y=number_y.get(numero);
        
        adicionarLineaEnX(matriz,pos_inicial,size,segments_x);
        adicionarLineaEnY(matriz,pos_inicial,size,segments_y);
        
        return sizeColumnas(size);
    }

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
