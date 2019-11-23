import java.io.*;
import java.net.*;



public class Server {

    protected String HostIP;
    protected int port;
    protected String fileName;

    Server(String hostIP, int port){
        this.HostIP = hostIP;
        this.port = port;
    }

    public void startServer(){
        try(
            ServerSocket serverSocket = new ServerSocket(port);
			Socket clientSocket = serverSocket.accept();
			// PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedOutputStream out = new BufferedOutputStream(clientSocket.getOutputStream(), 1024);
            
        ){
            String dataReceive;
            String fileName = "";
            dataReceive = in.readLine();
            // System.out.println("Inside loop" + dataReceive);
            fileName = dataReceive;
            // System.out.println(fileName);


            BufferedInputStream file = new BufferedInputStream(new FileInputStream(fileName));
            // System.out.print(file.read());

            byte[] buffer = new byte[1024];
            int read = 0;
            while((read = file.read(buffer)) > 0){
                // file.read(buffer, 0, read);
                System.out.print(buffer);
                out.write(buffer, 0, read);
            }
            out.flush();
            




            // 1024 buffer byte array this is where binary will be store




            // int bytesRead = 0;
            // while((bytesRead = file.read(buffer)) != -1){
            //     System.out.print("Tasdasdas");
            //     file.read(buffer, 0, bytesRead);

            //     out.write(buffer);
            //     System.out.println(buffer);
            // }
            


            //FileInputStream file = new FileInputStream(fileName);
            // BufferedInputStream file = new BufferedInputStream(new FileInputStream(fileName));
            // BufferedOutputStream write = new BufferedOutputStream(new FileOutputStream("test2.txt"));
            // write.write(file.read(1024));
            // write.flush();

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
        // try(
        // BufferedInputStream file = new BufferedInputStream(new FileInputStream("test.txt"));
        // ){
        // // BufferedOutputStream write = new BufferedOutputStream(new FileOutputStream("test2.txt"));
        // // write.write(file.read(1024));
        // // write.flush();
        // BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("test2.txt"));
        // // System.out.println(file.read());
        //     byte[] buffer = new byte[1024];

        //     int bytesRead = 0;
        //     while((bytesRead = file.read(buffer)) != -1){
        //         file.read(buffer, 0, bytesRead);
        //         //System.out.println(buffer);
        //         out.write(buffer);
                
        //     }
        //     out.flush();
            
            
            // out.close();
            // System.out.println(file.read(buffer));
            // while ((bytesRead = file.read(buffer)) != -1) {
            //     System.out.println(new String(buffer, 0, bytesRead));
            // }
    }
}