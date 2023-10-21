import java.util.HashSet;
import java.util.Set;

public class Main {


  public Set<Cell> neighboursDeadCells(Set<Cell> livingCells){
    Set<Cell> deadCells = new HashSet<>();
    for (Cell cell : livingCells) {
      deadCells.addAll(cell.deadNeighbours(livingCells));
    }
    return deadCells;
  }

  public Set<Cell> tick(Set<Cell> livingCells){
      Set<Cell> newLivingCell = new HashSet<>();
      Set<Cell> deadCells = neighboursDeadCells(livingCells);

      for (Cell cell : livingCells) {
        int nbrOfAliveNeighboor = cell.aliveNeighbours(livingCells).size();
        if (nbrOfAliveNeighboor == 2 || nbrOfAliveNeighboor == 3) {
          newLivingCell.add(cell);
        }
      }

      for (Cell cell : deadCells){
        int nbrOfAliveNeighboor = cell.aliveNeighbours(livingCells).size();
        if (nbrOfAliveNeighboor == 3) {
          newLivingCell.add(cell);
        }

      }
      return newLivingCell;
  }

  public static void main(String[] args) {
    System.out.println("Hello world!");
    Main main = new Main();
    Set<Cell> livingCell = new HashSet<>();
    livingCell.add(new Cell(3,3));
    livingCell.add(new Cell(2,3));
    livingCell.add(new Cell(4,3));
    for (int i = 0; i < 3; i++){
      System.out.println(livingCell);
      livingCell = main.tick(livingCell);
    }
  }

}