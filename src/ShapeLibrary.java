import java.util.Set;

public class ShapeLibrary {

  public static void glide(int startX, int startY, Set<Cell> livingCells) {
    for (int i = 0; i < 4; i++) {
      livingCells.add(new Cell(i + startX, startY));
      livingCells.add(new Cell(i + startX, startY + 8));
    }

    for (int i = 0; i < 8; i++) {
      livingCells.add(new Cell(i + startX - 2, startY + 2));
      livingCells.add(new Cell(i + startX - 2, startY + 6));
    }

    for (int i = 0; i < 12; i++) {
      livingCells.add(new Cell(i + startX - 4, startY + 4));
    }
  }

  public static void ship(int startX, int startY, Set<Cell> livingCells) {
    livingCells.add(new Cell(3 + startX, 3 + startY));
    livingCells.add(new Cell(2 + startX, 3 + startY));
    livingCells.add(new Cell(4 + startX, 3 + startY));
    livingCells.add(new Cell(3 + startX, 4 + startY));
  }
}
