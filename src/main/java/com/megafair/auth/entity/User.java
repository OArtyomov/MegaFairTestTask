package com.megafair.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "userSequence")
    @SequenceGenerator(name = "userSequence", sequenceName = "userSeq",
            allocationSize = 1, initialValue = 10)
    @Column(name = "id")
    Long id;

    @Column(name = "identifier")
    String identifier;

    @Column(name = "signature")
    String signature;

    @Column(name = "platform_id")
    Long platformId;

}
