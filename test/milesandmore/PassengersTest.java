/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package milesandmore;

import java.util.Map;
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
public class PassengersTest {
    
    public PassengersTest() {
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
     * Test of getMembers method, of class Passengers.
     */
    @Test
    public void testGetMembers() {
        System.out.println("getMembers");
        Passengers instance = new Passengers();
        Map<Long, Integer> expResult = null;
        Map<Long, Integer> result = instance.getMembers();
        assertNotEquals(expResult, result); 
    }

    /**
     * Test of addMember method, of class Passengers.
     */
    @Test
    public void testAddMember_long() {
        System.out.println("addMember");
        long MemberID = 1234567890L;
        Passengers instance = new Passengers();
        instance.addMember(MemberID);
    }

    /**
     * Test of containsMember method, of class Passengers.
     */
    @Test
    public void testContainsMember() {
        System.out.println("containsMember");
        long MemberID = 1234567890L;
        Passengers instance = new Passengers();
        instance.addMember(MemberID);
        boolean expResult = true;
        boolean result = instance.containsMember(MemberID);
        assertEquals(expResult, result);
    }

    /**
     * Test of addMember method, of class Passengers.
     */
    @Test
    public void testAddMember_long_int() {
        System.out.println("addMember");
        long memberID = 1234567890L;
        int miles = 1500;
        Passengers instance = new Passengers();
        instance.addMember(memberID, miles);
    }

    /**
     * Test of deleteMember method, of class Passengers.
     */
    @Test
    public void testDeleteMember() {
        System.out.println("deleteMember");
        long memberID = 1234567890L;
        Passengers instance = new Passengers();
        instance.addMember(memberID);
        instance.deleteMember(memberID);
    }

    /**
     * Test of updateMiles method, of class Passengers.
     */
    @Test
    public void testUpdateMiles() {
        System.out.println("updateMiles");
        long memberID = 1234567890L;
        int miles = 2000;
        Passengers instance = new Passengers();
        instance.addMember(memberID);
        int expResult = 3000;
        int result = instance.updateMiles(memberID, miles);
        assertEquals(expResult, result);
    }

    /**
     * Test of getMiles method, of class Passengers.
     */
    @Test
    public void testGetMiles() {
        System.out.println("getMiles");
        long MemberID = 1234567890L;
        Passengers instance = new Passengers();
        instance.addMember(MemberID);
        int expResult = 1000;
        int result = instance.getMiles(MemberID);
        assertEquals(expResult, result);
    }

    /**
     * Test of getSize method, of class Passengers.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        Passengers instance = new Passengers();
        int expResult = 0;
        int result = instance.getSize();
        assertEquals(expResult, result);
    }

    
    
}
