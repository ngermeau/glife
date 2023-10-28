import processing.core.PApplet;

import java.awt.*;
import java.util.*;

public class Main extends PApplet {

  Set<Cell> livingCells = new HashSet<>();

  int sketchWidth= 800;
  int sketchHeight = 800;
  int squareSize = 15;
  int boardWidth = sketchWidth / squareSize;
  int boardHeight= sketchHeight/ squareSize;
  int frameRate = 120;
  Color backgroundColor = new Color(0xf8f9fa);
//  backgroundColor = new Color(0x);
  Color playedColor = new Color(0xd62828);
  int cycle= 0;

//  String colorsString = "03045e,023e8a,0077b6,0096c7,00b4d8,48cae4,90e0ef,ade8f4,caf0f8";


  public void setup() {
    SoundLibrary.initialize(this);
    for (int i = 0; i < 10; i++){
      int x = round(random(0,100));
      int y = round(random(0,100));
      this.livingCells.addAll(ShapeLibrary.glide(x,y));
    }
    for (int i = 0; i < 100; i++){
      int x = round(random(0,100));
      int y = round(random(0,100));
      this.livingCells.addAll(ShapeLibrary.ship(x,y));
    }
    frameRate(frameRate);
  }

  public void settings() {
    size(sketchWidth, sketchHeight);
  }

  public void displayGrid(){
    for (int x = 0; x < width / squareSize; x++) {
      for (int y = 0; y < height / squareSize; y++) {
        final int i = x, j = y;
        Optional<Cell> optCell = livingCells.stream().filter(lc-> lc.equals(new Cell(i,j))).findFirst();
        if (optCell.isPresent()){
          Cell cell = optCell.get();
          cell.init();
          strokeWeight(cell.strokeWeight);
          stroke(cell.strokeColor.getRGB());
          noFill();
          cell.newSize();
          ellipse(x * squareSize, y * squareSize,cell.size,cell.size);
        }
      }
    }
  }
  public void draw() {
//    background(backgroundColor.getRGB());
    background(22);
    displayGrid();
    cycle++;
    System.out.println(cycle);
    if (cycle % 10 == 0) {
      cycle = 0;
      calculateNextTickLivingCells();
      playSound();
    }
  }

  private void playSound() {
    Optional<Cell> randomCell = livingCells.stream().filter(cell -> cell.x >= 0 && cell.x < boardWidth && cell.y >=0 && cell.y <= boardHeight).findAny();
    if (randomCell.isPresent()){
        randomCell.get().played = true;
//        randomCell.get().playSound();
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
        newLivingCells.add(cell);
      }
    }

    for (Cell cell : deadCells) {
      int nbrOfAliveNeighboor = cell.aliveNeighbours(livingCells).size();
      if (nbrOfAliveNeighboor == 3) {
        cell.isDrawable = false;
        newLivingCells.add(cell);
      }

    }
    this.livingCells = newLivingCells;
  }
 // start growing phase of next tick when the other is in descending phase

  public static void main(String[] args) {
    PApplet.main("Main");
  }

}