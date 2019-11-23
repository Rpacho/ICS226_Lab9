import java.net.*;
import java.io.*;
import java.nio.channels.*;
import java.util.zip.*;

public class Server2 {

    protected final String HOST = "127.0.0.1";
    protected int PORT;

    Server2(int port) {
        this.PORT = port;
    }

    public void startServer() {
        System.out.println("Server started.. with port " + PORT);
        try (ServerSocket serverSocket = new ServerSocket(PORT);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                // FileWriter writeFile = new FileWriter("Transcript.txt");
                // PrintWriter printWrite = new PrintWriter(new FileOutputStream("Transcript2.txt"));
                ) {
            while (true) {
                String inputLine = in.readLine();
                if (inputLine == null) {
                    break;
                }
                System.out.println(inputLine);

                // out.println(inputLine);
            }
        } catch (IOException e) {
            System.err.println(e);
            System.exit(-2);
        } catch (SecurityException e) {
            System.err.println(e);
            System.exit(-3);
        } catch (IllegalArgumentException e) {
            System.err.println(e);
            System.exit(-4);
        } catch (IllegalBlockingModeException e) {
            System.err.println(e);
            System.exit(-6);
        }
    }

    public static void main(String[] args){
    Server2 server = new Server2(12345);
    server.startServer();
    }
}