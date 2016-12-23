package com.pillsplan.revolware.pillsplan_reconstruction.data_access.exception;

/**
 * Created by Peter on 23. 12. 2016.
 */

public class AttemptLimitExceededException extends ServerException {

    @Override
    public String getMessage() {
        return "Unable to login - exceeded maximal number of attempts.";
    }

}