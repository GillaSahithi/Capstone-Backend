package com.example.vendorservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Events")
public class Event {

    @Id
    private String id;
    private String name;
    private String vendorId;
    private LocalDateTime eventDate;
    private String description;
    private String location;

}

