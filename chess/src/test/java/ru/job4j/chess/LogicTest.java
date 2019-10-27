package ru.job4j.chess;

import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.black.BishopBlack;
import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.core.Is.is;

public class LogicTest {
    @Test
    public void whenWayHasNotOtherFiguresThenTrue() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.C1));
        boolean result = logic.move(Cell.C1, Cell.H6);
        Assert.assertThat(result, is(true));
    }

    @Test
    public void whenWayHaveOtherFiguresThenFalse() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.F1));
        logic.add(new BishopBlack(Cell.C4));
        boolean moveResult = logic.move(Cell.F1, Cell.A6);
        Assert.assertThat(moveResult, is(false));
    }

    @Test
    public void whenWayHaveOtherFiguresInEndPathThenFalse() {
        Logic logic = new Logic();
        logic.add(new BishopBlack(Cell.F1));
        logic.add(new BishopBlack(Cell.C4));
        boolean moveResult = logic.move(Cell.F1, Cell.C4);
        Assert.assertThat(moveResult, is(false));
    }
}
