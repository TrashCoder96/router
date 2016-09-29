package com.router.web;

import com.router.data.DeviceRepository;
import com.router.data.vo.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by asus-pc on 29.09.2016.
 */

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private DeviceRepository deviceRepository;

    @RequestMapping(value = "/read", method = RequestMethod.POST)
    public ResponseEntity<Device> read(@RequestParam(required = true) String id) {
        Device device = deviceRepository.findOne(id);
        if (device != null)
            return new ResponseEntity<Device>(device, HttpStatus.OK);
        else
            return new ResponseEntity<Device>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Device> update(@RequestParam(required = true) String id, @RequestParam(required = true) String name) {
        Device device = deviceRepository.findOne(id);
        if (device != null) {
            device.setName(name);
            device = deviceRepository.save(device);
            return new ResponseEntity<Device>(device, HttpStatus.OK);
        }
        else
            return new ResponseEntity<Device>(HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<Device> delete(String id) {
        Device device = deviceRepository.findOne(id);
        if (device != null) {
            deviceRepository.delete(device);
            return new ResponseEntity<Device>(device, HttpStatus.OK);
        } else {
            return new ResponseEntity<Device>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/readList", method = RequestMethod.POST)
    public ResponseEntity<List<Device>> readList(@RequestParam(required = false)Integer offset, @RequestParam(required = false)Integer limit) {
        List<Device> devices = null;
        if (limit == null && offset == null) {
            devices = deviceRepository.findAll(new PageRequest(0, 19, Sort.Direction.ASC)).getContent();
        } else if (limit != null && offset != null) {
            devices = deviceRepository.findAll(new PageRequest(offset, limit, Sort.Direction.ASC)).getContent();
        } else {
            return new ResponseEntity<List<Device>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Device>>(devices, HttpStatus.OK);
    }
}
