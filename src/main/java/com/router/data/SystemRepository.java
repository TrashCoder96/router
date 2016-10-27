package com.router.data;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by asus-pc on 29.09.2016.
 */
public interface SystemRepository extends MongoRepository<com.router.data.vo.System, String> {

    com.router.data.vo.System findByName(String name);

}
