public class Base{
    public static void main(String[] args){
        boolean flag = false;
        char ch = 'D';
        byte b = 12;
        short s = 24;
        int i = 257;
        long l = 849L;
        float f = 3.1415F;
        double d = 2.71828;

        System.out.println(String.format("flag: %b", flag));
        System.out.println(String.format("char: %c", ch));
        System.out.println(String.format("byte: %d", b));
        System.out.println(String.format("short: %d", s));
        System.out.println(String.format("int: %d", i));
        System.out.println(String.format("long: %d", l));
        System.out.println(String.format("float: %f", f));
        System.out.println(String.format("double: %f", d));
        
    }
}