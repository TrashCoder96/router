package com.router.data.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by asus-pc on 29.09.2016.
 */

@Document
public class System {

    @Id
    private String id;

    private String name;

    private String system_url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSystem_url() {
        return system_url;
    }

    public void setSystem_url(String system_url) {
        this.system_url = system_url;
    }
}
