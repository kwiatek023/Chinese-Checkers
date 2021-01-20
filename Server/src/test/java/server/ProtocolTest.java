package server;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class ProtocolTest {
  ServerSocket serverSocket;
  Socket socket;
  Protocol protocol;

  @BeforeEach
  public void prepareProtocol() throws IOException {
    serverSocket = new ServerSocket(3333);
    socket = new Socket("localhost", 3333);
    PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
    protocol = new Protocol(output);
  }

  @Test
  public void shouldSendMessages() {
    protocol.welcome("GREEN", 3, "YELLOW");
    protocol.sendHandshake("OWNER");
    protocol.validMoveMsg(1, 1, 2, 2);
    protocol.pawnMoved(1, 1, 2, 2);
    protocol.nextTurn("GREEN");
    protocol.hasFinished("GREEN");
    protocol.rageQuit("GREEN");
    protocol.endGame();
  }

  @AfterEach
  public void cleanUp() throws IOException {
    socket.close();
    serverSocket.close();
  }
}