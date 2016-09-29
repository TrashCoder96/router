package com.router;

import com.router.data.DeviceRepository;
import com.router.data.SystemRepository;
import com.router.data.vo.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;

@SpringBootApplication
public class AutoRouterApplication {

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private SystemRepository systemRepository;

	@PostConstruct
	public void createBasicDevice() {
		Device device = new Device();
		device.setId("id1");
		device.setName("name1");
		if (deviceRepository.findOne("id1") == null) {
			deviceRepository.save(device);
		}
		com.router.data.vo.System system = new com.router.data.vo.System();
		system.setId("s1");
		system.setSystem_url("http://localhost:1337");
		system.setName("s1");
		if (systemRepository.findOne("s1") == null) {
			systemRepository.save(system);
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(AutoRouterApplication.class, args);
	}
}
