public class ParenMatch{
    public static String opening = "([{";
    public static String closing = ")]}";

    /** retorna se a uma expressão contendo parenteses está correta ou não
     * 
     * @param X uma string com a expressão
     * @param n tamanho da expressão
     * 
     * @return true se está correta, false caso contrário
     * 
     */
    public static boolean validate(String X){
        int n = X.length();
        NodeStack<Character> S = new NodeStack<>();

        for(int i = 0; i < n; i++){
            if(isOpening(X.charAt(i))){
                S.push(X.charAt(i));
            }

            if(isClosing(X.charAt(i))){
                if(S.isEmpty()) return false;
                if(!isMatching(S.pop(), X.charAt(i))) return false;
            }
        }

        if(S.isEmpty()) return true;
        return false;

    }

    /** verifica se é um símbolo de abertura */
    public static boolean isOpening(char p){
        return opening.indexOf(p) != -1;
    }

    /** verifica se é um símbolo de fechamento */
    public static boolean isClosing(char p){
        return closing.indexOf(p) != -1;
    }

    /** verifica se dois caracteres correspondem entre si */
    public static boolean isMatching(char o, char c){
        return opening.indexOf(o) == closing.indexOf(c);
    }

    public static void main(String[] args){
        String exp1 = ")(abc){((def))[]}", exp2 = "{([...])(([ghi]))}", exp3 = "(.", exp4 = "({[])}", exp5 = "(({})([]{[]}))";

        System.out.println(exp1 + " -> " + validate(exp1));
        System.out.println(exp2 + " -> " + validate(exp2));
        System.out.println(exp3 + " -> " + validate(exp3));
        System.out.println(exp4 + " -> " + validate(exp4));
        System.out.println(exp5 + " -> " + validate(exp5));

    }
}