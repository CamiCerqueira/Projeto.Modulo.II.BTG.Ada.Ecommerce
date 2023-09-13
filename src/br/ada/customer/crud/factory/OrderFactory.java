package br.ada.customer.crud.factory;

import br.ada.customer.crud.integration.database.MemoryDatabase;
import br.ada.customer.crud.integration.email.OrderEmailNotifierImpl;
import br.ada.customer.crud.integration.email.SendEmail;
import br.ada.customer.crud.integration.memoryrepository.OrderEntityMerge;
import br.ada.customer.crud.integration.memoryrepository.OrderMemoryRepositoryImpl;
import br.ada.customer.crud.integration.sms.OrderSmsNotifierImpl;
import br.ada.customer.crud.integration.sms.SendSms;
import br.ada.customer.crud.usecases.*;
import br.ada.customer.crud.usecases.impl.*;
import br.ada.customer.crud.usecases.repository.OrderRepository;

public class  OrderFactory {
    public static ICreateOrderUseCase createUseCase() {
        return new CreateOrderUseCaseImpl(
                createRepository(),
                CustomerFactory.createRepository()
        );
    }

    public static IOrderAddItemUseCase orderAddItemUseCase() {
        return new OrderAddItemUseCaseImpl(
                createRepository()
        );
    }

    public static IOrderChangeItemUseCase orderChangeItemUseCase() {
        return  new OrderChangeItemUseCaseImpl(
                createRepository()
        );
    }

    public static IOrderRemoveItemUseCase orderRemoveItemUseCase() {
        return new OrderRemoveItemUseCaseImpl(
                createRepository()
        );
    }

    public static IOrderPlaceUseCase placeOrderUseCase() {
        return new OrderPlaceUseCaseImpl(
                createRepository(),
                OrderFactory.createNotifierOrderSendEmail(),
                OrderFactory.createNotifierOrderSendSms()
        );
    }

    public static IOrderPaymentUseCase payOrderUseCase() {
        return  new OrderPaymentUseCaseImpl(
                createRepository(),
                OrderFactory.createNotifierOrderSendEmail(),
                OrderFactory.createNotifierOrderSendSms()
        );
    }

    public static IOrderShippingUseCase shippingUseCase() {
        return  new OrderShippingUseCaseImpl(
                createRepository(),
                OrderFactory.createNotifierOrderSendEmail(),
                OrderFactory.createNotifierOrderSendSms()
        );
    }

    public static OrderRepository createRepository() {
        return new OrderMemoryRepositoryImpl(
                MemoryDatabase.getInstance(),
                new OrderEntityMerge(MemoryDatabase.getInstance())
        );
    }

    public static INotifierOrderUseCase createNotifierOrderSendEmail() {
        return new OrderEmailNotifierImpl(new SendEmail());
    }

    public static INotifierOrderUseCase createNotifierOrderSendSms(){
        return new OrderSmsNotifierImpl(new SendSms());
    }
}