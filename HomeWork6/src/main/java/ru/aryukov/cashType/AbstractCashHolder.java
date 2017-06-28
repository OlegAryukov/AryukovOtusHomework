package ru.aryukov.cashType;

/**
 * Created by oaryukov on 27.06.2017.
 */
public abstract class AbstractCashHolder {
    CashType type;
    int count;
    int maxHolderSize = 500;

    public AbstractCashHolder(CashType type, int count) {
        this.type = type;
        this.count = count;
    }

    public int getCount(){
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public CashType getType(){
        return type;
    }

    public int getMaxHolderSize() {
        return maxHolderSize;
    }

}
