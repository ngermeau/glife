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

  private List<Path> sounds = new ArrayList<>();
  public void playSound(int id, PApplet applet){
    System.out.println(sounds.size());
      SoundFile sound = new SoundFile(applet,sounds.get(id).toString());
      sound.play();
  }

  public void loadSounds() {
    // prend le contenu du folder on s'en fout des noms et le tape dans un array
    try (Stream<Path> paths = Files.walk(Paths.get("sound"))) {
      sounds = paths.filter(Files::isRegularFile).toList();
      System.out.println(sounds.size());
    }catch (IOException ioException){
      throw new RuntimeException();
    }
  }
}
