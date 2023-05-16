package lt.viko.eif.vskuder.gameStore3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * Main class.
 */
@EnableSwagger2
@SpringBootApplication
public class GameStore3Application {

	public static void main(String[] args) {
		SpringApplication.run(GameStore3Application.class, args);
	}

}
