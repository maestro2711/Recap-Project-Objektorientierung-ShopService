import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();


    // add  an updateOrder method in the ShopService that updates the Order based on an Id.

    public Order updateOrder(String id ,OrderStatus newStatus){
        Order newOrder = orderRepo.getOrderById(id);
        orderRepo.getOrderById(id).withStatus(newStatus);// Use the @with generated method to update status
        orderRepo.addOrder(newOrder);
        return newOrder;

    }

    public Order addOrder(List<String> productIds)  throws Exception{
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product>productToOrder = productRepo.getProductById(productId);
            if (!productToOrder.isPresent()) {
                throw new NotFoundOrderException("Product with id" +productId+ "is not found");
                //System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
                //return null;
            }
            //products.add(productToOrder);
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products,OrderStatus.PROCESSING, ZonedDateTime.now(ZoneOffset.UTC));

        return orderRepo.addOrder(newOrder);
    }
    // getAllOrderByStatus using stream
    public List<Order> getAllOrderByStatus(OrderStatus orderStatus){
        return orderRepo.getOrders().stream() // stream all orders
                .filter(order -> order.status()==orderStatus)  // filter by status
                .collect(Collectors.toList());  // Collect the Result into a list


    }


}
