package com.github.jamal.domain;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

@Table
public class Customer {

  @PrimaryKey
  private UUID id;

  private String firstName;
  private String lastName;

  public Customer() {
  }

  public Customer(String firstName, String lastName) {
    this.id = UUID.randomUUID();
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public UUID getId() {
    return id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Override
  public String toString() {
    return "Customer{" +
        "id='" + id + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        '}';
  }

}
