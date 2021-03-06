package com.router.data.vo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by asus-pc on 28.09.2016.
 */
@Document
public class Device {

    @Id
    private String id;

    private String name;

    private String device_url;

    public String getDevice_url() {
        return device_url;
    }

    public void setDevice_url(String device_url) {
        this.device_url = device_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
