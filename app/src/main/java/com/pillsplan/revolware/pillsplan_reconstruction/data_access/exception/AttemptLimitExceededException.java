package com.pillsplan.revolware.pillsplan_reconstruction.data_access.exception;

/**
 * @author Peter Grajcar
 * 20.12.2016
 */

public class AttemptLimitExceededException extends ServerException {

    @Override
    public String getMessage() {
        return "Unable to login - exceeded maximal number of attempts.";
    }

    @Override
    public int getCode() {
        return 0;
    }
}