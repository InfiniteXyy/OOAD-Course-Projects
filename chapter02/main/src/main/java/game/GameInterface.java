package game;

public interface GameInterface {

  void drawCardForPlayer(Player player);

  Player getComputerPlayer();

  Player getWinner();

  int[] getDeltaMoney();
}
