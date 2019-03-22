package donotfightforthefood.models;

/**
 *
 * @author Luana Müller
 */
public class Employee {
    
    private String empID;
    private String empName;

    public Employee(String empID, String empName) {
        this.empID = empID;
        this.empName = empName;
    }
    
    public String getEmpID() {
        return empID;
    }
    public void setEmpID(String empID) {
        this.empID = empID;
    }
    public String getEmpName() {
        return empName;
    }
    public void setEmpName(String empName) {
        this.empName = empName;
    } 
    @Override
    public String toString() {
        return empID+";"+empName;
    }
    
    
}
