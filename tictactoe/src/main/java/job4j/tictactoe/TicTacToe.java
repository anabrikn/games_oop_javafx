package job4j.tictactoe;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/*
Класс TicTacToe - реализует визуальный компонент.
По нажатию на левую кнопку мыши выставляется крестик, на правую нолик.
 */
public class TicTacToe extends Application {
    private static final String JOB4J = "Крестики-нолики www.job4j.ru";
    private final int size = 3; // переменная хранящая число для обозначения размера поля (сторона квадрата)
    private final Figure3T[][] cells = new Figure3T[size][size]; // создается двумерный массив клеток (класс Figure3T) 3 по 3
    private final Logic3T logic = new Logic3T(cells); // создается экз. класса Logic3T и передаем туда свой двумерный массив клеток (выяснить зачем)

    // создает объект Figure3T для заполнения группы прямоугольников (поля) и заполнения двумерного массива Figure3T[][] cells
    private Figure3T buildRectangle(int x, int y, int size) {
        Figure3T rect = new Figure3T(); // создается объект Figure3T
        rect.setX(x * size); // возможно устанавливает координату точки X на поле, из которой клетка начнет отрисовываться
        rect.setY(y * size); // возможно устанавливает координату точки Y на поле, из которой клетка начнет отрисовываться
        rect.setHeight(size); // устанавливает высоту клетки?
        rect.setWidth(size); // устанавливает ширину клетки?
        rect.setFill(Color.WHITE); // заливает клетку (клетки?) белым цвветом
        rect.setStroke(Color.BLACK); // заливает рамку клетки (клеток?) черным цвветом
        return rect; // возвращает объект Figure3T
    }

    // метод для постройки/отрисовки нолика
    private Group buildMarkO(double x, double y, int size) {
        Group group = new Group();
        int radius = size / 2;
        Circle circle = new Circle(x + radius, y + radius, radius - 10);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
        group.getChildren().add(circle);
        return group;
    }

    // метод для постройки/отрисовки крестика
    private Group buildMarkX(double x, double y, int size) {
        Group group = new Group();
        group.getChildren().addAll(
                new Line(
                        x + 10, y  + 10,
                        x + size - 10, y + size - 10
                ),
                new Line(
                        x + size - 10, y + 10,
                        x + 10, y + size - 10
                )
        );
        return group;
    }

    // метод для вывода сообщения
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(JOB4J);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // проверка статуса игры? есть ли еще свободные поля
    private boolean checkState() { // метод не работает, потому что this.logic.hasGap() всегда возвращет true
        boolean gap = this.logic.hasGap(); // !!! проверяем экземпляр класса logic на наличие пустых клеток !!!
        if (!gap) {
            this.showAlert("Все поля запонены! Начните новую Игру!");
        }
        return gap;
    }

    // проверка на выигрышную комбинацию - просто вызываются методы isWinnerX() и isWinnerO()
    private void checkWinner() {
        // стоит сделать проверки независимыми
        if (this.logic.isWinnerX()) {  // !!! проверяем экземпляр класса logic на наличие выигрышных комбинаций для Х !!!
            this.showAlert("Победили Крестики! Начните новую Игру!");
        } else if (this.logic.isWinnerO()) { // !!! проверяем экземпляр класса logic на наличие выигрышных комбинаций для О !!!
            this.showAlert("Победили Нолики! Начните новую Игру!");
        }
    }

    // события мыши
    private EventHandler<MouseEvent> buildMouseEvent(Group panel) {
        return event -> {
            Figure3T rect = (Figure3T) event.getTarget();
            if (this.checkState()) { // если еще есть свободные поля
                // добавить проверку что в этой клетке не стоит уже другой значек, только тогда ставить


                if (event.getButton() == MouseButton.PRIMARY) {  // при нажатии на левую кнопку мыши? (крестик)
                    rect.take(true); // <------- ставим в клетке true для крестика и false для нолика
                    panel.getChildren().add(
                            this.buildMarkX(rect.getX(), rect.getY(), 50) // ищем координаты фигуры и отрисовываем крестик методом buildMarkX
                    );
                } else { // если нажата НЕ левая кнопка мыши? (нолик)
                    rect.take(false); // <------- ставим в клетке false для крестика и true для нолика
                    panel.getChildren().add(
                            this.buildMarkO(rect.getX(), rect.getY(), 50) // ищем координаты фигуры и отрисовываем нолик методом buildMarkO
                    );
                }
                this.checkWinner(); // проверяем есть ли выигрышные комбинации
                this.checkState(); // проверяем есть ли свободные клетки
            }
        };
    }

    private Group buildGrid() {
        Group panel = new Group(); // создаем группа прямоугольников
        for (int y = 0; y != this.size; y++) { // в этих циклах собирает игровое поле из клеток (3 на 3)
            for (int x = 0; x != this.size; x++) {
                Figure3T rect = this.buildRectangle(x, y, 50); // создаем прямоугольник
                this.cells[y][x] = rect; // ячейке двумерного массива Figure3T[][] cells с координатами Y и X присваивается созданный прямоугольник
                panel.getChildren().add(rect); // добавляем этот прямоугольник на панель
                rect.setOnMouseClicked(this.buildMouseEvent(panel)); // даем ему слушателя мыши
            }
        }
        //каждый прямоугольник добавлен и в двумерный массив Figure3T[][] cells
        // и в Group panel, которую и возвращает этот метод,
        // а так же ему назначен слушатель событий
        return panel; // возвращаем панель, на которую добавлены все прямоугольники
    }


    @Override
    public void start(Stage stage) {
        BorderPane border = new BorderPane();
        HBox control = new HBox();
        control.setPrefHeight(40);
        control.setSpacing(10.0);
        control.setAlignment(Pos.BASELINE_CENTER);
        Button start = new Button("Начать");
        start.setOnMouseClicked(
                event -> border.setCenter(this.buildGrid())
        );
        control.getChildren().addAll(start);
        border.setBottom(control);
        border.setCenter(this.buildGrid());
        stage.setScene(new Scene(border, 300, 300));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
    }
}
