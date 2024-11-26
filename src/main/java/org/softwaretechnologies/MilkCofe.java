package org.softwaretechnologies;

public class MilkCofe extends Cofe {


    public MilkCofe(Cofe cofe) {
        super();
    }



    /**
     * Создайте класс MilkCofe, поддерживающий интерфейс CofeIntrface
     * к стоимости базового напитка добавьте 10.
     * к описанию добавьте " + milk"
     */
    @Override
    public int getCost() {
        return super.getCost() + 10;
    }

    @Override
    public String description() {
        return super.description() + "milk";
    }
}
