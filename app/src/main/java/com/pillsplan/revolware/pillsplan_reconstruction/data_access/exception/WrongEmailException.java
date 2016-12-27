package com.pillsplan.revolware.pillsplan_reconstruction.data_access.exception;

/**
 * @author Peter Grajcar
 * 20.12.2016
 */

public class WrongEmailException extends ServerException {

    @Override
    public String getMessage() {
        return "Unable to login - account with this email does not exist.";
    }

    public int getCode () {
        return INVALID_ACCOUNT_CODE;
    }

}
