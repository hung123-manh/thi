package UDP;

import java.net.*;
import java.util.*;
import java.io.*;

public class CI33vBiV{
    public static void main (String []args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("36.50.135.242");
        int serverPort = 2208;

        String message = ";B22DCAT134;CI33vBiV";
        DatagramPacket sendPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, serverPort);
        socket.send(sendPacket);

        byte []buffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(receivePacket);

        String received = new String(receivePacket.getData(),0, receivePacket.getLength());
        System.out.println(received);

        String[] parts = received.split(";");
        String requestId = parts[0];
        String query = parts[1];

        String[] pairs = query.split("&");

        TreeMap<String,String> map = new TreeMap<>();

        for(String x : pairs){
            String[] kv = x.split("=");

            String key = URLDecoder.decode(kv[0], "UTF-8");
            String value = URLDecoder.decode(kv[1], "UTF-8");

            map.put(key, value);
        }

        String ans = "";

        for(Map.Entry<String,String> e : map.entrySet()){
            ans += e.getKey() + "=" + e.getValue() + ";";
        }

        ans = ans.substring(0, ans.length() - 1);

        String result = requestId + ";" + ans;

        System.out.println(result);

        DatagramPacket resultPacket = new DatagramPacket(result.getBytes(), result.length(), serverAddress, serverPort);
        socket.send(resultPacket);

        socket.close();
    }
}
