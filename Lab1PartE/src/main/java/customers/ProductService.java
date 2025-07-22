package customers;

import org.springframework.beans.factory.annotation.Autowired;

public class ProductService implements IProductService {

    @Autowired
    IProductDAO productDAO;

    @Autowired
    IEmailSender emailSender;

    @Override
    public void addProduct(String productName,String email) {

        Product product = new Product(productName);
        productDAO.save(product);
        emailSender.sendEmail(email, "The " + productName + " has been added");

    }
}
