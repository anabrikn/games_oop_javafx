package ru.job4j.chess.firuges.black;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


import org.junit.Assert;


public class BishopBlackTest {
    @Test
    public void whenCreateBishopBlackPositionIsSame() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        assertThat(Cell.C1, is(bishopBlack.position()));
    }

    @Test
    public void whenCopyBishopBlackPositionIsRight() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Figure bishopBlack1 = bishopBlack.copy(Cell.G5);
        assertThat(bishopBlack1.position(), is(Cell.G5));
    }

    @Test
    public void whenBishopBlackWayUpRight() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        Cell[] bishopCells = bishopBlack.way(Cell.C1, Cell.G5);
        Cell[] expected = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        assertThat(bishopCells, is(expected));
    }

    @Test
    public void whenBishopBlackWayUpLeft() {
        BishopBlack bishopBlack = new BishopBlack(Cell.F1);
        Cell[] bishopCells = bishopBlack.way(Cell.F1, Cell.A6);
        Cell[] expected = {Cell.E2, Cell.D3, Cell.C4, Cell.B5, Cell.A6};
        assertThat(bishopCells, is(expected));
    }

    @Test
    public void whenBishopBlackWayDownRight() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C8);
        Cell[] bishopCells = bishopBlack.way(Cell.C8, Cell.H3);
        Cell[] expected = {Cell.D7, Cell.E6, Cell.F5, Cell.G4, Cell.H3};
        assertThat(bishopCells, is(expected));
    }

    @Test
    public void whenBishopBlackWayDownLeft() {
        BishopBlack bishopBlack = new BishopBlack(Cell.F8);
        Cell[] bishopCells = bishopBlack.way(Cell.F8, Cell.A3);
        Cell[] expected = {Cell.E7, Cell.D6, Cell.C5, Cell.B4, Cell.A3};
        assertThat(bishopCells, is(expected));
    }

    @Test(expected = IllegalStateException.class)
    public void whenFigureWayIsNotCorrectThenException() {
        BishopBlack bishopBlack = new BishopBlack(Cell.C1);
        bishopBlack.way(bishopBlack.position(), Cell.C3);
    }

    @Test
    public void whenRightPathChosenThenIsDiagonalTrue() {
        BishopBlack bBishop = new BishopBlack(Cell.D4);
        Cell source = Cell.D4;
        Cell dest = Cell.A7;
        boolean isDiagonal = bBishop.isDiagonal(source, dest);
        Assert.assertThat(isDiagonal, is(true));
    }

    @Test
    public void whenWrongPathChosenThenIsDiagonalFalse() {
        BishopBlack bBishop = new BishopBlack(Cell.C3);
        Cell source = Cell.C3;
        Cell dest = Cell.E2;
        boolean isDiagonal = bBishop.isDiagonal(source, dest);
        Assert.assertThat(isDiagonal, is(false));
    }

}