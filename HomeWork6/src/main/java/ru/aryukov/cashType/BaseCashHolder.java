package ru.aryukov.cashType;

/**
 * Created by oaryukov on 28.06.2017.
 */
public class BaseCashHolder extends AbstractCashHolder{

    public BaseCashHolder(CashType type, int count) {
        super(type, count);
    }

    public int getCashHolderSum(){
        return type.getNominal() * count;
    }

    public void getCash(int count){
        if(count < this.count){
            this.count -= count;
        }
    }

    public void putCash(int count){
        if ((this.count + count) < maxHolderSize){
            this.count += count;
        } else {
            System.out.println("Ячейка для банкнот номиналом " + type.getDetail() + "не может больше принимать купюры");
        }
    }

}
