
package FunctionLayer.calculators;

import FunctionLayer.BillOfMaterial;
import FunctionLayer.entity.Carport;
import FunctionLayer.entity.Material;
import FunctionLayer.entity.Roof;
import FunctionLayer.entity.Shed;
import java.util.TreeMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LineItemTypeOfMaterialGeneratorTest {

    LineItemTypeOfMaterialGenerator instance;
    TreeMap<Double, Material> treemap;
    Material material3000;
    Material material3600;
    Material material3900;
    Material material4200;
    Material material4500;
    Material material4800;
    Material material5100;
    Material material5400;
    Material material6000;
//Different roofs
    Roof roof0;
    Roof roof25;
    Roof roof45;
    Roof roofNull;
     

//Different sheds
    
    Shed shedNoLength;
    Shed shedNull;
   
//Different BOMs
    BillOfMaterial bill;

    public LineItemTypeOfMaterialGeneratorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        roof25 = new Roof(25); //In between min and max
        roof45 = new Roof(45);//Max slope
        roofNull = null;

        shedNoLength = new Shed(0.0, 0.0);
        shedNull = null;

        bill = new BillOfMaterial();
        instance = new LineItemTypeOfMaterialGenerator();
        material3000 = new Material(0, "3000", 0, 0, 3000, 0, 0, true, 0, 0);
        material3600 = new Material(0, "3600", 0, 0, 3600, 0, 0, true, 0, 0);
        material4200 = new Material(0, "4200", 0, 0, 4200, 0, 0, true, 0, 0);
        material4500 = new Material(0, "4500", 0, 0, 4500, 0, 0, true, 0, 0);
        material4800 = new Material(0, "4800", 0, 0, 4800, 0, 0, true, 0, 0);
        material5100 = new Material(0, "5100", 0, 0, 5100, 0, 0, true, 0, 0);
        material5400 = new Material(0, "5400", 0, 0, 5400, 0, 0, true, 0, 0);
        material6000 = new Material(0, "6000", 0, 0, 6000, 0, 0, true, 0, 0);

        treemap = new TreeMap();
        treemap.put(3000.0, material3000);
        treemap.put(3600.0, material3600);
        treemap.put(4200.0, material4200);
        treemap.put(4500.0, material4500);
        treemap.put(4800.0, material4800);
        treemap.put(5100.0, material5100);
        treemap.put(5400.0, material5400);
        treemap.put(6000.0, material6000);
        roof0 = new Roof(0);//Min slope
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetBoardForRemSmallCarport() {
        Carport carport  = new Carport(2.4, 2.4, roof0, shedNull, bill);
        //TreeMap<Double, Material> treemap;
        Material expResult = material3000;
        Material result = instance.getBoardForRem(carport, treemap);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetBoardForRemBigCarport() {
        Carport carport  = new Carport(7.5, 7.8, roof0, shedNull, bill);
        Material expResult = material4200;
        Material result = instance.getBoardForRem(carport, treemap);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetBoardForUndersternFrontAndBackBigCarport() {
        Carport carport = new Carport(7.5, 7.8, roof0, shedNull, bill);
        Material expResult = material4200;
        Material result = instance.getBoardForUndersternFrontAndBack(carport, treemap);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetBoardForUndersternFrontAndBackSmallCarport() {
        Carport carport = new Carport(2.4, 2.4, roof0, shedNull, bill);
        Material expResult = material3000;
        Material result = instance.getBoardForUndersternFrontAndBack(carport, treemap);
        assertEquals(expResult, result);
    }
   
    @Test
    public void testGetBoardLengthForOversternAndUndersternSidesSmallCarport() {
        Carport carport  = new Carport(2.4, 2.4, roof0, shedNull, bill);
        Material expResult = material3000;
        Material result = instance.getBoardLengthForOversternAndUndersternSides(carport, treemap);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetBoardLengthForOversternAndUndersternSidesBigCarport() {
        Carport carport  = new Carport(7.5, 7.8, roof0, shedNull, bill);
        Material expResult = material4200;
        Material result = instance.getBoardLengthForOversternAndUndersternSides(carport, treemap);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetBoardForRafterBigCarport() {
        Carport carport  = new Carport(7.5, 7.8, roof0, shedNull, bill);
        Material expResult = material4200;
        Material result = instance.getBoardForRafter(carport, treemap);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetBoardForRafterNotChooseAbleValueForUserBigCarport() {
        Carport carport  = new Carport(7.4, 7.4, roof0, shedNull, bill);
        Material expResult = material4200;
        Material result = instance.getBoardForRafter(carport, treemap);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetBoardForRafterNotChooseAbleValueForUserSmallCarport() {
        Carport carport  = new Carport(3.81, 7.96, roof0, shedNull, bill);
        Material expResult = material4200;
        Material result = instance.getBoardForRafter(carport, treemap);
        assertEquals(expResult, result);
    }
      
    @Test
    public void testGetBoardForRafterSmallCarport() {
        Carport carport  = new Carport(2.4, 2.4, roof0, shedNull, bill);
        Material expResult = material3000;
        Material result = instance.getBoardForRafter(carport, treemap);
        assertEquals(expResult, result);
    }

    @Test
    public void testBoardCalculatorSmallCarport() {
        double carportMeasure = 3000.0;
        Carport carport  = new Carport(2.4, 2.4, roof0, shedNull, bill);
       
        Material expResult = material3000;
        Material result = instance.boardCalculator(carportMeasure, carport, treemap);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testBoardCalculatorBigCarport() {
        double carportMeasure = 7800.0;
        Carport carport  = new Carport(7.8, 7.5, roof0, shedNull, bill);
        Material expResult = material4200;
        Material result = instance.boardCalculator(carportMeasure, carport, treemap);
        assertEquals(expResult, result);
    }  

}
