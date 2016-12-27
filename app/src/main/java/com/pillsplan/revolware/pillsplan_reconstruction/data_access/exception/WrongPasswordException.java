package com.pillsplan.revolware.pillsplan_reconstruction.data_access.exception;

/**
 * @author Peter Grajcar
 * 20.12.2016
 */

public class WrongPasswordException extends ServerException {

    @Override
    public String getMessage() {
        return "Unable to login - wrong password.";
    }

    public int getCode () {
        return INVALID_PASSWORD_CODE;
    }

}
