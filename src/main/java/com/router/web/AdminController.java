package com.router.web;

import com.router.data.DeviceRepository;
import com.router.data.vo.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by asus-pc on 29.09.2016.
 */

@RestController
@RequestMapping(value = "/api/admin/device")
public class AdminController {

    @Autowired
    private DeviceRepository deviceRepository;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Device> create(@RequestParam(required = true) String name) {
        Device device = new Device();
        device.setName(name);
        device = deviceRepository.save(device);
        return new ResponseEntity<Device>(device, HttpStatus.OK);
    }

    @RequestMapping(value = "/read", method = RequestMethod.POST)
    public ResponseEntity<Device> read(@RequestParam(required = true) String id) {
        Device device = deviceRepository.findOne(id);
        if (device != null)
            return new ResponseEntity<Device>(device, HttpStatus.OK);
        else
            return new ResponseEntity<Device>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Device> update(@RequestParam(required = true) String id, @RequestParam(required = false) String name, @RequestParam(required = false) String device_url) {
        Device device = deviceRepository.findOne(id);
        if (device != null) {
            if (name != null) {
                device.setName(name);
            }
            if (device_url != null) {
                device.setDevice_url(device_url);
            }
            device = deviceRepository.save(device);
            return new ResponseEntity<Device>(device, HttpStatus.OK);
        }
        else
            return new ResponseEntity<Device>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<Device> delete(@RequestBody List<String> ids) {
        for (String id: ids) {
            if (deviceRepository.exists(id)) {
                deviceRepository.delete(id);
            }
        }
        return new ResponseEntity<Device>(HttpStatus.OK);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<List<Device>> readList(@RequestParam(required = false)Integer offset, @RequestParam(required = false)Integer limit) {
        List<Device> devices = null;
        if (limit == null && offset == null) {
            devices = deviceRepository.findAll(new PageRequest(0, 10)).getContent();
        } else if (limit != null && offset != null) {
            devices = deviceRepository.findAll(new PageRequest(offset, limit)).getContent();
        } else {
            return new ResponseEntity<List<Device>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Device>>(devices, HttpStatus.OK);
    }
}
