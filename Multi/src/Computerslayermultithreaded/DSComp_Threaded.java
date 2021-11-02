package Computerslayermultithreaded;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class ClientHandler implements Runnable
{

    Socket s;
    Socket s1;
    

    public ClientHandler(Socket s,Socket s1)
    {
        this.s = s;
        this.s1 = s1;
    }

    @Override
    public void run()
    {
        try
        {
            //3.create I/O streams
    //. Create I/O streams as client to the server
            DataInputStream dis1 = new DataInputStream(s1.getInputStream());
            DataOutputStream dos1 = new DataOutputStream(s1.getOutputStream());
             //create socket and connect as server to the client
            //and with portnumber: 1230
           
             //.create I/O with cumputing
             DataInputStream dis = new DataInputStream(s.getInputStream());
             DataOutputStream dos = new DataOutputStream(s.getOutputStream());   
            while (true)
            {
            //get location ques from server 
             String srvr_msg = dis1.readUTF();  
              //send location ques to client
              dos.writeUTF(srvr_msg);
              dos.flush();
            //get location answer from client
             String location = dis.readUTF();
             dos1.writeUTF(location);
             dos1.flush();
             
            //get destination ques from server 
            String srvr_msg1 = dis1.readUTF();  
            //send destination ques to client
             dos.writeUTF(srvr_msg1);
             dos.flush();
            //get destination answer from client
             String destination = dis.readUTF();
            dos1.writeUTF(destination);
            dos1.flush();
            //collecting sensors reading 
             dos1.writeUTF("Sensors reading");
            //get continue ques from server 
            String srvr_msg2 = dis1.readUTF();  
            //send  ques to client
             dos.writeUTF(srvr_msg2);
             dos.flush();
             //get answer from client
              String cont = dis.readUTF();
            dos1.writeUTF(cont);
            dos1.flush();    
            dos.flush();
            if (cont.equals("n"))
                {
                    dos.writeUTF("bye");
                    dos.flush();
                    break;
                }
            }
            dis1.close();
            dos1.close();
           
            dis.close();
            dos.close();
            s.close();
           s1.close();
        }
          catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}

public class DSComp_Threaded
{

    public static void main(String[] args)
    {
        try
        {
            //1.open server socket
            ServerSocket sv = new ServerSocket(1230);
             Socket s1 = new Socket("127.0.0.1", 1234);
            //. Create I/O streams as client to the server
          //  DataInputStream dis1 = new DataInputStream(s1.getInputStream());
            //DataOutputStream dos1 = new DataOutputStream(s1.getOutputStream());
            System.out.println("Server Running...");
            while (true)
            {
                //2.accept connection
                Socket s = sv.accept();
                System.out.println("Client Accepted...");
                //3. open thread for this client (s)
                ClientHandler ch = new ClientHandler(s,s1);
                Thread t = new Thread(ch);
                t.start();

            }

            //6.close server
            
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

}
