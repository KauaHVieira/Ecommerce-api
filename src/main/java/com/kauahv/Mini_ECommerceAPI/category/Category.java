package com.kauahv.Mini_ECommerceAPI.category;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kauahv.Mini_ECommerceAPI.product.Product;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name ="categories")
public class Category {

    @Id
    @GeneratedValue
    @UuidGenerator
    @EqualsAndHashCode.Include
    private UUID id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_parent_id")
    private Category categoryParent;

    @OneToMany(mappedBy = "category")
    @JsonManagedReference
    private List<Product> products = new ArrayList<>();

}
