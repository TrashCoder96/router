package com.router.web;

import com.router.data.DeviceRepository;
import com.router.data.vo.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by asus-pc on 29.09.2016.
 */

@RestController
public class DataController {

    @Autowired
    private DeviceRepository deviceRepository;

    public ResponseEntity sendData(@RequestParam String id, @RequestBody String str) {
        Device device = deviceRepository.findOne(id);
        if (device != null) {
            String url = device.getDevice_url();
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

    }

}
