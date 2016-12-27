package com.pillsplan.revolware.pillsplan_reconstruction.data_access.exception;

/**
 * @author Peter Grajcar
 * 20.12.2016
 */

public class InvalidCredentialsException extends ServerException {

    @Override
    public String getMessage() {
        return "Unable to login - invalid credentials.";
    }

    public int getCode () {
        return INVALID_CREDENTIALS_CODE;
    }

}
