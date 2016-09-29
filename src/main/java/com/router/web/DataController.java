package com.router.web;

import com.router.data.DeviceRepository;
import com.router.data.SystemRepository;
import com.router.data.vo.Device;
import com.router.data.vo.System;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by asus-pc on 29.09.2016.
 */

@RestController
public class DataController {

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private SystemRepository systemRepository;

    @RequestMapping(value = "/send", method = { RequestMethod.POST, RequestMethod.GET })
    public ResponseEntity sendData(@RequestParam(required = true) String id, String str) {
        Device device = deviceRepository.findOne(id);
        if (device != null) {
            //рассылаем всем системам данные
            OkHttpClient client = new OkHttpClient();
            for (System s: systemRepository.findAll()) {
                RequestBody body = RequestBody.create(null, str);
                Request request = new Request.Builder()
                            .url(s.getSystem_url())
                            .post(body)
                            .build();
                try {
                    Response response = client.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}
