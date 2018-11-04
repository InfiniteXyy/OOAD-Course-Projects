package game;

public interface GameInterface {

  void drawCardForPlayer(Player player);

  Player getComputerPlayer();

  int getWinner();

  int[] getDeltaMoney();
}
