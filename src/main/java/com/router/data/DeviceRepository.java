package com.router.data;

import com.router.data.vo.Device;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by asus-pc on 28.09.2016.
 */
public interface DeviceRepository extends MongoRepository<Device, String> {
}
