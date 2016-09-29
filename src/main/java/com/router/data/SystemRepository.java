package com.router.data;

import com.router.data.vo.Device;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by asus-pc on 29.09.2016.
 */
public interface SystemRepository extends MongoRepository<com.router.data.vo.System, String> {
}
