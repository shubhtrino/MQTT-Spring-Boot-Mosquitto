package trino.code.mqttmaven;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import trino.code.mqttmaven.service.MessagingService;

@SpringBootApplication
public class MqttMavenApplication implements CommandLineRunner{

	@Autowired
	private MessagingService messagingService;
	
	@Autowired
	private ConfigurableApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(MqttMavenApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		final String topic = "trino/topic/event";

		messagingService.subscribe(topic);

		System.out.println("Publishing message...");
		
		messagingService.publish(topic, "Hello\n This is a sample message published to topic trino/topic/event "+
		new java.util.Date(), 0, true);

		//context.close();
		
	}

}
