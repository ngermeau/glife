import processing.core.PApplet;
import processing.core.PImage;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Main extends PApplet {

  Set<Cell> livingCells = new HashSet<>();
  int sketchWidth= 800;
  int sketchHeight = 800;
  int squareSize = 7;
  int boardWidth = sketchWidth / squareSize;
  int boardHeight= sketchHeight/ squareSize;
  int frameRate = 200;
  Color backgroundColor = new Color(0xf8f9fa);
  List<Color> colors = new ArrayList<>();

  String colorsString = "03045e,023e8a,0077b6,0096c7,00b4d8,48cae4,90e0ef,ade8f4,caf0f8";


  public void setup() {
    SoundLibrary.initialize(this);
    for (int i = 0; i < 100; i++){
      int x = round(random(0,100));
      int y = round(random(0,100));
      this.livingCells.addAll(ShapeLibrary.glide(x,y));
    }
    frameRate(frameRate);
    colors.add(new Color(0xe9ecef));
    colors.add(new Color(0xdee2e6));
    colors.add(new Color(0xced4da));
    colors.add(new Color(0xadb5bd));
    colors.add(new Color(0x495057));
    colors.add(new Color(0x343a40));
    colors.add(new Color(0x212529));
  }

  public void settings() {
    size(sketchWidth, sketchHeight);
  }

  public void displayGrid(){
    for (int x = 0; x < width / squareSize; x++) {
      for (int y = 0; y < height / squareSize; y++) {
        final int i = x, j = y;
        Optional<Cell> cell = livingCells.stream().filter(lc-> lc.equals(new Cell(i,j))).findFirst();
        if (cell.isPresent()){
          Color color = colors.get(round(random(0, colors.size()-1)));
          int randomSize = round(random(0,8));
          stroke(color.getRGB());
          strokeWeight(random(1,10));
          noFill();
          ellipse(x * squareSize, y * squareSize,randomSize,randomSize);
//          arc(x * squareSize, y * squareSize, randomSize, randomSize, 0, PI+QUARTER_PI, CHORD);
        }else {
          fill( this.backgroundColor.getRGB());
          noStroke();
          rect(x * squareSize, y * squareSize, squareSize, squareSize);
        }
      }
    }
  }
  public void draw() {
    displayGrid();
    calculateNextTickLivingCells();
//    playSound();
  }

  private void playSound() {
    Optional<Cell> randomCell = livingCells.stream().filter(cell -> cell.x >= 0 && cell.x < boardWidth && cell.y >=0 && cell.y <= boardHeight).findAny();
    if (randomCell.isPresent()){
        randomCell.get().playSound();
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
        newLivingCells.add(cell);
      }
    }

    for (Cell cell : deadCells) {
      int nbrOfAliveNeighboor = cell.aliveNeighbours(livingCells).size();
      if (nbrOfAliveNeighboor == 3) {
        newLivingCells.add(cell);
      }

    }
    this.livingCells = newLivingCells;
  }


  public static void main(String[] args) {
    PApplet.main("Main");
  }

}