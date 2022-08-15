package com.example.coffeerest.Entity;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="name") private String name;
    @Column(name="price") private Float price;
    @Column(name="image") private String image;
    @Column(name="size") private Float size;
    @Column(name="sugar") private Float sugar;
    @Column(name="description") private String description;
    @Column(name="color") private String color;
    @Column(name="boughtItemsCount") private Integer boughtItemsCount;
    @Column(name="category") private String category;

    public Product(String n, float p, String path, float s1, float s2, String desc, String c, int count, String cat) {
        name = n;
        price = p;
        image = path;
        size = s1;
        sugar = s2;
        description = desc;
        color = c;
        boughtItemsCount = count;
        category = cat;
    }
}


