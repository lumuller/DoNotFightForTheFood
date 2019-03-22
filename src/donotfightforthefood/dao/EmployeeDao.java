/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package donotfightforthefood.dao;

import donotfightforthefood.models.Employee;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Luana Müller
 */
public class EmployeeDao {
    /**
     * Text file which persists the data about the employees
     */
    private final String fileName = "employeeData.txt";
    
        
    /***
     * Clean the restaurant registers
     * @throws FileNotFoundException 
     */
    public void cleanFiles() throws FileNotFoundException{        
        PrintWriter dataFile;
        
        dataFile = new PrintWriter(fileName); 
        dataFile.close();     
    }    
    /***
     * 
     * @return a HashMap containing the informations about the registered employees. The key is the secret ID provided by the employee during the registration.
     */
    public HashMap<String,String> loadEmployeeList(){
        HashMap<String,String> returnMap = new HashMap<>();
        try{            
            File arq = new File(fileName);
            Scanner dataFile = new Scanner(arq);
            while(dataFile.hasNext()){
                String line = dataFile.nextLine();
                String[] splitedLine = line.split(";");
                returnMap.put(splitedLine[0],splitedLine[1]);                
            }
            return returnMap;
        }catch(FileNotFoundException e){
            System.out.println("Erro ao carregar dados");
        }
        return returnMap;
    }
    
    /***
     * 
     * @param emp employee informations to be registered
     * @return a boolen, representing whether the record was included or not. 
     */
    public boolean addNewEmployee(Employee emp){
        PrintWriter dataFile;
        try {
            HashMap<String,String> employeeList = loadEmployeeList();
            
            if(employeeList == null || !employeeList.keySet().contains(emp.getEmpID())){
                dataFile = new PrintWriter(new FileWriter(fileName, true));
                dataFile.println(emp.toString());  
                dataFile.close();
                return true;
            } 
            
        } catch (FileNotFoundException ex) {
            System.out.println("Erro ao carregar dados");
        } catch (IOException ex) {
            System.out.println("Erro ao carregar dados");
        }
        return false;
    }
}
