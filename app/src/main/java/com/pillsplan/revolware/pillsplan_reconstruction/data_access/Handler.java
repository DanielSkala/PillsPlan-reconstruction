/*******************************************************************************
 * Author: Peter Grajcar
 * 2016
 *******************************************************************************/
package com.pillsplan.revolware.pillsplan_reconstruction.data_access;

import java.util.List;

/**
 * Created by Peter on 14. 12. 2016.
 */

public abstract class Handler<T> {

    public T get(T obj) {
        return null;
    }
    public List<T> getAll(T obj) {
        return null;
    }
    public void create(T obj) {}
    public void update(T obj) {}
    public void delete(T obj) {}

}
