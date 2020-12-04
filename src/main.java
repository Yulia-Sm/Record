public class main {
    public static void main(String[] args) {
        System.out.println("Кофе-машина");

        int moneyAmount = 12;
        System.out.println("Вы внесли: " + moneyAmount + " рублей");

        int cappucinoPrice = 150;
        int espressoPrice = 80;
        int waterPrice = 20;
        int milkPrice = 40;

        boolean canBuySomething = false;

        if(moneyAmount >= cappucinoPrice) {
            System.out.println("Вы можете купить капучино");
            canBuySomething = true;
        }

        if(moneyAmount >= espressoPrice) {
            System.out.println("Вы можете купить эспрессо");
            canBuySomething = true;
        }

        if(moneyAmount >= waterPrice) {
            System.out.println("Вы можете купить воду");
            canBuySomething = true;
        }

        if(moneyAmount >= milkPrice) {
            System.out.println("Вы можете купить молоко");
            canBuySomething = true;
        }

        if(canBuySomething == false) {
            System.out.println("Недостаточно средств! :( Изучайте Java и зарабатывайте много!))");
        }
    }
}
