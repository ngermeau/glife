import processing.core.PApplet;
import processing.core.PImage;

import java.util.HashSet;
import java.util.Set;

public class Main extends PApplet {

  int rectSize = 8;
  Set<Cell> livingCells = new HashSet<>();
  PImage colorRefImg;

  public void setup() {
    colorRefImg = loadImage("colorRefImage.jpg");
    colorRefImg.resize(1100,1100);
    frameRate(10);
    for (int i = 0; i < 200; i++) {
      ShapeLibrary.glide(round(random(0, (float) width /rectSize)),round(random(0,  (float) height /rectSize)), livingCells);
    }
  }

  public void settings() {
    size(800, 800);
  }

  public void grid(){
    for (int i = 0; i < width / rectSize; i++) {
      for (int j = 0; j < height / rectSize; j++) {
        if (livingCells.contains(new Cell(i,j))){
          int color = colorRefImg.get(i,j);
          fill(color);
          float size = map(brightness(color),0,255,0,rectSize-1);
          rect(i * rectSize, j * rectSize, size, size);

        }else {
          fill(22);
          rect(i * rectSize, j * rectSize, rectSize, rectSize);
        }
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