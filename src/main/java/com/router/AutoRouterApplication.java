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
		Device newdevice = new Device();
		newdevice.setId("id1");
		newdevice.setName("name1");
		if (deviceRepository.findOne("id1") == null) {
			deviceRepository.save(newdevice);
		}

		//для проивольно контекстной грамматики, может быть построен детерминированный автомат со стековой памятью, принимающий язык, порождаемый этой грамматикой
		com.router.data.vo.System newsystem = new com.router.data.vo.System();
		newsystem.setId("s1");
		newsystem.setSystem_url("http://localhost:1337");
		newsystem.setName("s1");
		if (systemRepository.findOne("s1") == null) {
			systemRepository.save(newsystem);
		}

		//рекурсивный метод нисходящего разбора

	}

	public static void main(String[] args) {
		SpringApplication.run(AutoRouterApplication.class, args);
	}
}
