import lombok.With;

import java.time.ZonedDateTime;
import java.util.List;

public record Order(
        String id,
        List<Product> products,

        @With
        OrderStatus status,
        ZonedDateTime orderTime
) {

        public Order(String id, List<Product> products,
                     OrderStatus status, ZonedDateTime orderTime) {
                this.id = id;
                this.products = products;
                this.status = status;
                this.orderTime = orderTime;
        }
}
