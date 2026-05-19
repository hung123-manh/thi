package UDP;
import java.io.*;
import java.net.*;
import java.util.*;

public class emailsdt {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("36.50.135.242");
        int serverPort = 2208;        
        String message = ";B22DCAT134;74IYm58K";
        DatagramPacket sendPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, serverPort);
        socket.send(sendPacket);        
        byte[] buffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(receivePacket);
        String received = new String (receivePacket.getData(),0,receivePacket.getLength());
        System.out.println(received);        
        String[] parts = received.split(";",2);
        String requestId = parts[0];
        String s = parts[1];
        s = s.replaceAll("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+", "[EMAIL]");
        s = s.replaceAll("\\d{10}", "[PHONE]");
        s = s.replaceAll("token=[^\\s]+", "token=[TOKEN]");
        String ans = s;                
         String result = requestId +";"+ans;
         System.out.println(result);
        DatagramPacket resultPacket = new DatagramPacket(result.getBytes(), result.length(), serverAddress, serverPort);
        socket.send(resultPacket);
        socket.close();
    }
}
