import processing.core.PApplet;

import java.awt.*;
import java.util.*;

public class Main extends PApplet {

  Set<Cell> livingCells = new HashSet<>();

  int sketchWidth= 1000;
  int sketchHeight = 1000;
  int squareSize = 15;

  int cycle= 0;
  int cycleSpeed = 60;
  int frameRate = 50;

  int startUpCells = 1000;
  Color backgroundColor = new Color(22);


//  String colorsString = "03045e,023e8a,0077b6,0096c7,00b4d8,48cae4,90e0ef,ade8f4,caf0f8";

  public void settings() {
    size(sketchWidth, sketchHeight);
  }

  public void setup() {
    frameRate(frameRate);
    for (int i = 0; i < startUpCells; i++){
      int x = round(random(0,sketchWidth/squareSize));
      int y = round(random(0,sketchHeight/squareSize));
      this.livingCells.add(new Cell(x,y));
    }
  }

  private void displayCell(Cell cell) {
    strokeWeight(cell.strokeWeight);
    stroke(cell.strokeColor.getRGB());
    noFill();
    cell.updateSize();
    ellipse(cell.x * squareSize, cell.y * squareSize, cell.size, cell.size);
  }

  public void draw() {
    background(backgroundColor.getRGB());
    livingCells.forEach(this::displayCell);
    cycle++;
    if (cycle % cycleSpeed == 0) {
      cycle = 0;
      calculateNextTickLivingCells();
    }
  }

  public Set<Cell> neighboursDeadCells(Set<Cell> livingCells) {
    Set<Cell> deadCells = new HashSet<>();
    for (Cell cell : livingCells) {
      deadCells.addAll(cell.deadNeighbours(livingCells));
    }
    return deadCells;
  }

  public void calculateNextTickLivingCells() {
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