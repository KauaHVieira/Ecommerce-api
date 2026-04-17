package com.kauahv.Mini_ECommerceAPI.domain;

import com.kauahv.Mini_ECommerceAPI.domain.pk.OrderItemPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "order_items")
public class OrderItem {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private OrderItemPK id = new OrderItemPK();
    private BigDecimal price;
    private Integer quantity;

    public Order getOrder(){ return id.getOrder(); }

    public void setOrder(Order order){ id.setOrder(order); }

    public Product getProduct(){ return id.getProduct(); }

    public void setProduct(Product product){ id.setProduct(product); }

    public BigDecimal getSubTotal(){
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
