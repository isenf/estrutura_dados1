import java.awt.Toolkit;
import java.util.Scanner;

/** simulação de um jogo da velha (sem estratégia) */
public class TicTacToe{
    protected static final int X = 1, O = -1;
    protected static final int EMPTY = 0;
    protected int board[][] = new int[3][3];
    protected int player;

    /**
     * construtor
     */
    public TicTacToe(){
        clearBoard();
    }

    /**
     * limpa o tabuleiro
     */
    public void clearBoard(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = EMPTY;
            }
        }
        player = X;
    }

    /** coloca X ou O na posição i, j */
    public void putMark(int i, int j) throws IllegalArgumentException {
        if ((i < 0) || (i > 2) || (j < 0) || (j > 2)) {
            throw new IllegalArgumentException("invalid board position");
        }

        if (board[i][j] != EMPTY) {
            throw new IllegalArgumentException("board position occupied");
        }
        
        board[i][j] = player;
        player = -player;
    }

    /** verifica se a config do tabuleiro é vencedora para um determinado jogador */
    public boolean isWin(int mark){
        return ((board[0][0] + board[0][1] + board[0][2] == mark * 3)       // linha 0
                || (board[1][0] + board[1][1] + board[1][2] == mark * 3)    // linha 1
                || (board[2][0] + board[2][1] + board[2][2] == mark * 3)    // linha 2
                || (board[0][0] + board[1][0] + board[2][0] == mark * 3)    // coluna 0
                || (board[0][1] + board[1][1] + board[2][1] == mark * 3)    // coluna 1
                || (board[0][2] + board[1][2] + board[2][2] == mark * 3)    // coluna 2
                || (board[0][0] + board[1][1] + board[2][2] == mark * 3)    // diagonal principal
                || (board[0][2] + board[1][1] + board[2][0] == mark * 3));  // diagonal secundária
    }

    /**
     * retorna o jogador vencedor ou indica um empate
     */
    public int winner(){
        if(isWin(X)) return X;
        else if (isWin(O)) return O;
        else return 0;
    }

    /**
     * verifica o tabuleiro está cheio (empate)
     * @return true se for um empate
     */
    public boolean tie(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(board[i][j] == EMPTY){
                    return false;
                }
            }
        }
        return true;
    }

    public String toString(){
        String s = "";

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                switch(board[i][j]){
                    case X:
                        s += "X";
                        break;
                    case O:
                        s += "O";
                        break;
                    case EMPTY:
                        s += " ";
                        break;
                }
                if(j < 2) s += "|";
            }
            if(i < 2) s += "\n------\n";

        }
        return s;
    }

    public static void main(String[] args){
        System.out.print("\007");
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Deseja jogar jogo da velha? (true/false)");
        boolean isPlaying = scanner.nextBoolean();

        while((isPlaying)){
            TicTacToe game = new TicTacToe();
            boolean isOver = false;

            while(!isOver){
                System.out.println(game);

                int i, j, player = game.player;
                System.out.printf("Vez do jogador %c\n", player == 1 ? 'X' : 'O');

                System.out.print("Insira a linha (de 0 a 2): ");
                i = scanner.nextInt();
                System.out.print("\nInsira a coluna(de 0 a 2): ");
                j = scanner.nextInt();

                game.putMark(i, j);

                if(game.isWin(player)){
                    Toolkit.getDefaultToolkit().beep();
                    System.out.println(game);
                    System.out.printf("\nO jogador %c venceu o jogo!!\n", player == 1 ? 'X' : 'Y');
                    isOver = true;
                } else if(game.tie()){
                    System.out.println(game);
                    System.out.println("\nEmpate! O tabuleiro está cheio");
                    isOver = true;
                }
            }

            System.out.println("\n\nDeseja jogar novamente? (true/false)");
            isPlaying = scanner.nextBoolean();    
        }

        System.out.println("\nFim do programa!");
        scanner.close();
    }


}