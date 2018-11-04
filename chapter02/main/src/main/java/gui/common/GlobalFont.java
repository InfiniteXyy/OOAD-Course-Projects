package gui.common;

import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class GlobalFont {

  private static Font h1 = new Font("PingFang SC", Font.BOLD, 36);
  private static Font h2 = new Font("PingFang SC", Font.BOLD, 30);
  private static Font h3 = new Font("PingFang SC", Font.BOLD, 20);
  private static Font h4 = new Font("PingFang SC", Font.PLAIN, 16);

  public static Font H1() {
    return h1;
  }

  public static Font H2() {
    return h2;
  }

  public static Font get(String variant) {
    switch (variant) {
      case "h1":
        return h1;
      case "h2":
        return h2;
      case "h3":
        return h3;
      case "h4":
        return h4;
      default:
        return h3;
    }
  }

  public static void main(String[] args) {
    String fonts[] =
        GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

    for (int i = 0; i < fonts.length; i++) {
      System.out.println(fonts[i]);
    }
  }

}