import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StaticMethods.start();
        Player player1 = new Player(sc.nextLine());
        System.out.print("Введите имя второго игрока: ");
        Player player2 = new Player(sc.nextLine());
        System.out.println();

        player1.insertShips();
        player2.insertShips();

        Random random = new Random();
        int rand = random.nextInt(2);

        if(rand == 0){
            System.out.println("Первый ходит " + player1.getName());
            while((player1.getShipsRemain() != 0) && (player2.getShipsRemain() != 0)){
                player1.turn(player2);
                player2.turn(player1);
            }
        } else {
            System.out.println("Первый ходит " + player2.getName());
            while((player1.getShipsRemain() != 0) && (player2.getShipsRemain() != 0)){
                player2.turn(player1);
                player1.turn(player2);
            }
        }
        if(player1.getShipsRemain() == 0){
            System.out.println(player2.getName() + " ПОБЕДИЛ!");
        } else {
            System.out.println(player1.getName() + " ПОБЕДИЛ!");
        }
    }
}
