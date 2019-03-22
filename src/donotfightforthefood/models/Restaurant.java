package donotfightforthefood.models;

/**
 *
 * @author Luana Müller
 */
public class Restaurant {
    
    private String restName;
    private double averagePrice;
    private String typeOfFood;

    public Restaurant(String restName, double averagePrice, String typeOfFood) {
        this.restName = restName;
        this.averagePrice = averagePrice;
        this.typeOfFood = typeOfFood;
    }

    public String getRestName() {
        return restName;
    }

    public void setRestName(String restName) {
        this.restName = restName;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public String getTypeOfFood() {
        return typeOfFood;
    }

    public void setTypeOfFood(String typeOfFood) {
        this.typeOfFood = typeOfFood;
    }
    @Override
    public String toString(){
        return restName+";"+averagePrice+";"+typeOfFood;
    }
    
    
}
