/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jairo
 */
public class ImpresorLCDTest {
            ImpresorLCD instance;

    
    public ImpresorLCDTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new ImpresorLCD();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of sizeColumnas method, of class ImpresorLCD.
     */
    @Test
    public void testSizeColumnas() {
        System.out.println("sizeColumnas");
        int size = 0;
        int expResult = 0;
        int result = 0;
        for (int i = 0; i < 100; i++) {
            size=i;
            result = instance.sizeColumnas(size);
            expResult=size+2;
            assertEquals(expResult, result);
            
        }
    }

    /**
     * Test of sizeFilas method, of class ImpresorLCD.
     */
    @Test
    public void testSizeFilas() {
        System.out.println("sizeFilas");
        int size = 0;
        int expResult = 0;
        int result = 0;
        
        for (int i = 0; i < 100; i++) {
            size=i;
            result = instance.sizeFilas(size);
            expResult=2*size+3;
            assertEquals(expResult, result);
            
        }

    }

    /**
     * Test of procesar method, of class ImpresorLCD.
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
            assertNotEquals(read_file("data/test11.txt"), result);
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
     * Test of isNumeric method, of class ImpresorLCD.
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
