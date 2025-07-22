package customers;

import org.springframework.stereotype.Repository;

@Repository("productDAO")
public class ProductDAO implements IProductDAO{
    private ILogger logger;
    public ProductDAO(ILogger logger) {
        this.logger = logger;
    }
    @Override
    public void save(Product product) {
         //simple sleep
    try {
        Thread.sleep(350);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    System.out.println("ProductDAO: saving product "+product.getName());
    logger.log("Product is saved in the DB: "+ product.getName() );


    }
}
