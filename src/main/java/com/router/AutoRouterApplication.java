package com.router;

import com.router.data.DeviceRepository;
import com.router.data.vo.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AutoRouterApplication {

	@Autowired
	private DeviceRepository deviceRepository;

	@PostConstruct
	public void createBasicDevice() {
		Device device = new Device();
		device.setId("id1");
		device.setName("name1");
		if (deviceRepository.findOne("id1") == null) {
			deviceRepository.save(device);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(AutoRouterApplication.class, args);
	}
}
