package ru.ikusov.training.skillbox.customerlist;

import java.util.HashMap;
import java.util.List;

public class CustomerStorage {
    private static final
    String NAME_REGEX = "[А-яA-z\\sЁё]+",
            EMAIL_REGEX = ".+@.+\\..+",
            PHONE_REGEX = "\\d+";

    private HashMap<String, Customer> storage;

    private CustomerStorage() {
        storage = new HashMap<>();
    }

    public static CustomerStorage getInstance() {
        return new CustomerStorage();
    }

    public void addCustomer(String customerTokens) {

        if (customerTokens == null) {
            throw new NullPointerException("Empty name!");
        }

        String[] token = customerTokens.split(" ");
        int size = token.length;

        if (size < 4 ) {
            throw new IllegalArgumentException("Not enough parameters!");
        }

        String name = token[0] + " " + token[1];
        if (!name.matches(NAME_REGEX)) {
            throw new IllegalArgumentException("Incorrect name format!");
        }

        String email = token[3];
        if (!email.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("Incorrect email format!");
        }

        String phone = token[2];
        if (!phone.matches(PHONE_REGEX)) {
            throw new IllegalArgumentException("Incorrect phone format!");
        }

        storage.put(name, new Customer(name, phone, email));
    }

    public void removeCustomer(String name) {
        if (name == null || name.equals("")) {
            throw new NullPointerException("Empty name!");
        }

        if (!name.matches(NAME_REGEX)) {
            throw new IllegalArgumentException("Incorrect name format!");
        }

        if (storage.remove(name) == null) {
            throw new IllegalArgumentException("No such name in the customer storage!");
        }
    }

    public List<Customer> customerList() {
        return storage.values().stream().toList();
    }

    public int getCount() {
        return storage.size();
    }
}
