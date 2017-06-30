package ru.aryukov.cashType;

/**
 * Created by oaryukov on 27.06.2017.
 */
public enum CashType {
    FIVE_THOUSAND(5000, 0){
        public String getDetail(){
            return "Пять тысяч";
        }
    },
    ONE_THOUSAND(1000, 1){
        public String getDetail(){
            return "Одна тысяча";
        }
    },
    FIVE_HUNDRED(500, 2){
        public String getDetail(){
            return "Пять сотен";
        }
    },
    ONE_HUNDRED(100, 3){
        public String getDetail(){
            return "Одна сотня";
        }
    },
    FIFTY(50, 4){
        public String getDetail(){
            return "Пятьдесят рублей";
        }
    };

    private int nominal;
    private int level;

    private CashType(int nominal, int level){
        this.nominal = nominal;
        this.level = level;
    }

    public abstract String getDetail();

    public int getNominal(){
        return this.nominal;
    }

    public int getLevel() {
        return level;
    }
}
