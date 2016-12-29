package com.pillsplan.revolware.temppillsplan.data_access;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Peter Grajcar
 * 14.12.2016
 *
 * Special URI class with template resolving capability.
 */

public class URI {
    private String path;

    public URI (String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    /**
     *
     * @param name template identifier
     * @param value value which will replace the template
     * @return new URI with replaced template
     */
    public URI resolveTemplate(String name, String value) {
        return new URI(getPath().replace("{" + name + "}", value));
    }

    /**
     *
     * @return URL Object
     * @throws MalformedURLException
     */
    public URL getURL () throws MalformedURLException {
        return new URL(getPath());
    }
}
