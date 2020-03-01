package job4j.tictactoe;

import javafx.scene.shape.Rectangle;
/*
Класс Figure3T - отвечает за клетку на поле.
Он содержит методы - имеет ли клетка крестик или нолик. или пустая.

По нажатию на левую кнопку мыши выставляется крестик, на правую нолик.
 */
public class Figure3T extends Rectangle { // наследуется от класса ПРЯМОУГОЛЬНИК
    private boolean markX = false; //
    private boolean markO = false; //

    public Figure3T() {
    }

    public Figure3T(boolean markX, boolean markO) { // может быть true одновременно?????
        this.markX = markX;
        this.markO = markO;
    }

    /*
    public Figure3T(boolean markX) {
        this.markX = markX;
        this.markO = !markX;
    }
     */

    public void take(boolean markX) { // устанавливаем значение обоих полей, передавая значение Х - true или  false
            this.markX = markX;
            this.markO = !markX;
    }

    public boolean hasMarkX() { // true, если клетка помечена крестиком
        return this.markX;
    }

    public boolean hasMarkO() { // true, если клетка помечена ноликом
        return this.markO;
    }
}
