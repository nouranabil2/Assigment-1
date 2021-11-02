package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class DSServer
{

    public static void main(String[] args)
    {
        try
        {
           
            //1.open server socket
            ServerSocket sv = new ServerSocket(1234);
            System.out.println("Server Running...");
            
                //2.accept connection
                Socket s = sv.accept();
                System.out.println("Client Accepted...");
                //3.create I/O with cumputing
                 DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
             while (true)
            {
                dos.writeUTF("Please enter your current location");
                dos.flush();
                String location = dis.readUTF();
                System.out.println("client says his current location is :"+location);
                dos.writeUTF("Please enter your destination location");
                dos.flush();
                String destination = dis.readUTF();
                System.out.println("client says his destination is :"+destination);
                
                String sensors = dis.readUTF();
                System.out.println(sensors );
                dos.writeUTF("Recommendation done Successfully ,Try again[y/n]?");
                dos.flush();
                
                  String usr_choice = dis.readUTF();
                //apply checks
                if (usr_choice.equals("n"))
                {
                    dos.writeUTF("bye");
                    dos.flush();
                    break;
                }
            }
              // dos.writeUTF("server says :"+location);
               
            

            //6.close server
             dis.close();
            dos.close();
            s.close();
            sv.close();
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

}
