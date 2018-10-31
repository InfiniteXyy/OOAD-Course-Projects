package game;

public class ComputerPlayer extends Player {

  @Override
  public String getCardInfo() {
    return ("庄家的第一张牌是" + this.getCards().get(0));
  }

  @Override
  public void refreshDrawingState() {
    this.setDrawing(getCardSum() <= 17);
  }
}
