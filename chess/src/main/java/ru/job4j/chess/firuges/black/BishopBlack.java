package ru.job4j.chess.firuges.black;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell destination) throws IllegalStateException {
//        if (!isDiagonal(source, dest)) {
//            throw new IllegalStateException(
//                    String.format("Could not way by diagonal from %s to %s", source, dest)
//            );
//        }
        int size = Math.abs(destination.getX() - source.getX());
        Cell[] steps = new Cell[size];
        int deltaX = source.getX() < destination.getX() ? 1 : -1;
        int deltaY = source.getY() < destination.getY() ? 1 : -1;
        int x = source.getX();
        int y = source.getY();

        for (int index = 0; index < size; index++) {
            if (deltaX == 1) {
                x++;
            } else {
                x--;
            }
            if (deltaY == 1) {
                y++;
            } else {
                y--;
            }


            steps[index] = Cell.findByXY(x, y);
        }
        return steps;
    }

    public boolean isDiagonal(Cell source, Cell dest) {
        //TODO check diagonal
        return false;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
