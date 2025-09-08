package gr.aueb.cf.realestateapp.core.exceptions;

import lombok.Getter;

@Getter
public class AppGenericException extends Exception{
    private final String code;

    public AppGenericException(String code, String message) {
        super(message);
        this.code = code;
    }
}
