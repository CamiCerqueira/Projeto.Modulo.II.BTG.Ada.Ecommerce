package br.ada.customer.crud;

import br.ada.customer.crud.factory.CustomerFactory;
import br.ada.customer.crud.factory.OrderFactory;
import br.ada.customer.crud.factory.ProductFactory;
import br.ada.customer.crud.model.Customer;
import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.Product;
import br.ada.customer.crud.usecases.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class ExampleOrderMain {

    public static void main(String[] args) {
        IProductUseCase productUseCase = ProductFactory.createUseCase();
        ICustomerUseCase customerUseCase = CustomerFactory.createUseCase();
        ICreateOrderUseCase orderUseCase = OrderFactory.createUseCase();
        IOrderRemoveItemUseCase orderRemoveItemUseCase = OrderFactory.orderRemoveItemUseCase();
        IOrderChangeItemUseCase orderChangeItemUseCase = OrderFactory.orderChangeItemUseCase();
        IOrderPlaceUseCase orderPlaceUseCase = OrderFactory.placeOrderUseCase();
        IOrderPaymentUseCase orderPayUseCase = OrderFactory.payOrderUseCase();
        IOrderShippingUseCase orderShipping = OrderFactory.shippingUseCase();
        IOrderAddItemUseCase orderItemUseCase = OrderFactory.orderAddItemUseCase();

        Customer customer = new Customer();
        customer.setName("Camila");
        customer.setEmail(Collections.singletonList("camilaroberia@gmail.com"));
        customer.setTelephone(Collections.singletonList("11991115535"));
        customer.setBirthDate(LocalDate.of(1990, 1, 26));
        customerUseCase.create(customer);

        Product productOne = new Product();
        productOne.setDescription("Unicórnio de Pelúcia");
        productUseCase.create(productOne);

        Product productTwo = new Product();
        productTwo.setDescription("Blocos de Madeira");
        productUseCase.create(productTwo);

        Product productThree = new Product();
        productThree.setDescription("Livro Interativo");
        productUseCase.create(productThree);

        Order order = orderUseCase.create(customer);
        orderItemUseCase.addItem(order, productOne, BigDecimal.TEN, 1);
        orderItemUseCase.addItem(order, productTwo, BigDecimal.TEN, 2);
        orderChangeItemUseCase.changeAmount(order, productTwo, 5);
        orderRemoveItemUseCase.removeItem(order, productOne);
        orderPlaceUseCase.placeOrder(order);
        orderPayUseCase.pay(order);
        orderShipping.shipping(order);
    }

}
