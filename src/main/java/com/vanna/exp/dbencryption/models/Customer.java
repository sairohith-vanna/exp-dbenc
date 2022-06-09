package com.vanna.exp.dbencryption.models;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.vanna.exp.dbencryption.extensions.AttributesEncryptionConverter;

import lombok.Data;

@Data
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(
        name = "id"
    )
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(
        name = "first_name",
        length = 128,
        nullable = false
    )
    @Convert(
        converter = AttributesEncryptionConverter.class
    )
    private String firstName;

    @Column(
        name = "last_name",
        length = 128,
        nullable = false
    )
    @Convert(
        converter = AttributesEncryptionConverter.class
    )
    private String lastName;

    @Column(
        name = "age",
        nullable = false
    )
    private int age;    

    @Column(
        name = "date_of_birth",
        nullable = false
    )
    private LocalDate dateOfBirth;
    
}
