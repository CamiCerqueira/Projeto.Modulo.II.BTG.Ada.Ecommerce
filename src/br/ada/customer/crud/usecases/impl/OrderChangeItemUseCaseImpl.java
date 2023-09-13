package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderItem;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.model.Product;
import br.ada.customer.crud.usecases.IOrderChangeItemUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

import java.util.List;

public class OrderChangeItemUseCaseImpl implements IOrderChangeItemUseCase {
    private final OrderRepository orderRepository;

    public OrderChangeItemUseCaseImpl(
            OrderRepository orderRepository
    ) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderItem changeAmount(Order order, Product product, Integer amount) {
        if(order.getStatus() != OrderStatus.OPEN){
            throw new IllegalStateException("Pedido não pode ser alterado");
        }

        OrderItem itemToUpdate = findOrderItem(order, product.getId());

        if (itemToUpdate != null) {
            itemToUpdate.setAmount(amount);
            orderRepository.update(order);
        }else {
            throw new IllegalStateException("Produto não encontrado");
        }

        return itemToUpdate;
    }

    private OrderItem findOrderItem(Order order, Long productId) {
        List<OrderItem> listProductsOrder = order.getItems();
        OrderItem toItemChangeAmount = null;

        for (OrderItem item : listProductsOrder) {
            if (item.getProduct().getId().equals(productId)) {
                toItemChangeAmount = item;
                break;
            }
        }
        return toItemChangeAmount;
    }
}
