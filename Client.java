import java.io.*;
import java.net.*;

public class Client{

    protected String serverIP;
    protected int port;
    protected String dataToSend;

    Client(String ip, int port, String fileName){
        this.serverIP = ip;
        this.port = port;
        this.dataToSend = fileName;
    }
    // Write the file
    // public void writeFile(){

    // }

    // Send the file name
    public void connect(){
        try(
        Socket socket = new Socket(serverIP, port);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
        //DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        
        ){
            System.out.print(dataToSend);
            out.print("hello");
            
            // byte[] buffer = new byte[1024];
            // int bytesRead = 0;
            // in.read(buffer, 0, 1024);
            // System.out.println(buffer);
            // while((bytesRead = in.read(buffer)) > 0){
            //     in.read(buffer, 0, 1024);
            //     System.out.println(buffer);
            // }
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



    public static void main(String args[]){
        Client client = new Client("127.0.0.1", 12345, args[0]);
        client.connect();
    }
}