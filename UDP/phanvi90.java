package UDP;
import java.io.*;
import java.net.*;
import java.util.*;

public class phanvi {
    public static void main(String []args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("36.50.135.242");
        int serverPort = 2207;        
        String message = ";B22DCAT134;1qwpuOTg";
        DatagramPacket sendPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, serverPort);
        socket.send(sendPacket);        
        byte[] buffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buffer,buffer.length);
        socket.receive(receivePacket);
        String received = new String(receivePacket.getData(),0,receivePacket.getLength());
        System.out.println(received);        
        String[] parts = received.split(";");
        String requestId = parts[0];
        String[] arr = parts[1].split(",");
        ArrayList<Integer> a = new ArrayList<>();
        for (String x : arr) {
            a.add(Integer.parseInt(x.trim()));
        }
        double avg = 0;
        for (int x : a) {
            avg += x;
        }
        avg /= a.size();
        Collections.sort(a);
        int idx = (int)Math.ceil(a.size()*0.9) - 1;
        int p90 = a.get(idx);
        int cnt = 0;
        for (int x : a) {
            if (x > avg){
                cnt++;
            }
        }
        String result = requestId + ";p90=" + p90 + ";aboveAvg=" +cnt;
        System.out.println(result); 
        DatagramPacket resultPacket = new DatagramPacket(result.getBytes(), result.length(), serverAddress, serverPort);
        socket.send(resultPacket);
        socket.close();
    }
}

