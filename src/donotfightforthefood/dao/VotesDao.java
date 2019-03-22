package donotfightforthefood.dao;

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
public class VotesDao {
    /**
     * Text file which persists the data about the 5 last winnersList
     */
    private final String fileNameWinners = "winnersOfTheWeek.txt";
    /**
     * Text file which persists the data about the votes received by restaurants
     */
    private final String fileDayVotesRestaurant = "votesOfTheDayRestaurants.txt";
    /**
     * Text file which persists the data about the employees who already voted in the current votation
     */
    private final String fileDayVotesEmployees = "votesOfTheDayEmployees.txt";
    
    
    /***
     * Clean the files about the actual votation
     * @throws FileNotFoundException 
     */
    public void cleanFiles() throws FileNotFoundException{        
        PrintWriter dataFile;
        
        dataFile = new PrintWriter(fileDayVotesRestaurant); 
        dataFile.close();
        
        dataFile = new PrintWriter(fileDayVotesEmployees); 
        dataFile.close();        
    }
    
    /***
     * 
     * @return a String containing the information of the winners of the week.
     * @throws FileNotFoundException 
     */
    public String winnersList() {
        File arq = new File(fileNameWinners);
        Scanner dataFile;
        StringBuilder sb = new StringBuilder();
        int totalWinners = 0;
        try {
            dataFile = new Scanner(arq);              
            while(dataFile.hasNext()){
                sb.append(dataFile.nextLine()).append("\n");
                totalWinners++;
            }
            dataFile.close();
            
             /*In order to not repeat the same restaurant during the week, I established that each 5 days (a working week) the winnersList list will be cleaned.*/
            if(totalWinners==5){            
                PrintWriter writer = new PrintWriter(fileNameWinners); 
                writer.close(); 
                return "";
            } else {
                return sb.toString();  
            } 
            
        } catch (FileNotFoundException ex) {
            return "";
        }
        
       
    }
    
    /***
     * Count the votes for the restaurants and return the name of the winner
     * 
     * @return String the name of the restaurant which won the current votation
     * @throws FileNotFoundException 
     */
    public String checkWinner(){
        try{ 
            HashMap<String,Integer> votes = new HashMap<>();
            File arq = new File(fileDayVotesRestaurant);
            Scanner dataFile = new Scanner(arq);
            while(dataFile.hasNext()){
                String restaurantName = dataFile.nextLine();
                if(votes.get(restaurantName) == null){
                    votes.put(restaurantName, 1);
                } else {
                    votes.put(restaurantName, votes.get(restaurantName) + 1);
                }
            }
                        
            String mostVoted = votes.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
            
            PrintWriter writer = new PrintWriter(new FileWriter(fileNameWinners, true));            
            writer.println(mostVoted);
            writer.close();
            
            //check the winner is a definitive action. After vote counting, the system will consider the votation finished and reset the files to perform a new votation.
            cleanFiles();
            
            return mostVoted;
            
        }catch(IOException e){
            System.out.println("Erro ao carregar dados");
        }
        return null;
       
    }
    /***
     * Checks if the employee already voted in the current votation
     * @param empID the secret ID of the employee who is voting.
     * @return a boolean representing the existence (or not) of a vote from the employee in the current votation.
     */
    public boolean voteByEmployeeExists(String empID){
        try{           
            File arq = new File(fileDayVotesEmployees);
            
            Scanner dataFile = new Scanner(arq);
            while(dataFile.hasNext()){
                if(dataFile.nextLine().equals(empID)){
                    return true;
                }               
            }

        }catch(FileNotFoundException e){
            //
        }
        return false;
    }
    
    /***
     * Adds a new vote in the current votation
     * 
     * @param secretId Secret ID of the Employee who is voting.
     * @param restName Name of the restaurant     
     */
    public boolean addNewVote(String secretId, String restName){        
        PrintWriter dataFile;      
        try {           
            if(voteByEmployeeExists(secretId)){
                //this person already voted.
                return false;
            } else {             
               dataFile = new PrintWriter(new FileWriter(fileDayVotesRestaurant, true)); 
               dataFile.println(restName);
               dataFile.close();

               dataFile = new PrintWriter(new FileWriter(fileDayVotesEmployees, true)); 
               dataFile.println(secretId);
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
