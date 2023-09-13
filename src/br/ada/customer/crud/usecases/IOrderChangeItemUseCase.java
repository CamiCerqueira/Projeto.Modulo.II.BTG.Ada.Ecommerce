package br.ada.customer.crud.usecases;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderItem;
import br.ada.customer.crud.model.Product;

public interface IOrderChangeItemUseCase {
    OrderItem changeAmount(Order order, Product product, Integer amount);
}
