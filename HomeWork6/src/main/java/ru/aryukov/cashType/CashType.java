package ru.aryukov.cashType;

/**
 * Created by oaryukov on 27.06.2017.
 */
public enum CashType {
    FIVE_THOUSAND(5000){
        public String getDetail(){
            return "Пять тысяч";
        }
    },
    ONE_THOUSAND(1000){
        public String getDetail(){
            return "Одна тысяча";
        }
    },
    FIVE_HUNDRED(500){
        public String getDetail(){
            return "Пять сотен";
        }
    },
    ONE_HUNDRED(100){
        public String getDetail(){
            return "Одна сотня";
        }
    },
    FIFTY(50){
        public String getDetail(){
            return "Пятьдесят рублей";
        }
    };

    private int nominal;

    private CashType(int nominal){
        this.nominal = nominal;
    }

    public abstract String getDetail();

    public int getNominal(){
        return this.nominal;
    }
}
