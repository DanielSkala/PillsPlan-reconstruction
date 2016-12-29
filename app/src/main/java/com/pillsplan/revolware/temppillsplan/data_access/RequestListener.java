package com.pillsplan.revolware.temppillsplan.data_access;

import com.pillsplan.revolware.temppillsplan.data_access.exception.ServerException;

/**
 * @author Peter Grajcar
 * 27.12.2016
 */

public interface RequestListener {

    public void onError(ServerException e);
    public void onSuccess(Object obj);


}
