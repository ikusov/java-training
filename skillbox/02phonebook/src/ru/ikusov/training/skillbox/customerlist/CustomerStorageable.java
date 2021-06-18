package ru.ikusov.training.skillbox.customerlist;

public interface CustomerStorageable {
    void addCustomer(Customer customer);
    void removeCustomer(Customer customer);
    int getCount();
}
