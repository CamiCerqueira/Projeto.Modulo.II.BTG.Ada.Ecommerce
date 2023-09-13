package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderItem;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.INotifierOrderUseCase;
import br.ada.customer.crud.usecases.IOrderPlaceUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

import java.math.BigDecimal;

public class OrderPlaceUseCaseImpl implements IOrderPlaceUseCase {
    private OrderRepository orderRepository;
    private final INotifierOrderUseCase notifierUseCaseEmail;
    private final INotifierOrderUseCase notifierUseCaseSms;


    public OrderPlaceUseCaseImpl(
            OrderRepository orderRepository,
            INotifierOrderUseCase notifierUseCaseEmail,
            INotifierOrderUseCase notifierUseCaseSms
    ) {
        this.orderRepository = orderRepository;
        this.notifierUseCaseEmail = notifierUseCaseEmail;
        this.notifierUseCaseSms = notifierUseCaseSms;
    }

    @Override
    public void placeOrder(Order order) {
        if (order.getStatus() != OrderStatus.OPEN) {
            throw new IllegalStateException("O pedido não está aberto");
        }

        if (order.getItems() == null ||order.getItems().isEmpty()) {
            throw new IllegalStateException("Não há produtos no carrinho");
        }

        BigDecimal totalValue = calculateTotalValue(order);
        //Integer totalValueCompareTo = totalValue.compareTo(BigDecimal.ZERO);
        if (totalValue.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("Adicione produtos ao carrinho antes de fechar o pedido");
        }

        order.setStatus(OrderStatus.PENDING_PAYMENT);
        notifierUseCaseEmail.pendingPayment(order);
        notifierUseCaseSms.pendingPayment(order);
    }

    private BigDecimal calculateTotalValue(Order order) {
        BigDecimal totalValue = BigDecimal.ZERO;

        for (OrderItem item : order.getItems()) {
            totalValue = totalValue.add(item.getSaleValue());
        }

        return totalValue;
    }
}
