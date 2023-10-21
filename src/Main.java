import java.util.ArrayList;
import java.util.List;

public class Main {

  private int boardSize = 10;
  private List<Cell> livingCells = new ArrayList<>();

  public void initLivingCells(){
    livingCells.add(new Cell(5,5));
    livingCells.add(new Cell(7,7));
  }

  private void printLivingCells() {
    System.out.println(livingCells);
  }

  public List<Cell> neighbours(Cell cell){
    List<Cell> neighbours = new ArrayList<>();
    for(int i = -1; i <= 1; i++){
      for (int j = -1; j <= 1; j++) {
        if (i == 0 && j == 0) continue;
        neighbours.add(new Cell(cell.x + i, cell.y + j));
      }
    }
    return neighbours;
  }
  public void calculate(Cell cell){

  }
  public static void main(String[] args) {
    System.out.println("Hello world!");
    Main main = new Main();
    main.initLivingCells();
    new Cell(3,3);
    List<Cell> neighbours = main.neighbours(new Cell(3,3));
    System.out.println(neighbours);
//    main.printLivingCells();
  }

}