package UDP;

import java.net.*;
import java.util.*;
import java.io.*;

public class _3pDyTO4G {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("36.50.135.242");
        int serverPort = 2208;

        String message = ";B22DCAT134;3pDyTO4G";

        DatagramPacket sendPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, serverPort);
        socket.send(sendPacket);

        byte[] buffer = new byte[2048];

        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(receivePacket);

        String received = new String(receivePacket.getData(), 0, receivePacket.getLength());

        System.out.println(received);

        String[] parts = received.split(";", 2);

        String requestId = parts[0];
        String data = parts[1];

        String[] lines = data.split("\\|");

        int error = 0;
        int info = 0;
        int warn = 0;

        String ans = "";

        for(String line : lines){

            if(line.startsWith("ERROR")) error++;
            if(line.startsWith("INFO")) info++;
            if(line.startsWith("WARN")) warn++;

            line = line.replaceAll("email=[^\\s]+", "email=[EMAIL]");
            line = line.replaceAll("phone=[^\\s]+", "phone=[PHONE]");
            line = line.replaceAll("token=[^\\s]+", "token=[TOKEN]");

            ans += line + "|";
        }

        ans = ans.substring(0, ans.length() - 1);

        ans += "##ERROR=" + error + ";INFO=" + info + ";WARN=" + warn;

        String result = requestId + ";" + ans;

        System.out.println(result);

        DatagramPacket resultPacket = new DatagramPacket(result.getBytes(), result.length(), serverAddress, serverPort);

        socket.send(resultPacket);

        socket.close();
    }
}
