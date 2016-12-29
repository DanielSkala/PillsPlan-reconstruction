package com.pillsplan.revolware.temppillsplan.data_access.exception;

/**
 * Created by Peter on 27. 12. 2016.
 */

public class InternalErrorException extends ServerException {
    @Override
    public String getMessage() {
        return "Internal server error.";
    }

    @Override
    public int getCode() {
        return INTERNAL_ERROR_CODE;
    }
}
