import java.util.*;

public class ShapeLibrary {


  public static Set<Cell> glide(int startX, int startY) {
    Set<Cell> cells = new HashSet<>();
    for (int i = 0; i < 4; i++) {
      cells.add(new Cell(i + startX, startY));
      cells.add(new Cell(i + startX, startY + 8));
    }

    for (int i = 0; i < 8; i++) {
      cells.add(new Cell(i + startX - 2, startY + 2));
      cells.add(new Cell(i + startX - 2, startY + 6));
    }

    for (int i = 0; i < 12; i++) {
      cells.add(new Cell(i + startX - 4, startY + 4));
    }
    return cells;
  }

  public static Set<Cell> ship(int startX, int startY) {
    Set<Cell> cells = new HashSet<>();
    cells.add(new Cell(3 + startX, 3 + startY));
    cells.add(new Cell(2 + startX, 3 + startY));
    cells.add(new Cell(4 + startX, 3 + startY));
    cells.add(new Cell(3 + startX, 4 + startY));
    return cells;
  }
}

