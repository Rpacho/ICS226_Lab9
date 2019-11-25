import java.io.*;
import java.net.*;



public class Server {

    protected String HostIP;
    protected int port;
    protected String fileName;
    protected int BUFF_SIZE = 1024;

    /*
        This is the class constructor it needs the host ip and port
    */
    Server(String hostIP, int port){
        this.HostIP = hostIP;
        this.port = port;
    }


    /*
        This function open the server and accept any client that connects to it and wait for the filename data that the client will send.
        Then look at that file in the directory, if it exist then it will read that file by 1024 buffer then send it to the client

        No return
        
        Note: try and catch are provide so any error will be handled.
    */
    public void startServer(){
        try(
            ServerSocket serverSocket = new ServerSocket(port);
			Socket clientSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedOutputStream out = new BufferedOutputStream(clientSocket.getOutputStream(), 1024);
            
        ){
            String dataReceive;
            String fileName = "";
            dataReceive = in.readLine();
            fileName = dataReceive;

            // Try catch error if the file doesn't exist
            try(
                    BufferedInputStream file = new BufferedInputStream(new FileInputStream(fileName));
            ){

                byte[] buffer = new byte[BUFF_SIZE];
                int read = 0;
                while ((read = file.read(buffer)) > 0) {
                    System.out.println(read);   // To prove that it is sending by 1024
                    out.write(buffer, 0, read);
                }
                out.flush();

            }catch (FileNotFoundException e){
                System.err.println(e);
                System.exit(-2);
            }

        }catch(IOException e){
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





    public static void main(String args[]) {
        Server server1 = new Server("127.0.0.1", 12345);
        server1.startServer();

    }
}