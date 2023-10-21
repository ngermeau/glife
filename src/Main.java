import processing.core.PApplet;
import java.util.HashSet;
import java.util.Set;

public class Main extends PApplet {

  Set<Cell> livingCells = new HashSet<>();

  public void loadGlide(){
    for (int i = 0; i < 4; i++) {
      livingCells.add(new Cell(i + 10, 10));
      livingCells.add(new Cell(i + 10, 18));
    }

    for (int i = 0; i < 8; i++) {
      livingCells.add(new Cell(i + 8, 12));
      livingCells.add(new Cell(i + 8, 16));
    }

    for (int i = 0; i < 12; i++) {
      livingCells.add(new Cell(i + 6, 14));
    }
  }

  public void loadShip(){
    livingCells.add(new Cell(3 + 8, 3 + 8));
    livingCells.add(new Cell(2 + 8, 3 + 8));
    livingCells.add(new Cell(4 + 8, 3 + 8));
    livingCells.add(new Cell(3 + 8, 4 + 8));
  }
  public void setup() {
    frameRate(1);
    loadGlide();
  }

  public void settings() {
    size(300, 300);
  }

  public void grid(){
    int rectSize = 10;
    for (int i = 0; i < 50; i++) {
      for (int j = 0; j < 50; j++) {
        if (livingCells.contains(new Cell(i,j))){
          fill(255,0,0);
        }else {
          fill(255);
        }
        rect(i * rectSize, j * rectSize, rectSize, rectSize);
      }
    }
  }
  public void draw() {
    grid();
    this.livingCells = tick(livingCells);
  }

  public Set<Cell> neighboursDeadCells(Set<Cell> livingCells) {
    Set<Cell> deadCells = new HashSet<>();
    for (Cell cell : livingCells) {
      deadCells.addAll(cell.deadNeighbours(livingCells));
    }
    return deadCells;
  }

  public Set<Cell> tick(Set<Cell> livingCells) {
    Set<Cell> newLivingCell = new HashSet<>();
    Set<Cell> deadCells = neighboursDeadCells(livingCells);

    for (Cell cell : livingCells) {
      int nbrOfAliveNeighboor = cell.aliveNeighbours(livingCells).size();
      if (nbrOfAliveNeighboor == 2 || nbrOfAliveNeighboor == 3) {
        newLivingCell.add(cell);
      }
    }

    for (Cell cell : deadCells) {
      int nbrOfAliveNeighboor = cell.aliveNeighbours(livingCells).size();
      if (nbrOfAliveNeighboor == 3) {
        newLivingCell.add(cell);
      }

    }
    return newLivingCell;
  }


  public static void main(String[] args) {
    PApplet.main("Main");
  }

}