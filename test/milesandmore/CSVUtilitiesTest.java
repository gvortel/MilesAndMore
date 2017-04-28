package milesandmore;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author avon
 */
public class CSVUtilitiesTest {
    
    public CSVUtilitiesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of ReadCSV method, of class CSVUtilities.
     */
    @Test
    public void testReadCSV() {
        System.out.println("ReadCSV");
        Passengers expResult = null;
        Passengers result = CSVUtilities.ReadCSV();
        assertNotEquals(expResult, result); 
    }

    /**
     * Test of CSVtoArrayList method, of class CSVUtilities.
     */
    @Test
    public void testCSVtoArrayList() {
        System.out.println("CSVtoArrayList");
        String Line = "Test1,Test2";
        ArrayList<String> expResult = new ArrayList<String>();
        expResult.add("Test1");
        expResult.add("Test2");
        ArrayList<String> result = CSVUtilities.CSVtoArrayList(Line);
        assertEquals(expResult, result);
    }

    /**
     * Test of createCSV method, of class CSVUtilities.
     * @throws java.lang.Exception
     */
    @Test
    public void testCreateCSV() throws Exception {
        System.out.println("createCSV");
        Passengers passengers = null;
        CSVUtilities.createCSV(passengers);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of writeLine method, of class CSVUtilities.
     * @throws java.lang.Exception
     */
    @Test
    public void testWriteLine() throws Exception {
        System.out.println("writeLine");
        List<String> values = null;
        CSVUtilities.writeLine(values);
        // TODO review the generated test code and remove the default call to fail
    }
    
}
