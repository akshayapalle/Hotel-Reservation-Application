package service;

/**
 * @author 20B01
 *
 */
import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {

    private static final CustomerService instance = new CustomerService();
    private final Map<String, Customer> customerMap = new HashMap<>();

    private CustomerService() {}

    public static CustomerService getInstance() {
        return instance;
    }

    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        customerMap.put(email, customer);
    }

    public Customer getCustomer(String customerEmail) {
        return customerMap.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers() {
        return customerMap.values();
    }
}
