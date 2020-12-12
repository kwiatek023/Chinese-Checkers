package techprog;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

class ClientTest {
    @BeforeEach
    void setUp() throws IOException {
        var listener = new ServerSocket(3333);
        Socket socket = listener.accept();
        Scanner input = new Scanner(socket.getInputStream());
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        output.println("FIRST");
    }

    @Test
    void isFirst() {
        Client client = Client.getInstance();
        Assertions.assertTrue(client.isOwner());
    }
}