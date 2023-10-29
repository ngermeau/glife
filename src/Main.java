import processing.core.PApplet;
import processing.event.MouseEvent;

import java.util.*;

public class Main extends PApplet {

  Set<Cell> livingCells = new HashSet<>();
  public int cycle= 0;
  public int cycleSpeed = 60;


  public void settings() {
    size(Config.sketchWidth, Config.sketchHeight);
  }

  public void setup() {
    frameRate(Config.frameRate);
    for (int i = 0; i < Config.startUpCells; i++){
      int x = round(random(0,Config.sketchWidth/Config.cellSize));
      int y = round(random(0,Config.sketchHeight/Config.cellSize));
      this.livingCells.add(new Cell(x,y));
    }
  }

  private void displayCell(Cell cell) {
    strokeWeight(cell.strokeWeight);
    stroke(cell.strokeColor.getRGB());
    noFill();
    cell.updateSize();
    ellipse(cell.x * Config.cellSize, cell.y * Config.cellSize, cell.size, cell.size);
  }

  private void cycle(){
    cycle++;
    if (cycleSpeed != 0 && cycle % cycleSpeed != 0) return;
    cycle = 0;
    calculateNextLivingCells();
  }

  public void draw() {
    background(Config.backgroundColor.getRGB());
    livingCells.forEach(this::displayCell);
    cycle();
  }

  public Set<Cell> neighboursDeadCells(Set<Cell> livingCells) {
    Set<Cell> deadCells = new HashSet<>();
    for (Cell cell : livingCells) {
      deadCells.addAll(cell.deadNeighbours(livingCells));
    }
    return deadCells;
  }

  public void calculateNextLivingCells() {
    Set<Cell> newLivingCells = new HashSet<>();
    Set<Cell> deadCells = neighboursDeadCells(livingCells);

    for (Cell cell : livingCells) {
      int nbrOfAliveNeighbours = cell.aliveNeighbours(livingCells).size();
      if (nbrOfAliveNeighbours == 2 || nbrOfAliveNeighbours == 3) {
        newLivingCells.add(new Cell(cell.x,cell.y));
      }
    }

    for (Cell cell : deadCells) {
      int nbrOfAliveNeighbours = cell.aliveNeighbours(livingCells).size();
      if (nbrOfAliveNeighbours == 3) {
        newLivingCells.add(new Cell(cell.x,cell.y));
      }

    }
    this.livingCells = newLivingCells;
  }

  public void mouseDragged(){
    int x = round(mouseX/Config.cellSize);
    int y = round(mouseY/Config.cellSize);
    livingCells.add(new Cell(x,y));
  }

  public void mouseWheel(MouseEvent event) {
    cycleSpeed -= round(event.getCount());
    System.out.println(cycleSpeed);
  }

  public static void main(String[] args) {
    PApplet.main("Main");
  }

}