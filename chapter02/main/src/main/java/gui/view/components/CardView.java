package gui.view.components;

import game.Card;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import javax.imageio.ImageIO;

public class CardView {

  private static HashMap<String, Image> cardImg = new HashMap<>();
  private Card card;
  private Rectangle cardBody;
  private Image img;

  public CardView(int gridX, Card card) {
    this.card = card;
    int gridY = gridX / 6;
    gridX = gridX % 6;
    cardBody = new Rectangle(16 + gridX * 96, 160 + gridY * 160, 72, 110);
    try {
      if (cardImg.containsKey(card.getResourceName())) {
        this.img = cardImg.get(card.getResourceName());
      } else {
        this.img = readPng(card.getResourceName());
        cardImg.put(card.getResourceName(), img);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private Image readPng(String cardName) throws IOException {
    return ImageIO.read(Objects
        .requireNonNull(getClass().getClassLoader().getResource("deck/" + cardName + ".png")));
  }

  public void draw(Graphics2D g) {
    this.drawCard(g);
  }

  private void drawCard(Graphics2D g) {
    g.drawImage(img, cardBody.x, cardBody.y, cardBody.width, cardBody.height, null);
  }

}
