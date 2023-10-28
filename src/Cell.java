import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Cell {

  public int x;
  public int y;
  public boolean played;

  public Cell(int x, int y) {
    this.x = x;
    this.y = y;
    this.played = false;
  }

  public Set<Cell> aliveNeighbours(Set<Cell> livingCells) {
    Set<Cell> neighbours = neighbours();
    neighbours.retainAll(livingCells);
    return neighbours;
  }

  public Set<Cell> deadNeighbours(Set<Cell> livingCells) {
    Set<Cell> neighbours = neighbours();
    neighbours.removeAll(livingCells);
    return neighbours;
  }
  public Set<Cell> neighbours() {
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

  public void playSound(){
    this.played = true;
    SoundLibrary.play(this.y);
  }

  public boolean isPlayed() {
    return played;
  }
}
