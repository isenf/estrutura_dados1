public class CreditCard{
    private String number;
    private String name;
    private String bank;
    private double balance;
    private int limit;

    CreditCard(String no, String nm, String bk, double bal, int lim){
        number = no;
        name = nm;
        bank = bk;
        balance = bal;
        limit = lim;
    }

    public String getNumber(){
        return number;
    }

    public String getName(){
        return name;
    }

    public String getBank(){
        return bank;
    }

    public double getBalance(){
        return balance;
    }

    public int getLimit(){
        return limit;
    }

    public boolean chargeIt(double price){
        if(price + balance > (double) limit)
            return false;
        
        balance += price * 1.02;        // debita juros de 2%
        return true;
    }

    public void makePayment(double payment){
        balance -= payment;
    }

    public static void printCard(CreditCard c){
        System.err.println("Number: " + c.getNumber());
        System.err.println("Name: " + c.getName());
        System.err.println("Bank: " + c.getBank());
        System.err.println("Balance: " + c.getBalance());
        System.err.println("Limit: " + c.getLimit());
    }
}