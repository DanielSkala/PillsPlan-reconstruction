package com.pillsplan.revolware.pillsplan_reconstruction.data_access.user;

import com.pillsplan.revolware.pillsplan_reconstruction.data_access.exception.ServerException;

/**
 * Created by Peter on 27. 12. 2016.
 */

public interface LoginListener {

    public void onError(ServerException e);
    public void onSuccess(String token);

}
