package job4j.tictactoe;

import java.util.Arrays;
import java.util.function.Predicate;
/*
Класс Logic3T отвечает за проверку логики.
В этом задании тебе нужно до реализовать эти методы.
 */
public class Logic3T {
    private final Figure3T[][] table; //массив, хранит значения игрового поля, передается из класса TicTacToe при создании экземпляра Logic3T

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean fillBy(Predicate<Figure3T> predicate, int startX, int startY, int deltaX, int deltaY) {
        boolean result = true; // переменная для хранения результата
        for (int index = 0; index != this.table.length; index++) { // проход по строкам двумерного массива - игрового поля
            Figure3T cell = this.table[startX][startY]; // в переменную Figure3T cell помещаем значение двумерного массива Figure3T[][] table с индексами startX и startY
            startX += deltaX; // стартовое значение Х увеличиваем на значение дельта Х
            startY += deltaY; // стартовое значение Y увеличиваем на значение дельта Y
            if (!predicate.test(cell)) { // предикат здесь это проверка что для объекта Figure3T cell методы hasMarkX или hasMarkO возвращают false
                result = false;
                break;
            }
        }
        return result;
    }

    /*
    public boolean isWinnerX() - проверяет есть ли в поле выигрышные комбинации для Крестика.
     */
    public boolean isWinnerX() {
        return this.isWinCombination(Figure3T::hasMarkX);
    }

    /*
    public boolean isWinnerO() - проверяет есть ли в поле выигрышные комбинации для Нолика.
     */
    public boolean isWinnerO() {
        return this.isWinCombination(Figure3T::hasMarkO);
    }

    public boolean isWinCombination(Predicate<Figure3T> predicate) {
        return this.fillBy(predicate, 0, 0, 1, 0) // вертикаль - первый столбик
                || this.fillBy(predicate, 0, 1, 1, 0) // вертикаль - второй столбик
                || this.fillBy(predicate, 0, 2, 1, 0)  // вертикаль - третий столбик
                || this.fillBy(predicate, 0, 0, 0, 1)  // горизонталь - первая строка
                || this.fillBy(predicate, 1, 0, 0, 1)  // горизонталь - первая строка
                || this.fillBy(predicate, 2, 0, 0, 1)  // горизонталь - первая строка
                || this.fillBy(predicate, 0,0, 1, 1) // по диагонали сверху низ с лева на право
                || this.fillBy(predicate, this.table.length - 1 , 0, -1, 1); // по диагонали снизу вверх с лева на право
    }

    /*
    public boolean hasGap() - проверяет, если ли пустые клетки для новых ходов.
     */
    public boolean hasGap() {
        return Arrays.stream(this.table).flatMap(Arrays::stream).anyMatch(cell -> !cell.hasMarkO() && !cell.hasMarkX());
    }
}

/*
allMatch(Predicate predicate) — возвращает true, если все элементы стрима удовлетворяют условию. Если встречается какой-либо элемент, для которого результат вызова функции-предиката будет false, то оператор перестаёт просматривать элементы и возвращает false:

Stream.of(1, 2, 3, 4, 9).allMatch(x -> x <= 7);//false
anyMatch(Predicate predicate) — вернет true, если хотя бы один элемент стрима удовлетворяет условию predicate:

Stream.of(1, 2, 3, 4, 9).anyMatch(x -> x >= 7);//true
noneMatch(Predicate predicate) — вернёт true, если, пройдя все элементы стрима, ни один не удовлетворил условию predicate:

Stream.of(1, 2, 3, 4, 9).noneMatch(x -> x >= 7);//false
 */