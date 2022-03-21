import javax.imageio.ImageIO;

public class ShowImageIOInfo {
  static public void main(String args[]) throws Exception {
    String names[] = ImageIO.getReaderFormatNames();
    for (int i = 0; i < names.length; ++i) {
      System.out.println("reader " + names[i]);
    }

    names = ImageIO.getWriterFormatNames();
    for (int i = 0; i < names.length; ++i) {
      System.out.println("writer " + names[i]);
    }
  }
}
