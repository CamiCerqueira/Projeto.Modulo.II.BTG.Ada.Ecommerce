package br.ada.customer.crud.view.order;

import br.ada.customer.crud.usecases.ICreateOrderUseCase;
import br.ada.customer.crud.view.component.menu.AbstractMenuOptionView;
import br.ada.customer.crud.view.component.menu.MenuOptionView;

public class PayOrderMenuView extends AbstractMenuOptionView implements MenuOptionView {

    public static final String MENU_CODE = "4";
    public static final Integer MENU_ORDER = 4;
    private ICreateOrderUseCase useCase;


    public PayOrderMenuView() {
        super(MENU_CODE, MENU_ORDER);
    }


    @Override
    public void render() {
        System.out.println(MENU_CODE + " - Pagar um pedido");
    }

    @Override
    public void selected() {
        System.out.println("Entrou em pagar um pedido");
    }
}
