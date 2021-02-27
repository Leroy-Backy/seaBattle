import java.util.*;

public class Player {
    private String name;
    // каждой клетке с кораблем присваивается айди корабля и они в этом листе. Я буду проверять остался ли корабль наличием его айди здесь
    private List<Integer> ships = new LinkedList<>();
    private Cell[][] seaBoard = new Cell[11][11];
    private int shipsRemain = 20;

    public Player(String name){
        this.name = name;
        StaticMethods.buildBattleground(seaBoard);
    }

    // метод, чтобы сделать ход
    public void turn(Player enemy){
        if ((this.shipsRemain == 0) || enemy.getShipsRemain() == 0) return;
        Scanner scanner = new Scanner(System.in);
        enemy.printBoard();
        System.out.print(this.name + ", введите координаты клетки, в которую хотите выстрелить: ");
        while (shoot(scanner.nextLine(), enemy) != 1) {
            if ((this.shipsRemain == 0) || enemy.getShipsRemain() == 0) return;
            System.out.print(this.name + ", введите координаты клетки, в которую хотите выстрелить: ");
        }

    }

    // метод делающий выстрел
    private int shoot(String coords, Player enemy){
        if(!Character.isDigit(coords.charAt(0)) || !Character.isDigit(coords.charAt(2))){
            System.out.println("Введена не цифра.");
            return 0;                                                                          //возвращает 0 при ошибке
        } else if(coords.length() != 3 || coords == null){
            System.out.println("Не правильное количество цифр в одной палубею");
            return 0;
        } else if(Integer.parseInt(String.valueOf(coords.charAt(0))) < 0 || Integer.parseInt(String.valueOf(coords.charAt(0))) > 9
                || Integer.parseInt(String.valueOf(coords.charAt(2))) < 0 || Integer.parseInt(String.valueOf(coords.charAt(2))) > 9){
            System.out.println("Использованы неразрешенные цифры.");
            return 0;
        } else {
            int x = Integer.parseInt(String.valueOf(coords.charAt(0))) + 1;
            int y = Integer.parseInt(String.valueOf(coords.charAt(2))) + 1;
            if(enemy.getSeaBoard()[x][y].getFilled()){
                System.out.println("Вы уже стреляли сюда, выберите новую клетку");
                return 0;
            } else {
                if(enemy.getSeaBoard()[x][y].getShipId() == 0) {
                    enemy.getSeaBoard()[x][y].setSymbol(Sym.PAINTED.toString());
                    enemy.getSeaBoard()[x][y].setFilled(true);
                    enemy.printBoard();
                    return 1;                                                                 //возвращает 1 при промахе
                } else {
                    enemy.getSeaBoard()[x][y].setFilled(true);
                    enemy.getSeaBoard()[x][y].setSymbol(Sym.CRUSHED.toString());
                    for(int i = 0; i < enemy.getShips().size(); i++){
                        if(enemy.getShips().get(i) == enemy.getSeaBoard()[x][y].getShipId()){
                            enemy.getShips().remove(i);
                            break;
                        }
                    }
                    if(enemy.getShips().contains(enemy.getSeaBoard()[x][y].getShipId())){
                        System.out.println(this.name + " попал по кораблю!");
                    } else {
                        System.out.println(this.name + " потопил корабль!");
                    }
                    enemy.setShipsRemain(enemy.getShipsRemain() - 1);
                    enemy.printBoard();
                    return 2;                                                               //возвращает 2 при попадании
                }
             }
        }
    }

    public List<Integer> getShips() {
        return ships;
    }

    public Cell[][] getSeaBoard() {
        return seaBoard;
    }

    public String getName() {
        return name;
    }

    public int getShipsRemain() {
        return shipsRemain;
    }

    public void setShipsRemain(int shipsRemain) {
        this.shipsRemain = shipsRemain;
    }

    //метод для добавления всех кораблей
    public void insertShips(){
        int shipId = 1;
        int ship4 = 0, ship3 = 0, ship2 = 0, ship1 = 0;
        System.out.println("Сейчас вы будете расставлять корабли. Вводите координаты клеток таким образом: x1,y1;x2,y2....");
        System.out.println("Игрок " + name + " Расставляет корабли.");

        Scanner scanner = new Scanner(System.in);

        while(ship4 < 1){
            System.out.print("Введите координаты четырехпалубного корабля: ");
            if(analyseLine(scanner.nextLine(), 4, shipId)){               // если корабль добавлся, то метод возвращает тру
                ship4++;
                shipId++;
                printBoard();
            }
        }
        while (ship3 < 2){
            System.out.print("Введите координаты трехпалубного корабля: ");
            if(analyseLine(scanner.nextLine(), 3, shipId)){               // если корабль добавлся, то метод возвращает тру
                ship3++;
                shipId++;
                printBoard();
            }
        }
        while (ship2 < 3){
            System.out.print("Введите координаты двухпалубного корабля: ");
            if(analyseLine(scanner.nextLine(), 2, shipId)){               // если корабль добавлся, то метод возвращает тру
                ship2++;
                shipId++;
                printBoard();
            }
        }
        while (ship1 < 4){
            System.out.print("Введите координаты  однопалубного корабля: ");
            if(analyseLine(scanner.nextLine(), 1, shipId)){               // если корабль добавлся, то метод возвращает тру
                ship1++;
                shipId++;
                printBoard();
            }
        }
        resetSymbols();
        System.out.println("Вы расставили свои корабли,запомните расположение и нажмите enter, чтобы передать управление второму игроку.");
        scanner.nextLine();
        StaticMethods.clearScreen();
    }


    // анализ строки и добавление кораблей
    private boolean analyseLine(String line, int size, int shipId){
        int[] x = new int[size];
        int[] y = new int[size];
        String[] array = line.split(";");

        boolean incorrectNum = false;                                    // проверка на правильность введенной цифры
        boolean incorrectAmountOfNums = false;                           // проверка на правильное количество цифр в одной координате
        boolean isFilled = false;                                        // проверка занята ли клетка
        boolean isNotDigit = false;                                      // на случай, если знак не является цифрой
        for(int i = 0; i < size; i++){
            if(!Character.isDigit(array[i].charAt(0)) || !Character.isDigit(array[i].charAt(2))){
                isNotDigit = true;
                break;
            } else if(array.length != size){
                break;
            }
            x[i] = Integer.parseInt(String.valueOf(array[i].charAt(0))) + 1;
            y[i] = Integer.parseInt(String.valueOf(array[i].charAt(2))) + 1;
            if(x[i] < 1 || x[i] > 10 || y[i] < 1 || y[i] > 10){
                incorrectNum = true;
                break;
            }
            if(array[i].length() > 3){
                incorrectAmountOfNums = true;
                break;
            }
            if(seaBoard[x[i]][y[i]].getFilled()) {
                isFilled = true;
                break;
            }
        }

        boolean incorrectPosition = false;                                // неправильно расположенные палубы
        if(size > 1){
            if(!((checkArr(x) == 0 && checkArr(y) == 1) || (checkArr(y) == 0 && checkArr(x) == 1)))
                incorrectPosition = true;
            if(checkArr(x) == 1){
                Arrays.sort(x);
            } else if(checkArr(y) == 1){
                Arrays.sort(y);
            }
        }

        if(isNotDigit){
            System.out.println("Введена не цифра.");
            return false;
        } else if(array.length != size){
            System.out.println("Не правильное количество палуб.");
            return false;
        } else if(incorrectNum) {
            System.out.println("Использованы неразрешенные цифры.");
            return false;
        } else if(incorrectPosition){
            System.out.println("Палубы корабля расположены не рядомю");
            return false;
        } else if(incorrectAmountOfNums){
            System.out.println("Не правильное количество цифр в одной палубею");
            return false;
        } else if(isFilled){
            System.out.println("Одна из клеток уже занята, или находится рядом с кораблем");
            return false;
        } else {
            int i, j, n, m;            // i - j -> ширина заполнения занятой территории по оси х. n - m -> по оси у.

            if((x[0] - 1) < 1) {
                i = 1;
            } else {
                i = x[0] - 1;
            }
            if((x[size-1] + 1) > 10) {
                j = 10;
            } else {
                j = x[size-1] + 1;
            }
            if((y[0] - 1) < 1) {
                n = 1;
            } else {
                n = y[0] - 1;
            }
            if((y[size-1] + 1) > 10) {
                m = 10;
            } else {
                m = y[size-1] + 1;
            }

            for(; i <= j; i++){                                          // заполняю клетки на которые нельзя ставить корабли
                for(int k = n; k <= m; k++){
                    seaBoard[i][k].setFilled(true);
                    seaBoard[i][k].setSymbol(Sym.PAINTED.toString());
                }
            }

            for(int g = 0; g < size; g++){                               // заполняю клетки с кораблями и присваиваю им айди корабля
                seaBoard[x[g]][y[g]].setShipId(shipId);
                seaBoard[x[g]][y[g]].setSymbol(Sym.SHIP.toString());
                ships.add(shipId);
            }
            return true;
        }
    }

    // проверка того рядом ли находятся клетки корабля введенные игроком
    private int checkArr(int[] array){
        int[] temp = array;
        Arrays.sort(temp);
        boolean equal = true;
        boolean growth = true;

        for(int i = 0; i < temp.length - 1; i++ ){
            if(temp[i] != temp[i+1]) equal = false;
            if(temp[i+1] - temp[i] != 1) growth = false;
        }

        if(equal == true){
            return 0;                    // Если 0, то массив с одинаковыми цифрами
        } else if(growth == true){
            return 1;                    // Если 1, то масив с возрастающими на 1 цифрами
        } else {
            return 2;                    // Если 2, то рандомный
        }

    }

    private void resetSymbols(){
        for(int i = 1; i < 11; i++){
            for(int j = 1; j < 11; j++){
                seaBoard[i][j].setSymbol(Sym.CLEAR.toString());
                seaBoard[i][j].setFilled(false);
            }
        }
    }

    // Высвечивание поля
    public void printBoard(){
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                System.out.print(seaBoard[i][j]);
            }
            System.out.println();
        }
    }
}
