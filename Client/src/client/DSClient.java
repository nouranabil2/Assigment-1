package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class DSClient
{

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        try
        {
            //1.create socket and connect to the server
            //with IP:127.0.0.1(localhost)
            //and with portnumber: 1230
            Socket s = new Socket("127.0.0.1", 1230);
            //2. Create I/O streams
            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                   while (true)
            {
                //a. receive server command & print to user
                String srvr_msg = dis.readUTF();                
                if(srvr_msg.equals("bye"))
                {
                    System.out.println("Thank you");
                    break;
                }
                System.out.println(srvr_msg);
                //b. take command from usr and send to the server
                String usr_msg = sc.next();
                dos.writeUTF(usr_msg);
                dos.flush();
            }
            
            //close connections
            dis.close();
            dos.close();
            s.close();
            
        } 
        catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
