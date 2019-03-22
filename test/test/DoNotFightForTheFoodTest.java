/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import donotfightforthefood.dao.EmployeeDao;
import donotfightforthefood.dao.RestaurantDao;
import donotfightforthefood.dao.VotesDao;
import donotfightforthefood.models.Employee;
import donotfightforthefood.models.Restaurant;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Luana Müller
 */
public class DoNotFightForTheFoodTest {
    
    private static EmployeeDao employees;
    private static RestaurantDao restaurants;
    private static VotesDao votes;
    
    public DoNotFightForTheFoodTest() {
        employees = new EmployeeDao();
        restaurants = new RestaurantDao();
        votes = new VotesDao();
    }
    
    @BeforeClass
    public static void setUpClass() {        
    
    }
    
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        try {
            employees.cleanFiles();
            restaurants.cleanFiles();
            votes.cleanFiles();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DoNotFightForTheFoodTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        employees.addNewEmployee(new Employee("abc123", "Employee One"));
        employees.addNewEmployee(new Employee("abc456", "Employee Two"));
        employees.addNewEmployee(new Employee("abc789", "Employee Three"));
        employees.addNewEmployee(new Employee("def123", "Employee Four"));
        employees.addNewEmployee(new Employee("def456", "Employee Five"));
        employees.addNewEmployee(new Employee("def789", "Employee Six"));


        restaurants.addNewRestaurant(new Restaurant("Restaurant One", 25.00, "Food"));
        restaurants.addNewRestaurant(new Restaurant("Restaurant Two", 30.00, "Food"));
        restaurants.addNewRestaurant(new Restaurant("Restaurant Three", 35.00, "Food"));
        restaurants.addNewRestaurant(new Restaurant("Restaurant Four", 40.00, "Food"));
        restaurants.addNewRestaurant(new Restaurant("Restaurant Five", 45.00, "Food"));
        restaurants.addNewRestaurant(new Restaurant("Restaurant Six", 50.00, "Food"));
        
        
        votes.addNewVote("abc123", "Restaurant One");
        votes.addNewVote("abc456", "Restaurant One");
        votes.addNewVote("abc789", "Restaurant Two");
        votes.addNewVote("def123", "Restaurant Three");
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Registering a new employee
     */
    @Test
    public void addValidEmployee(){
        assertTrue(employees.addNewEmployee(new Employee("ghi123", "Employee Seven")));
    }
    
    /**
     * Registering a new employee with a repeated secret ID
     */
    @Test
    public void addInvalidEmployee(){        
        assertFalse(employees.addNewEmployee(new Employee("abc123", "Employee Seven")));
    }
    
    /**
     * Registering a new vote
     */
    @Test
    public void addValidVote(){
        assertTrue(votes.addNewVote("def789", "Restaurant Six"));
    }
    
    /**
     * Registering a new vote for a employee that already voted
     */
    @Test
    public void addInvalidVote(){
        assertFalse(votes.addNewVote("abc123", "Restaurant One"));
    }
    
    /**
     * Retrieving the result of the votation
     */
    @Test
    public void votationResult(){
        assertEquals("Restaurant One", votes.checkWinner());
    }
}
