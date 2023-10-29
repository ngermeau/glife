import processing.core.PApplet;

import java.util.*;

public class Main extends PApplet {

  Set<Cell> livingCells = new HashSet<>();
  public static int cycle= 0;


  public void settings() {
    size(Config.sketchWidth, Config.sketchHeight);
  }

  public void setup() {
    frameRate(Config.frameRate);
    for (int i = 0; i < Config.startUpCells; i++){
      int x = round(random(0,Config.sketchWidth/Config.squareSize));
      int y = round(random(0,Config.sketchHeight/Config.squareSize));
      this.livingCells.add(new Cell(x,y));
    }
  }

  private void displayCell(Cell cell) {
    strokeWeight(cell.strokeWeight);
    stroke(cell.strokeColor.getRGB());
    noFill();
    cell.updateSize();
    ellipse(cell.x * Config.squareSize, cell.y * Config.squareSize, cell.size, cell.size);
  }

  private void cycle(){
    cycle++;
    if (cycle % Config.cycleSpeed != 0) return;
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

  public static void main(String[] args) {
    PApplet.main("Main");
  }

}