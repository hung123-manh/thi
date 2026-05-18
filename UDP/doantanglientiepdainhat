package UDP;
import java.io.*;
import java.net.*;
import java.util.*;

public class Kr1svMb5 {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("36.50.135.242");
        int serverPort = 2207;
        String message = ";B22DCAT134;Kr1svMb5";
        DatagramPacket sendPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, serverPort);
        socket.send(sendPacket);
        byte[] buffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(receivePacket);
        String received = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println(received);
        String[] parts = received.split(";");
        String requestId = parts[0];
        String[] arr = parts[1].split(",");
        ArrayList<Integer> a = new ArrayList<>();
        for(String x : arr){
            a.add(Integer.parseInt(x.trim()));
        }
        ArrayList<Integer> best = new ArrayList<>();
        for(int i = 0; i < a.size(); i++){
            ArrayList<Integer> cur = new ArrayList<>();
            cur.add(a.get(i));
            for(int j = i + 1; j < a.size(); j++){
                if(a.get(j) > a.get(j - 1)){
                    cur.add(a.get(j));
                }
                else{
                    break;
                }
            }
            if(cur.size() > best.size()){
                best = cur;
            }
        }
        int sum = 0;
        for(int x : best){
            sum += x;
        }
        String segment = "";
        for(int i = 0; i < best.size(); i++){
            segment += best.get(i);
            if(i != best.size() - 1){
                segment += ",";
            }
        }
        String result = requestId + ";segment=" + segment + ";length=" + best.size() + ";sum=" + sum;
        System.out.println(result);
        DatagramPacket resultPacket = new DatagramPacket(result.getBytes(), result.length(), serverAddress, serverPort);
        socket.send(resultPacket);
        socket.close();
    }
}
