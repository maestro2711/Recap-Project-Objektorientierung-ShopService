import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderListRepoTest {

    @Test
    void getOrders() {
        //GIVEN
        OrderListRepo repo = new OrderListRepo();
        ZonedDateTime timestamp=ZonedDateTime.now();

        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product),OrderStatus.COMPLETED, timestamp);
        repo.addOrder(newOrder);

        //WHEN
        List<Order> actual = repo.getOrders();

        //THEN
        List<Order> expected = new ArrayList<>();
        Product product1 = new Product("1", "Apfel");
        expected.add(new Order("1", List.of(product1),OrderStatus.COMPLETED,timestamp));

        assertEquals(actual, expected);
    }

    @Test
    void getOrderById() {
        //GIVEN
        OrderListRepo repo = new OrderListRepo();

        ZonedDateTime timestamp=ZonedDateTime.now();

        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product),OrderStatus.IN_DELIVERY,timestamp);
        repo.addOrder(newOrder);

        //WHEN
        Order actual = repo.getOrderById("1");

        //THEN
        Product product1 = new Product("1", "Apfel");
        Order expected = new Order("1", List.of(product1),OrderStatus.IN_DELIVERY,timestamp);

        assertEquals(actual, expected);
    }

    @Test
    void addOrder() {
        //GIVEN
        OrderListRepo repo = new OrderListRepo();
        ZonedDateTime timestamp=ZonedDateTime.now();
        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product),OrderStatus.PROCESSING,timestamp);

        //WHEN
        Order actual = repo.addOrder(newOrder);

        //THEN
        Product product1 = new Product("1", "Apfel");
        Order expected = new Order("1", List.of(product1),OrderStatus.PROCESSING,timestamp);
        assertEquals(actual, expected);
        assertEquals(repo.getOrderById("1"), expected);
    }

    @Test
    void removeOrder() {
        //GIVEN
        OrderListRepo repo = new OrderListRepo();

        //WHEN
        repo.removeOrder("1");

        //THEN
        assertNull(repo.getOrderById("1"));
    }
}
