package br.ada.customer.crud.integration.sms;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.INotifierOrderUseCase;

public class OrderSmsNotifierImpl implements INotifierOrderUseCase {
    private final SendSms sendSms;

    public OrderSmsNotifierImpl(SendSms sendSms) {
        this.sendSms = sendSms;
    }

    @Override
    public void shipping(Order order) {
        sendSms.send("1111111111111", order.getCustomer().getTelephone(), "Olá, Seu pedido foi enviado");
    }

    @Override
    public void updatedPayOrder(Order order) {
        sendSms.send("1111111111111", order.getCustomer().getTelephone(), "Olá, O pagamento do seu pedido " + order.getId() +
                " mudou o status para " + order.getStatus());
    }

    @Override
    public void pendingPayment(Order order) {
        sendSms.send("1111111111111", order.getCustomer().getTelephone(), "Olá, Seu pedido " + order.getId() +
                " está aguardado pagamento");
    }
}
