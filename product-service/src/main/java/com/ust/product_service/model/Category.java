package com.ust.product_service.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "categories")
public class Category {

    @Id
    private String id;
    @Field(name = "category_name")
    private String name;
    private String description;

    private List<String> productIds = new ArrayList<>();

}