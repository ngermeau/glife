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

  public void print(Set<Cell> livingCells){
      int[][] board = new int[20][20];

      for(Cell cell : livingCells){
        board[cell.x][cell.y] = 1;
      }

    for (int i = 0; i < 20; i++) {
      System.out.println();
      for (int j = 0; j < 20; j++) {
        System.out.print(board[i][j]);
      }
    }
    System.out.println();
    System.out.println();
    System.out.print("new state");
  }
  public static void main(String[] args) {
    Main main = new Main();
    Set<Cell> livingCells = new HashSet<>();
    livingCells.add(new Cell(3 +  8,3 + 8));
    livingCells.add(new Cell(2 +8,3 + 8));
    livingCells.add(new Cell(4 + 8,3 + 8));
    livingCells.add(new Cell(3 + 8,4 + 8));
    for (int i = 0; i < 10; i++){
      main.print(livingCells);
      livingCells = main.tick(livingCells);
    }
  }

}