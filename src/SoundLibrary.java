import processing.core.PApplet;
import processing.sound.SoundFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SoundLibrary {

  private static PApplet currentPApplet;
  private static List<String> listOfSounds = new ArrayList<>();
  private static String folderName = "sound";

  public static void play(int x) {
    SoundFile sound = new SoundFile(currentPApplet, listOfSounds.get(x));
    sound.play();
  }

  public static void initialize(PApplet pApplet) {
    currentPApplet = pApplet;
    try (Stream<Path> paths = Files.walk(Paths.get(folderName))) {
      listOfSounds = paths.filter(Files::isRegularFile).map(Path::toString).toList();
      System.out.println(listOfSounds);
    } catch (IOException ioException) {
      throw new RuntimeException();
    }
  }
}