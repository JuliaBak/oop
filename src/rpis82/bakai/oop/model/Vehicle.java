package rpis82.bakai.oop.model;

public class Vehicle {
    int gosNumber;
    String producer, model;

    public Vehicle()
    {

    }
    public Vehicle(int gosNumber, String producer, String model)
    {
        this.gosNumber = gosNumber;
        this.producer = producer;
        this.model = model;
    }

    public int getGosNumber() {
        return gosNumber;
    }

    public void setGosNumber(int gosNumber) {
        this.gosNumber = gosNumber;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
