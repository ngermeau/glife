import java.awt.*;
import java.util.*;
import java.util.List;

import static java.lang.Math.random;
import static java.lang.Math.round;

public class Cell {

  public int x;
  public int y;
  public boolean played;
  public boolean isDrawable;
  public Color strokeColor;
  public int size;
  public int strokeWeight;
  public int speed;
  public boolean growing;
  public int sizeThreshold;

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

  public void newSize(){
    if (growing) {
      this.size = size + speed;
      if (this.size > this.sizeThreshold){
        growing = false;
      }
    }else {
      this.size = size - speed;
      if (this.size < 0) {
        growing = true;
      }
    }
  }
  public void init(){

    List<Color> colors = new ArrayList<>();
    colors.add(new Color(0xe9ecef));
    colors.add(new Color(0xdee2e6));
    colors.add(new Color(0xced4da));
    colors.add(new Color(0xadb5bd));
    colors.add(new Color(0x495057));
    colors.add(new Color(0x343a40));
    colors.add(new Color(0x212529));
    if (!isDrawable){
      int randomColor = new Random().nextInt(0,colors.size() - 1);
      this.strokeColor = colors.get(randomColor);
      this.size = new Random() .nextInt(1,8);
      this.strokeWeight= new Random().nextInt(2,10);
      this.isDrawable = true;
      this.speed = new Random().nextInt(1,4);
      this.growing = true;
      this.sizeThreshold= new Random().nextInt(size,20);
    }
  }
  public boolean isPlayed() {
    return played;
  }
}
