package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.INotifierOrderUseCase;
import br.ada.customer.crud.usecases.IOrderPaymentUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

public class OrderPaymentUseCaseImpl implements IOrderPaymentUseCase {
    private OrderRepository orderRepository;
    private final INotifierOrderUseCase notifierUseCaseEmail;
    private final INotifierOrderUseCase notifierUseCaseSms;


    public OrderPaymentUseCaseImpl(
            OrderRepository repository,
            INotifierOrderUseCase notifierUseCaseEmail,
            INotifierOrderUseCase notifierUseCaseSms
    ) {
        this.orderRepository = repository;
        this.notifierUseCaseEmail = notifierUseCaseEmail;
        this.notifierUseCaseSms = notifierUseCaseSms;
    }

    @Override
    public void pay(Order order) {
        if (order.getStatus() != OrderStatus.PENDING_PAYMENT) {
            throw new IllegalStateException("O pedido não pode ser pago");
        }

        order.setStatus(OrderStatus.PAID);
        orderRepository.save(order);
        notifierUseCaseEmail.updatedPayOrder(order);
        notifierUseCaseSms.updatedPayOrder(order);
    }
}
