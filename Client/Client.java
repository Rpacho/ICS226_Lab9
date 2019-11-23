import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    protected String serverName;
    protected int serverPort;
    protected String message;

    public Client(String serverName, int serverPort, String message) {
        this.serverName = serverName;
        this.serverPort = serverPort;
        this.message = message;
    }

    public void connect() {
        try (Socket socket = new Socket(serverName, serverPort);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
                // File wrrite
                BufferedOutputStream fileWrite = new BufferedOutputStream(new FileOutputStream(message));
            ) {

            out.println(message);
            byte[] buffer = new byte[1024];
            int readData = 0;
            while ((readData = in.read(buffer)) > 0){
                // in.read(buffer, 0, readData);
                System.out.print(buffer);
                fileWrite.write(buffer, 0, readData);
            }
            fileWrite.flush();
            
            

        } catch (UnknownHostException e) {
            System.err.println(e);
            System.exit(-1);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(-2);
        } catch (SecurityException e) {
            System.err.println(e);
            System.exit(-3);
        } catch (IllegalArgumentException e) {
            System.err.println(e);
            System.exit(-4);
        }
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 12345, args[0]);
        client.connect();
    }
}
