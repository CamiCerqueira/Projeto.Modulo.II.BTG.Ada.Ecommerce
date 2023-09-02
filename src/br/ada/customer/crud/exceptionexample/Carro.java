package br.ada.customer.crud.exceptionexample;

public class Carro {

    private boolean bateria = true;
    private boolean freio = true;

    public Carro(){}
    public Carro(boolean bateria){
        this.bateria = bateria;
    }

    // Exception
    public void ligar() throws SemBateriaException {
        if (!bateria) {
            throw new SemBateriaException();
        }
        // injetar combustivel
        //  - Falta de combustivel
        //      - Tanque reserva

        // faísca da vela
        // - Velha queimada
        // motor ligado
        System.out.println("Carro ligado");
    }

    public void frear() {
        //injetar oleo para pressão
        //realizar frenagem
        if (!freio) {
            throw new SemFreioException();
        }
        System.out.println("Reduzindo velocidade");
    }

    public void recebeNovaBateria() throws BateriaIncorretaException{
        this.bateria = true;
        throw new BateriaIncorretaException();
    }

}
