package ru.wintermute.postal_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.wintermute.postal_service.controllers.MailController;
import ru.wintermute.postal_service.controllers.WarehouseController;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class PostalServiceApplicationTests {

	@Autowired
	private MailController mailController;
	@Autowired
	private WarehouseController warehouseController;

	@Test
	void contextLoads() {
	}
	@Test
	void mailControllerLoads() {
		assertThat(mailController).isNotNull();
	}
	@Test
	void warehouseControllerLoads() {
		assertThat(warehouseController).isNotNull();
	}

}
