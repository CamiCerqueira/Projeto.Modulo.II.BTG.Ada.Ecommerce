package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderItem;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.model.Product;
import br.ada.customer.crud.usecases.IOrderPlaceUseCase;
import br.ada.customer.crud.usecases.IOrderRemoveItemUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;

public class OrderRemoveItemUseCaseImpl implements IOrderRemoveItemUseCase {
    private OrderRepository orderRepository;


    public OrderRemoveItemUseCaseImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;

    }

    private Product getProductFromOrder(Product product, List<OrderItem> listItems) {
        Product itemDelete = null;

        for (OrderItem item : listItems) {
            if (item.getProduct().equals(product)) {
                itemDelete =  item.getProduct();
                break;
            }
        }
        return itemDelete;
    }

    private Order deleteProductFromOrder(Product productDelete, List<OrderItem> listItems, Order order) {
        for (OrderItem item : listItems) {
            if (item.getProduct().equals(productDelete)) {
                listItems.remove(item);
                break;
            }
        }
        order.setItems(listItems);
        return order;
    }

    @Override
    public void removeItem(Order order, Product product) {
        if (order.getStatus() != OrderStatus.OPEN) {
            throw new IllegalStateException("Não pode remover item no pedido");
        }

        List<OrderItem> listItems = order.getItems();

        Product productDelete = getProductFromOrder(product, listItems);
        if (productDelete == null) {
            throw new IllegalStateException("Produto não encontrado");
        }

        Order orderUpdate = deleteProductFromOrder(productDelete, listItems, order);
        orderRepository.update(orderUpdate);
    }
}
