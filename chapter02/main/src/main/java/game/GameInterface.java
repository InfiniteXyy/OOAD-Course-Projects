package game;

public interface GameInterface {

  // 用户操作
  void drawCardForPlayer(Player player);

  void stopDrawingForPlayer(Player player);

  Player getCurrentPlayer();

  boolean nextPlayer();

  Player getComputerPlayer();

  // 游戏功能操作
  boolean judgeGame();

  boolean isGaming();
}
