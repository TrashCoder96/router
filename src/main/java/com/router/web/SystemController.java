package com.router.web;

import com.router.data.SystemRepository;
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
@RequestMapping(value = "/api/admin/system")
public class SystemController {

    @Autowired
    private SystemRepository systemRepository;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<com.router.data.vo.System> create(@RequestParam(required = true) String name) {
        com.router.data.vo.System system = new com.router.data.vo.System();
        system.setName(name);
        system = systemRepository.save(system);
        return new ResponseEntity<com.router.data.vo.System>(system, HttpStatus.OK);
    }

    @RequestMapping(value = "/read", method = RequestMethod.POST)
    public ResponseEntity<com.router.data.vo.System> read(@RequestParam(required = true) String id) {
        com.router.data.vo.System system = systemRepository.findOne(id);
        if (system != null)
            return new ResponseEntity<com.router.data.vo.System>(system, HttpStatus.OK);
        else
            return new ResponseEntity<com.router.data.vo.System>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<com.router.data.vo.System> update(@RequestParam(required = true) String id, @RequestParam(required = false) String name, @RequestParam(required = false) String system_url) {
        com.router.data.vo.System system = systemRepository.findOne(id);
        if (system != null) {
            if (name != null) {
                system.setName(name);
            }
            if (system_url != null) {
                system.setSystem_url(system_url);
            }
            system = systemRepository.save(system);
            return new ResponseEntity<com.router.data.vo.System>(system, HttpStatus.OK);
        }
        else
            return new ResponseEntity<com.router.data.vo.System>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseEntity<com.router.data.vo.System> delete(@RequestBody List<String> ids) {
        for (String id: ids) {
            if (systemRepository.exists(id)) {
                systemRepository.delete(id);
            }
        }
        return new ResponseEntity<com.router.data.vo.System>(HttpStatus.OK);
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<List<com.router.data.vo.System>> readList(@RequestParam(required = false)Integer offset, @RequestParam(required = false)Integer limit) {
        List<com.router.data.vo.System> systems = null;
        if (limit == null && offset == null) {
            systems = systemRepository.findAll(new PageRequest(0, 10)).getContent();
        } else if (limit != null && offset != null) {
            systems = systemRepository.findAll(new PageRequest(offset, limit)).getContent();
        } else {
            return new ResponseEntity<List<com.router.data.vo.System>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<com.router.data.vo.System>>(systems, HttpStatus.OK);
    }

}
