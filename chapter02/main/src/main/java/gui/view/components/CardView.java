package gui.view.components;

import gui.view.Utils;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import javax.imageio.ImageIO;

public class CardView {

  private static HashMap<String, Image> cardImg = new HashMap<>();

  // dev
  private static final String[] TYPES = {"C", "D", "H", "S"};
  private static final String[] NUMBER = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J",
      "Q", "K"};

  private Rectangle cardBody;
  private int value;
  private Image img;

  public CardView(int gridX, int gridY, int value) {
    this.value = value;
    cardBody = new Rectangle(16 + gridX * 96, 16 + gridY * 200, 80, 120);
    String cardName = TYPES[0] + NUMBER[value];
    try {
      if (cardImg.containsKey(cardName)) {
        this.img = cardImg.get(cardName);
      } else {
        this.img = readPng(cardName);
        cardImg.put(cardName, img);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private Image readPng(String cardName) throws IOException {
    return ImageIO.read(new File("src/main/resources/deck/" + cardName + ".png"));
  }

  public void draw(Graphics2D g) {
    this.drawCard(g);
  }

  private void drawCard(Graphics2D g) {
    g.drawImage(img, cardBody.x, cardBody.y, cardBody.width, cardBody.height, null);
  }


}
