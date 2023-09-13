package br.ada.customer.crud.usecases;

import br.ada.customer.crud.model.Order;

public interface INotifierOrderUseCase {
    void shipping(Order order);
    void updatedPayOrder(Order order);
    void pendingPayment(Order order);
}
