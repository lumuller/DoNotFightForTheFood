package donotfightforthefood.dao;

import donotfightforthefood.models.Restaurant;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luana Müller
 */
public class RestaurantDao {
   
    /**
     * Text file which persists the data about the restaurants
     */
    private final String fileName = "restaurantData.txt";
    
    /***
     * Clean the employee registers
     * @throws FileNotFoundException 
     */
    public void cleanFiles() throws FileNotFoundException{        
        PrintWriter dataFile;
        
        dataFile = new PrintWriter(fileName); 
        dataFile.close();     
    }
    
   /***
     * 
     * @return a HashMap containing the informations about the registered restaurants. The key is the name of the restaurant, provided during the registration.
     */
    public HashMap<String, Restaurant> loadRestaurantList(){
        HashMap<String, Restaurant>  returnMap = new HashMap<>();
        try{            
            File arq = new File(fileName);
            Scanner dataFile = new Scanner(arq);
            
            while(dataFile.hasNext()){
                String line = dataFile.nextLine();
                String[] splitedLine = line.split(";");
                returnMap.put(splitedLine[0],new Restaurant(splitedLine[0],Double.parseDouble(splitedLine[1]),splitedLine[2]));                
            }
            return returnMap;
        }catch(FileNotFoundException e){
            System.out.println("Erro ao carregar dados");
        }
        return returnMap;
    }
    
    /***
     * 
     * @param rest restaurant informations to be registered
     * @return a boolen, representing whether the record was included or not. 
     */
    public boolean addNewRestaurant(Restaurant rest){
        PrintWriter dataFile;
        try {           
            HashMap<String, Restaurant> restaurantList = loadRestaurantList();
            if(restaurantList == null || !restaurantList.keySet().contains(rest.getRestName().toUpperCase())){
                dataFile = new PrintWriter(new FileWriter(fileName, true));        
                dataFile.println(rest.toString());  
                dataFile.close();
                return true;
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("Erro ao carregar dados");
        } catch (IOException ex) {
           Logger.getLogger(RestaurantDao.class.getName()).log(Level.SEVERE, null, ex);
       }
        return false;
    } 
}
