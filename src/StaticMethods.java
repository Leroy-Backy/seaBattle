import java.util.Scanner;

public class StaticMethods {
    public static void clearScreen(){
        for(int i = 0; i < 40; i++) System.out.println();
    }

    public static void start(){
        System.out.print("Привет это морской бой. Сечас игроки по очереди будут расставлять корабли, а потом рандом решит кто начинает.\n" +
                "Введите имя первого игрока: ");
    }

    public static void buildBattleground(Cell[][] board){
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                board[i][j] = new Cell();
            }
        }
        board[0][0].setSymbol("⚪️");

        board[0][1].setSymbol("0️⃣");
        board[0][2].setSymbol("1️⃣");
        board[0][3].setSymbol("2️⃣");
        board[0][4].setSymbol("3️⃣");
        board[0][5].setSymbol("4️⃣");
        board[0][6].setSymbol("5️⃣");
        board[0][7].setSymbol("6️⃣");
        board[0][8].setSymbol("7️⃣");
        board[0][9].setSymbol("8️⃣");
        board[0][10].setSymbol("9️⃣");

        board[1][0].setSymbol("0️⃣");
        board[2][0].setSymbol("1️⃣");
        board[3][0].setSymbol("2️⃣");
        board[4][0].setSymbol("3️⃣");
        board[5][0].setSymbol("4️⃣");
        board[6][0].setSymbol("5️⃣");
        board[7][0].setSymbol("6️⃣");
        board[8][0].setSymbol("7️⃣");
        board[9][0].setSymbol("8️⃣");
        board[10][0].setSymbol("9️⃣");
    }
}
