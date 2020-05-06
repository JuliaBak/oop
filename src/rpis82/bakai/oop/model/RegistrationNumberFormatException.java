package rpis82.bakai.oop.model;

import java.util.Objects;

public class RegistrationNumberFormatException extends RuntimeException {

        public RegistrationNumberFormatException(String errorWarning, Objects objects){
            super(errorWarning + objects.toString());
        }

        public RegistrationNumberFormatException(String errorWarning){
            super(errorWarning);
        }

        public RegistrationNumberFormatException(){
            super();
        }
    }




