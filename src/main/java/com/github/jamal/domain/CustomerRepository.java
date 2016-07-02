package com.github.jamal.domain;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.domain.Continuable;
import org.springframework.data.domain.Continuation;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {

  @Query("SELECT * FROM customer")
  Continuation<Customer> findCustomers(Continuable page);

}
