import processing.core.PApplet;

import java.awt.*;
import java.util.*;
import java.util.List;

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


  public void displayGrid(){
    List<Cell> lc = livingCells.stream().toList();
    for (int i = 0; i < lc.size()- 1; i++) {
        Cell cell = lc.get(i);
        cell.init();
        strokeWeight(cell.strokeWeight);
        stroke(cell.strokeColor.getRGB());
        noFill();
        cell.newSize();
        ellipse(cell.x * squareSize, cell.y * squareSize,cell.size,cell.size);
      }
    }
  public void draw() {
    background(backgroundColor.getRGB());
    displayGrid();
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
      int nbrOfAliveNeighboor = cell.aliveNeighbours(livingCells).size();
      if (nbrOfAliveNeighboor == 2 || nbrOfAliveNeighboor == 3) {
        cell.isDrawable = false;
        cell.played = false;
        newLivingCells.add(cell);
      }
    }

    for (Cell cell : deadCells) {
      int nbrOfAliveNeighboor = cell.aliveNeighbours(livingCells).size();
      if (nbrOfAliveNeighboor == 3) {
        cell.isDrawable = false;
        cell.played = false;
        newLivingCells.add(cell);
      }

    }
    this.livingCells = newLivingCells;
  }

  public static void main(String[] args) {
    PApplet.main("Main");
  }

}