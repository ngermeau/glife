import processing.core.PApplet;
import processing.core.PImage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main extends PApplet {

  int rectSize = 40;
  Set<Cell> livingCells = new HashSet<>();
  PImage colorRefImg;
  SoundLibrary sl = new SoundLibrary();
  int soundColumn = 0;

  public void setup() {
    sl.loadSounds();
    colorRefImg = loadImage("colorRefImage.jpg");
    colorRefImg.resize(200,200);
    frameRate(1);
    for (int i = 0; i < 2; i++) {
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
          final int x = i;
          final int y = j;
          Cell cell = livingCells.stream().filter(livingCell -> livingCell.x == x && livingCell.y == y).findFirst().get();
          int color = colorRefImg.get(x,y);
          fill(color);
          if (cell.played) {
            fill(184, 15, 10);
          }
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
    playSound();
  }

  private void playSound() {
    System.out.println("column: " + soundColumn);
    List<Cell> sameColumLivingCells = livingCells.stream().filter(livingCell -> livingCell.x == soundColumn).toList();
    if (sameColumLivingCells.size() == 0) {
      soundColumn++;
      return;
    };
    if (soundColumn == width/rectSize) {
      soundColumn = 0;
    } else {
      soundColumn++;
    }
    Cell cell = sameColumLivingCells.get(round(random(0,sameColumLivingCells.size() - 1)));
    cell.played = true;
    System.out.println("cell : " + cell.x + "," + cell.y);
    sl.playSound(cell.y,this);
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