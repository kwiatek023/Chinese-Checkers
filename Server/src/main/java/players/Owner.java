package players;

import java.net.Socket;

public class Owner extends Player {

  public Owner(Socket socket, String color) {
    super(socket, color);
  }

  @Override
  public void run() {

  }
}
