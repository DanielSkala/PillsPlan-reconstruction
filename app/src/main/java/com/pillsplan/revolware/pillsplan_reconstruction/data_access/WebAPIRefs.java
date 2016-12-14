package com.pillsplan.revolware.pillsplan_reconstruction.data_access;

/**
 * Created by Peter on 14. 12. 2016.
 */

public enum WebAPIRefs {
    LOGIN("http://hrabovec.hopto.org:7070/api/login"),
    USER("http://hrabovec.hopto.org:7070/api/user/{userId}"),
    ALARM("http://hrabovec.hopto.org:7070/api/user/{userId}/alarm/{alarmId}"),
    ALARMS("http://hrabovec.hopto.org:7070/api/user/{userId}/alarm"),
    SESSION("http://hrabovec.hopto.org:7070/api/analytics/session/{sessionId}"),
    USER_SESSIONS("http://hrabovec.hopto.org:7070/api/analytics/user/{userId}/sessions");

    private URI uri;
    private WebAPIRefs(String uri){
        this.uri = new URI(uri);
    }

    public URI getURI () {
        return uri;
    }

}
