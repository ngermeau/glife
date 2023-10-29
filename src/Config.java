import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Config {

  //Sketch
  public static int sketchWidth= 1000;
  public static int sketchHeight = 1000;
  public static int squareSize = 15;

  //Speed
  public static int cycleSpeed = 60;
  public static int frameRate = 50;

  //Init
  public static int startUpCells = 1000;

  //Grid
  public static Color backgroundColor = new Color(22);

  //Cell
  public static int initialSize = 1;
  public static int thresholdSizeMax = 20;
  public static int finalSizeMin = 1;
  public static int finalSizeMax = 4;
  public static int speedOfGrowthMin = 1;
  public static int speedOfGrowthMax = 3;
  public static int strokeWeightMin = 2;
  public static int strokeWeightMax = 12;
  public static List<Color> colors = new ArrayList<>();

  static {
    colors.add(new Color(0x828960));
    colors.add(new Color(0x517250));
    colors.add(new Color(0x427072));
    colors.add(new Color(0x7C8048));
    colors.add(new Color(0x85C6CC));
    colors.add(new Color(0x70BEB7));
    colors.add(new Color(0x5A5947));
    colors.add(new Color(0x345A59));
    colors.add(new Color(0x4E6256));
    colors.add(new Color(0xCA2926));
    colors.add(new Color(0x8E6C41));
    colors.add(new Color(0xF9FCF2));
    colors.add(new Color(0xE7C241));
    colors.add(new Color(0x516D6C));
    colors.add(new Color(0xe9ecef));
    colors.add(new Color(0xdee2e6));
    colors.add(new Color(0xced4da));
    colors.add(new Color(0xadb5bd));
    colors.add(new Color(0x495057));
    colors.add(new Color(0x343a40));
    colors.add(new Color(0x212529));
    colors.add(new Color(0x599492));
    colors.add(new Color(0xE5AC69));
  }

}
