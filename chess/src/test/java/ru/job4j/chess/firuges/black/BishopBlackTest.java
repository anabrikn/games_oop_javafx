package ru.job4j.chess.firuges.black;

import org.junit.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
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
}