package com.kauahv.Mini_ECommerceAPI.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name ="products")
public class Product {

    @Id
    @GeneratedValue
    @UuidGenerator
    @EqualsAndHashCode.Include
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    @ManyToOne
    @JsonBackReference
    private Category category;
    private String imageURL;

    @OneToMany(mappedBy = "id.product")
    private Set<OrderItem> items = new HashSet<>();

}
