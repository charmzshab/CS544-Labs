package customers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public CustomerService customerService() {
        return new CustomerService();
    }
    @Bean
    public ProductService productService() {
        return new ProductService();
    }
    @Bean
    public CustomerDAO customerDAO() {
        return new CustomerDAO(logger());
    }

    @Bean
    public EmailSender emailSender() {
        return new EmailSender(logger());
    }

    @Bean
    public Logger logger() {
        return new Logger();
    }

    @Bean
    public ProductDAO productDAO() {
        return new ProductDAO(logger());
    }

}
