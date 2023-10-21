import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cell {

  public int x;
  public int y;

  public Cell(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Set<Cell> aliveNeighbours(Set<Cell> livingCells) {
    Set<Cell> neighbours = neighboors();
    neighbours.retainAll(livingCells);
    return neighbours;
  }

  public Set<Cell> deadNeighbours(Set<Cell> livingCells) {
    Set<Cell> neighbours = neighboors();
    neighbours.removeAll(livingCells);
    return neighbours;
  }
  public Set<Cell> neighboors() {
    Set<Cell> neighbours = new HashSet<>();
    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (i == 0 && j == 0) continue;
        neighbours.add(new Cell(this.x + i, this.y + j));
      }
    }
    return neighbours;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cell cell = (Cell) o;
    return x == cell.x && y == cell.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }

  public String toString() {
    return "[" + x + "," + y + "]";
  }
}
