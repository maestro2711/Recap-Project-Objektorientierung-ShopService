import java.lang.reflect.Array;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class Main {
    public static void main(String[] args){
        OrderListRepo orderListRepo = new OrderListRepo(new ArrayList<>());
        ProductRepo productRepo = new ProductRepo();

        ShopService shopService = new ShopService(productRepo, orderListRepo);

        Product product1 = new Product(UUID.randomUUID().toString(),"Lamp");
        Product product2 = new Product(UUID.randomUUID().toString(),"Light");

         Order order1= new Order(UUID.randomUUID().toString(),new ArrayList<>(Arrays.asList(product1,product2)),OrderStatus.PROCESSING, ZonedDateTime.now());
         orderListRepo.addOrder(order1);

        System.out.println(order1);


    }


}
