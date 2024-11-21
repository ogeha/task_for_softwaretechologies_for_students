package org.softwaretechnologies;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import static java.lang.Integer.MAX_VALUE;

public class Money {
    private final MoneyType type;
    private final BigDecimal amount;

    public Money(MoneyType type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
    }

    /**
     * Money равны, если одинаковый тип валют и одинаковое число денег до 4 знака после запятой.
     * Округление по правилу: если >= 5, то в большую сторону, интаче - в меньшую
     * Пример округления:
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     *
     * @param o объект для сравнения
     * @return true - равно, false - иначе
     */
    @Override
    public boolean equals(Object o) {
        // TODO: реализуйте вышеуказанную функцию
        Money money = (Money) o;
        BigDecimal thisAmount = this.getAmount().setScale(4, RoundingMode.HALF_UP);
        BigDecimal otherAmount = money.getAmount().setScale(4, RoundingMode.HALF_UP);
        if (this.type == money.type && thisAmount.intValue() == otherAmount.intValue()){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Формула:
     * (Если amount null 10000, иначе количество денег окрукленные до 4х знаков * 10000) + :
     * если USD , то 1
     * если EURO, то 2
     * если RUB, то 3
     * если KRONA, то 4
     * если null, то 5
     * Если amount округленный до 4х знаков * 10000 >= (Integer.MaxValue - 5), то хеш равен Integer.MaxValue
     * Округление по правилу: если >= 5, то в большую сторону, иначе - в меньшую
     * Пример округления:
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     *
     * @return хеш код по указанной формуле
     */
    @Override
    public int hashCode() {
        // TODO: реализуйте вышеуказанную функцию
        int hash = 0;
        if (this.amount == null){
            return hash + 10000;
        }
        if (this.amount != null){
            BigDecimal thisAmount1 = this.getAmount().setScale(4, RoundingMode.HALF_UP);
            if (this.type == MoneyType.USD) {
                hash = thisAmount1.intValue() * 10000 + 1;
            }
            if (this.type == MoneyType.EURO) {
                hash = thisAmount1.intValue() * 10000 + 2;
            }
            if (this.type == MoneyType.RUB) {
                hash = thisAmount1.intValue() * 10000 + 3;
            }
            if (this.type == MoneyType.KRONA) {
                hash = thisAmount1.intValue() * 10000 + 4;
            }
            if (this.type == null) {
                hash = thisAmount1.intValue() * 10000 + 5;
            }
            if (hash >= Integer.MAX_VALUE - 5){
                hash = Integer.MAX_VALUE;
            }
            return hash;
        }


        Random random = new Random();
        return random.nextInt();
    }

    /**
     * Верните строку в формате
     * Тип_ВАЛЮТЫ: количество.XXXX
     * Тип_валюты: USD, EURO, RUB или KRONA
     * количество.XXXX - округленный amount до 4х знаков.
     * Округление по правилу: если >= 5, то в большую сторону, интаче - в меньшую
     * BigDecimal scale = amount.setScale(4, RoundingMode.HALF_UP);
     * <p>
     * Если тип валюты null, то вернуть:
     * null: количество.XXXX
     * Если количество денег null, то вернуть:
     * Тип_ВАЛЮТЫ: null
     * Если и то и то null, то вернуть:
     * null: null
     *
     * @return приведение к строке по указанному формату.
     */
    @Override
    public String toString() {
        String str = null;
        // TODO: реализуйте вышеуказанную функцию
        if (this.amount != null && this.type != null){
            str = type.toString()+": "+amount.setScale(4, RoundingMode.HALF_UP).toString();
        }
        if (this.amount == null && this.type != null){
            str = type.toString()+": null";
        }
        if (this.amount != null && this.type == null){
            str = "null: "+amount.setScale(4, RoundingMode.HALF_UP).toString();
        }
        if (this.amount == null && this.type == null){
            str = "null: null";
        }

        return str;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public MoneyType getType() {
        return type;
    }

    public static void main(String[] args) {
        Money money = new Money(MoneyType.EURO, BigDecimal.valueOf(10.00012));
        Money money1 = new Money(MoneyType.EURO, BigDecimal.valueOf(12.40000));
        System.out.println(money1.toString());
        System.out.println(money1.hashCode());
        System.out.println(money.equals(money1));
    }
}

