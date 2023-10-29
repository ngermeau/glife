import java.awt.*;
import java.util.*;
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

  public static int Random(int min, int max){
    return new Random().nextInt(min,max);
  }
  public Cell(int x, int y) {
    this.x = x;
    this.y = y;

    this.size = Config.initialSize;
    this.shouldGrow = true;

    this.thresholdSize = Random(size, Config.thresholdSizeMax);
    this.finalSize = Random(Config.finalSizeMin,Config.finalSizeMax);
    this.speedOfGrowth = Random(Config.speedOfGrowthMin,Config.speedOfGrowthMax);

    this.strokeColor = Config.colors.get(Random(0,Config.colors.size()-1));
    this.strokeWeight = Random(Config.strokeWeightMin, Config.strokeWeightMax);
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

}
