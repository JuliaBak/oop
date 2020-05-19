package rpis82.bakai.oop.model;

import java.lang.Object;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Vehicle implements Cloneable{

    public static final Vehicle EMPTY_VEHICLE = new Vehicle(" ", " ", " ", VehiclesTypes.NONE);

        public String number = "A654BM64";

    private final String registrationNumber, manufacturer, model;
    private final VehiclesTypes type;

    public Vehicle() throws RegistrationNumberFormatException{
        this.manufacturer = EMPTY_VEHICLE.manufacturer;
        this.model = EMPTY_VEHICLE.model;
        this.registrationNumber = EMPTY_VEHICLE.registrationNumber;
        this.type = EMPTY_VEHICLE.type;
    }

    public Vehicle (String registrationNumber, String manufacturer, String model, VehiclesTypes vehicleType) throws RegistrationNumberFormatException, NullPointerException {


        this.manufacturer =   Objects.requireNonNull( manufacturer,"Manufacture is null");
        this.model =   Objects.requireNonNull( model,"Model  is null");
        this.type =   Objects.requireNonNull( vehicleType,"Type is null");

        this.registrationNumber = checkRegNumberFormat(number);//(registrationNumber);

    }

    public String checkRegNumberFormat(String registrationNumber) throws RegistrationNumberFormatException
    {
        Objects.requireNonNull(registrationNumber, "Reg Number is null");

        Pattern pattern = Pattern.compile("[ABEKMHOPCTYX][0-9]{3}[ABEKMHOPCTYX]{2}[0-9]{2,3}"); // Корректный формат регистрационного номера:
        //<буква><цифра><цифра><цифра><буква><буква><2-3 цифры>
        Matcher matcherNumber = pattern.matcher(registrationNumber);
        if ( matcherNumber.matches() )
        {
            return registrationNumber;
        }
        else { throw  new RegistrationNumberFormatException("RegNumber has wrong format");}
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public VehiclesTypes getType() {
        return type;
    }

    //Lab4
    @Override
    public String toString() {
        if (type == VehiclesTypes.NONE)
        {return "NONE";}
        else
        {
            return String.format("<%s> <%s> (<%s>) regNumber: <%s>", this.manufacturer,
                    this.model,
                    this.type,
                    this.registrationNumber);
        }
    }

    @Override
    public int hashCode()
    {
        return model.hashCode() &  manufacturer.hashCode() &  registrationNumber.hashCode() &  type.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vehicle vehicleObj = (Vehicle) obj;
        return
                this.type.equals(vehicleObj.type) &&
                        this.registrationNumber.equals(vehicleObj.registrationNumber) &&
                        this.manufacturer.equals(vehicleObj.manufacturer) &&
                        this.model.equals(vehicleObj.model);
    }

    @Override
    public Vehicle clone() throws CloneNotSupportedException
    {
        return (Vehicle) super.clone();
    }

}



