import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    protected String serverName;
    protected int serverPort;
    protected String message;
    protected int BUFF_SIZE = 1024;
    /*
    This is the class constructor that accepts server ip, port, and the message (in this case file name)
    and set it to this class variable.

    */
    public Client(String serverName, int serverPort, String message) {
        this.serverName = serverName;
        this.serverPort = serverPort;
        this.message = message;
    }

    /*

        This function will try to connect to the server and sends the data we receive from the users
        then wait for the file that the server will sent. If the file data received it will write that file 
        and create using that binary data it recieved

        No param

        No return

        Note: Assuming the ip, port, and the file is valid then it will work.

    */

    public void connect() {
        try (Socket socket = new Socket(serverName, serverPort);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
                // File write
                BufferedOutputStream fileWrite = new BufferedOutputStream(new FileOutputStream(message));
            ) {

            out.println(message);
            byte[] buffer = new byte[BUFF_SIZE]; // This is the 1024 buffer
            int readData = 0;
            while ((readData = in.read(buffer)) > 0){   // While theres a data left then keep writing.
                System.out.println(readData);
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


    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 12345, args[0]);
        client.connect();
    }
}
