package com.msagres.inventoryapp.category;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.*;
import java.util.UUID;

//same name as the table
@Entity
public class Category {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "pg-uuid"
    )
    @GenericGenerator(
            name = "pg-uuid",
            strategy = "uuid2",
            parameters = @org.hibernate.annotations.Parameter(
                    name = "uuid_gen_strategy_class",
                    value = "com.msagres.inventory.PostgreSQLUUIDGenerationStrategy"
            )
    )
    private UUID id;

    @Column(length = 45, nullable = false, unique = true)
    private String name;

    public Category() {
    }

    public Category(UUID id) {
        this.id = id;
    }

    public Category(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
