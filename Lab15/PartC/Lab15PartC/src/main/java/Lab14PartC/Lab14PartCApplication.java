package Lab14PartC;

import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab14PartCApplication implements CommandLineRunner {

	@Autowired
	private ProfitRepository profitRepository;

	public static void main(String[] args) {
		SpringApplication.run(Lab14PartCApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Profit profitEntity = new Profit("January", 500.00);
		profitRepository.save(profitEntity);
		Profit profitEntity2 = new Profit("February", 1423.99);
		profitRepository.save(profitEntity2);
		Profit profitEntity3 = new Profit("March", 150.00);
		profitRepository.save(profitEntity3);
		Profit profitEntity4 = new Profit("April", 200.58);
		profitRepository.save(profitEntity4);
	}

}
