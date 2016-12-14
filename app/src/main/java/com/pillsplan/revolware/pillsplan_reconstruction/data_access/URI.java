package com.pillsplan.revolware.pillsplan_reconstruction.data_access;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Peter on 14. 12. 2016.
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

    public URI resolveTemplate(String name, String value) {
        return new URI(getPath().replace("{" + name + "}", value));
    }
    public URL getURL () throws MalformedURLException {
        return new URL(getPath());
    }
}
