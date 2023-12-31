package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.INotifierOrderUseCase;
import br.ada.customer.crud.usecases.IOrderShippingUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

public class OrderShippingUseCaseImpl implements IOrderShippingUseCase {

    private final OrderRepository orderRepository;
    private final INotifierOrderUseCase notifierUseCaseEmail;
    private final INotifierOrderUseCase notifierUseCaseSms;


    public OrderShippingUseCaseImpl(
            OrderRepository orderRepository,
            INotifierOrderUseCase notifierUseCaseEmail,
            INotifierOrderUseCase notifierUseCaseSms
    ) {
        this.orderRepository = orderRepository;
        this.notifierUseCaseEmail = notifierUseCaseEmail;
        this.notifierUseCaseSms = notifierUseCaseSms;
    }

    @Override
    public void shipping(Order order) {
        if (order.getStatus() != OrderStatus.PAID) {
            throw new IllegalStateException("O pedido não pode ser pago");
        }

        order.setStatus(OrderStatus.FINISH);
        orderRepository.save(order);
        notifierUseCaseEmail.shipping(order);
        notifierUseCaseSms.shipping(order);
    }

}