public class Test{
    public static void main(String[] args){
        CreditCard wallet[] = new CreditCard[10];

        wallet[0] = new CreditCard("1938 4812 1948 1938", "John B.", "Bank A", 0.0, 2500);
        wallet[1] = new CreditCard("1348 2845 1935 1598", "John B.", "Bank B", 0.0, 3500);
        wallet[2] = new CreditCard("1194 1481 1941 3841", "John B.", "Bank C", 0.0, 5000);

        for(int i=0; i<=16; i++){
            wallet[0].chargeIt((double)i);
            wallet[1].chargeIt(2.0*i);
            wallet[2].chargeIt((double)3*i);
        }

        for(int i=0; i < 3; i++){
            CreditCard.printCard(wallet[i]);

            while(wallet[i].getBalance() > 100.0){
                wallet[i].makePayment(100.0);
                System.out.println("New balance: " + wallet[i].getBalance());
            }
        }
    }
}