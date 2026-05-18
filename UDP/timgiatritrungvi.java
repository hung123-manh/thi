package UDP;
import java.io.*;
import java.net.*;
import java.util.*;

public class C4oHHozk {
    public static void main (String []args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("36.50.135.242");
        int serverPort = 2207;        
        String message = ";B22DCAT134;C4oHHozk";
        DatagramPacket sendPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, serverPort);
        socket.send(sendPacket);        
        byte[] buffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(receivePacket);
        String received = new String(receivePacket.getData(),0,receivePacket.getLength());
        System.out.println(received);
        
        String[] parts = received.split(";");
        String requestId = parts[0];
        String[] arr = parts[1].split(",");
        ArrayList<Integer> a = new ArrayList<>();
        for (String x : arr){
            a.add(Integer.parseInt(x.trim()));
        }
        Collections.sort(a);
        double ans = 0;
        int n = a.size();
        if (n % 2 == 1){
            ans = a.get(n/2);
        }  else { 
            ans = (a.get(n/2 - 1) + a.get(n/2)) / 2.0;
        }        
        String result;
        
        if(ans == (int)ans){
            result = requestId + ";" + (int)ans;
        } else {
            result = requestId + ";" + String.format("%.2f", ans);
        }
        System.out.println(result);              
        DatagramPacket resultPacket = new DatagramPacket(result.getBytes(), result.length(), serverAddress, serverPort);
        socket.send(resultPacket);
        socket.close();    
    }
}


