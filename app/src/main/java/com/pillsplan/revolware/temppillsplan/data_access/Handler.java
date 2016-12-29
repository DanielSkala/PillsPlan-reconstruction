package com.pillsplan.revolware.temppillsplan.data_access;

import java.util.List;

/**
 * @author Peter Grajcar
 * 14.12.2016
 *
 * Abstract generic Handler class. Inherited classes are meant to handle multiple sources of data.
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
