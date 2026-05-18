package UDP;
import java.io.*;
import java.net.*;
import java.util.*;

public class fuvL7DJZ {
    public static void main(String []args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("36.50.135.242");             
        int serverPort = 2207;        
        String message = ";B22DCAT134;fuvL7DJZ";
        DatagramPacket sendPacket = new DatagramPacket(message.getBytes(), message.length(),serverAddress, serverPort);
        socket.send(sendPacket);        
        byte[] buffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(receivePacket);
        String received = new String (receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println(received);        
        String[] parts = received.split(";");
        String requestId = parts[0];
        String[] arr = parts[1].split(",");
        ArrayList<Integer> a = new ArrayList<>();
        for (String x : arr) {
            a.add(Integer.parseInt(x.trim()));
        }
        long ans = 0;
        for (int i = 0; i < a.size(); i++){
            ans += (long)(i + 1) * a.get(i);
            ans %= 100000;
        }        
        String result = requestId +";"+ans;
        System.out.println("result");      
        DatagramPacket resultPacket = new DatagramPacket(result.getBytes(), result.length(), serverAddress, serverPort);
        socket.send(resultPacket);
        socket.close();       
    }
}

