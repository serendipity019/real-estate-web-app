package gr.aueb.cf.realestateapp.core.exceptions;

public class AppObjectNotAuthorizedException extends AppGenericException {
    private static final String DEFAULT_CODE = "NotAuthorized";

    public AppObjectNotAuthorizedException(String code, String message) {
        super(code + DEFAULT_CODE, message);
    }
}
