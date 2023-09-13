package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderItem;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.model.Product;
import br.ada.customer.crud.usecases.IOrderAddItemUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

import java.math.BigDecimal;

public class OrderAddItemUseCaseImpl implements IOrderAddItemUseCase {
    private final OrderRepository orderRepository;

    public OrderAddItemUseCaseImpl(
            OrderRepository repository
    ) {
        this.orderRepository = repository;
    }

    @Override
    public OrderItem addItem(Order order, Product product, BigDecimal price, Integer amount) {
        if (order.getStatus() != OrderStatus.OPEN) {
            throw new IllegalStateException("Não pode adicionar item no pedido");
        }

        OrderItem newItem = createOrderItem(product, price, amount);
        order.getItems().add(newItem);

        orderRepository.update(order);
        return newItem;
    }

    private OrderItem createOrderItem(Product product, BigDecimal price, Integer amount) {
        OrderItem item = new OrderItem();

        item.setProduct(product);
        item.setSaleValue(price);
        item.setAmount(amount);
        return item;
    }

}
