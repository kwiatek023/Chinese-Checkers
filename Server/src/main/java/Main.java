import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        var listener = new ServerSocket(3333);
        Socket socket = listener.accept();
        Scanner input = new Scanner(socket.getInputStream());
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
//        output.println("FIRST");
        output.println("SECOND");
//        System.out.println(input.nextLine());
//        System.out.println(input.nextLine());
        for(int i =0; i < 2000000; i++ ){int n=i+i;
            System.out.println(n);}
        output.println("START");
        while(true);
    }
}
