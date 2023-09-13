package br.ada.customer.crud.usecases;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.Product;

public interface IOrderRemoveItemUseCase {
    void removeItem(Order order, Product product);
}
