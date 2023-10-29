import java.awt.*;
import java.util.*;
import java.util.List;


public class Cell {

  public int x;
  public int y;

  public int size;
  public int thresholdSize;
  public final int finalSize;
  public int speedOfGrowth;

  public Color strokeColor;
  public int strokeWeight;
  public boolean shouldGrow;

  List<Color> colors = new ArrayList<>();

  public Cell(int x, int y) {
    basic_color2();
    this.x = x;
    this.y = y;

    this.size = 1;
    this.thresholdSize = new Random().nextInt(size, 30) - strokeWeight;
    this.finalSize = new Random().nextInt(1, 4);
    this.speedOfGrowth = new Random().nextInt(1, 3);

    this.strokeColor = colors.get(new Random().nextInt(0, colors.size() - 1));
    this.strokeWeight = new Random().nextInt(2, 12);
    this.shouldGrow = true;
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


  public void updateSize() {
    if (shouldGrow) {
      this.size = size + speedOfGrowth;
      if (this.size >= this.thresholdSize) shouldGrow = false;
    } else if (this.size > finalSize) {
      this.size = size - speedOfGrowth;
    }
  }

  public void basic_color2() {
    colors = new ArrayList<>();
    colors.add(new Color(0x828960));
    colors.add(new Color(0x517250));
    colors.add(new Color(0x427072));
    colors.add(new Color(0x7C8048));
    colors.add(new Color(0x85C6CC));
    colors.add(new Color(0x70BEB7));
    colors.add(new Color(0x5A5947));
    colors.add(new Color(0x345A59));
    colors.add(new Color(0x4E6256));
    colors.add(new Color(0xCA2926));
    colors.add(new Color(0x8E6C41));
    colors.add(new Color(0xF9FCF2));
    colors.add(new Color(0xE7C241));
    colors.add(new Color(0x516D6C));
    colors.add(new Color(0xe9ecef));
    colors.add(new Color(0xdee2e6));
    colors.add(new Color(0xced4da));
    colors.add(new Color(0xadb5bd));
    colors.add(new Color(0x495057));
    colors.add(new Color(0x343a40));
    colors.add(new Color(0x212529));
    colors.add(new Color(0x599492));
    colors.add(new Color(0xE5AC69));
  }


}
