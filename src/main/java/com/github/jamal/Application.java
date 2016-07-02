package com.github.jamal;

import com.github.jamal.domain.Customer;
import com.github.jamal.domain.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.domain.*;

@SpringBootApplication
@EnableCassandraRepositories
public class Application implements CommandLineRunner {

  @Configuration
  public static class CassandraConfig extends AbstractCassandraConfiguration {

    @Override
    protected String getKeyspaceName() {
      return "slicetest";
    }

  }

  @Autowired
  private CustomerRepository repository;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    repository.deleteAll();

    repository.save(new Customer("Alice", "Smith"));
    repository.save(new Customer("Bob", "Smith"));
    repository.save(new Customer("Bob", "Doe"));
    repository.save(new Customer("Bob", "Charles"));

    System.out.println("\nFind First 20");

    Continuable continuable = new ContinuationRequest(20);
    Continuation<Customer> page = repository.findCustomers(continuable);
    System.out.println("Next continuation: " + page.getContinuationValue());
    for (Customer customer : page.getContent()) {
      System.out.println(customer);
    }

    System.out.println("\nFind First 2");

    continuable = new ContinuationRequest(2);
    page = repository.findCustomers(continuable);
    System.out.println("Next continuation: " + page.getContinuationValue());
    for (Customer customer : page.getContent()) {
      System.out.println(customer);
    }

    System.out.println("\nFind Next 2");

    continuable = new ContinuationRequest(page.getContinuationValue(), 2);
    page = repository.findCustomers(continuable);
    System.out.println("Next continuation: " + page.getContinuationValue());
    for (Customer customer : page.getContent()) {
      System.out.println(customer);
    }
  }

}
