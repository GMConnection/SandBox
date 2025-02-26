package gm.sandbox.websandboxserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "gm.sandbox.websandboxserver")
public class WebSandboxServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebSandboxServerApplication.class, args);
	}

}
