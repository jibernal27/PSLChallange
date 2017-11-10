package test.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import src.model.ImpresorLCD;

public class ImpresorLCDTest extends TestCase {
	private ImpresorLCD instance;


	   
    /**
     * Reinciar la instancia de mundo antes de cada test
     */
    @Before
    public void setUp() {
        instance = new ImpresorLCD();
    }
    


    /**
     * Prueba que el numero de columnas de un digito sea correcto
     */
    @Test
    public void testSizeColumnas() {
        System.out.println("sizeColumnas");
        int size = 0;
        int expResult = 0;
        int result = 0;
        for (int i = 1; i <= 10; i++) {
            size=i;
            result = instance.sizeColumnas(size);
            expResult=size+2;
            assertEquals(expResult, result);
            
        }
    }

    /**
     * Prueba que el numero de filas de un digito sea correcto
     */
    @Test
    public void testSizeFilas() {
        System.out.println("sizeFilas");
        int size = 0;
        int expResult = 0;
        int result = 0;
        
        for (int i = 1; i <= 10; i++) {
            size=i;
            result = instance.sizeFilas(size);
            expResult=2*size+3;
            assertEquals(expResult, result);
            
        }

    }

    /**
     * Usa datos provenientes de https://www.udebug.com/UVa/706 para asegurar
     * que el procesamiento para los distintos tamanios se correcto
     * TAmbien comprueba que comandos incorrectos sean ignorados
     */
    @Test
    public void testProcesar() {
        System.out.println("procesar");
        
        int espacioDig = 1;
        String result;
        
        for (int i = 1; i <= 10; i++) {
            result = instance.procesar(i+",1234567890", espacioDig);
            assertEquals(read_file("data/test"+i+".txt"), result);
        }
        
        for (int i = 1; i <= 10; i++) {
            result = instance.procesar(i+",1234567890", espacioDig);
            assertNotSame(read_file("data/test11.txt"), result);
        }
        
        try {
            result = instance.procesar("0,1234567890", espacioDig);
            fail("Debe haber una excepcion");
        } catch (Exception e) {
           
        }
        
         try {
            result = instance.procesar("11,1234567890", espacioDig);
            fail("Debe haber una excepcion");
        } catch (Exception e) {
           
        }
        try {
            result = instance.procesar("1,a1234567890", espacioDig);
            fail("Debe haber una excepcion");
        } catch (Exception e) {
           
        }
           
    }

    /**
     * TComprueba que los numeros sean correctamente identificados
     */
    @Test
    public void testIsNumeric() {
        System.out.println("isNumeric");
        boolean result;
        
        for (int i = 0; i < 100; i++) {
         result = ImpresorLCD.isNumeric(i+"");
         assertEquals(true, result);
        }
        
         for (int i = 0; i < 100; i++) {
         result = ImpresorLCD.isNumeric(i+"abcd");
         assertEquals(false, result);
        }
       
    }
    
    /**
     * Metdo auxiliar para acomular todas las lineas de un texto en un string
     * @param fileName El archivo donde esta el texto
     * @return UN string con todas las lineas del texto
     */
    private String read_file(String fileName)
    {
        String rta="";
             try {

            File f = new File(fileName);

            BufferedReader b = new BufferedReader(new FileReader(f));

            String readLine = "";
            while ((readLine = b.readLine()) != null) {
               rta+=readLine+"\n";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

                return rta;
    }
    
}
