package rpis82.bakai.oop.model;

import java.util.Objects;

public class NoRentedSpaceException extends Exception{
    public NoRentedSpaceException() {
        super();
    }

    public NoRentedSpaceException(String errorWarning) {
        super(errorWarning);
    }

    public NoRentedSpaceException(String errorWarning, Objects exceptionObject) {
        super(errorWarning + "Object:" + exceptionObject.toString());
    }
}
