package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Customer;
import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.ICreateOrderUseCase;
import br.ada.customer.crud.usecases.repository.CustomerRepository;
import br.ada.customer.crud.usecases.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CreateOrderUseCaseImpl implements ICreateOrderUseCase {

    private OrderRepository repository;
    private CustomerRepository customerRepository;

    public CreateOrderUseCaseImpl(OrderRepository repository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Order create(Customer customer) {
        validCustomer(customer);
        Order order = new Order();
        order.setCustomer(customer);
        order.setItems(new ArrayList<>());
        order.setStatus(OrderStatus.OPEN);
        order.setShippingAddress("Minha casa sempre");
        order.setOrderedAt(LocalDateTime.now());
        repository.save(order);
        return order;
    }

    private void validCustomer(Customer customer) {
        Customer found = customerRepository.findByDocument(customer.getDocument());
        if (found == null) {
            throw new IllegalStateException("Cliente não encontrado");
        }
    }

}
