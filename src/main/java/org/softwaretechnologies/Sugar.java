package org.softwaretechnologies;

public class Sugar extends MilkCofe {
    public Sugar(Cofe cofe) {
        super(cofe);
    }

    /**
     * Создайте класс MilkCofe, поддерживающий интерфейс CofeIntrface
     * к стоимости базового напитка добавьте 20.
     * к описанию добавьте " + sugar"
     */
    @Override
    public int getCost() {
        return super.getCost() + 20;
    }

    @Override
    public String description() {
        return super.description() + "sugar";
    }


}
