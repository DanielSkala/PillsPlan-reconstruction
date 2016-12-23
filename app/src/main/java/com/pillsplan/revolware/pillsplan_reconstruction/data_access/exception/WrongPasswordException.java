package com.pillsplan.revolware.pillsplan_reconstruction.data_access.exception;

/**
 * Created by Peter on 23. 12. 2016.
 */

public class WrongPasswordException extends ServerException {

    @Override
    public String getMessage() {
        return "Unable to login - wrong password.";
    }

}
