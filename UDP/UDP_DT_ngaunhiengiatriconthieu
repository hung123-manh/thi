/* Nhận thông điệp là một chuỗi từ server theo định dạng “requestId;n;A1,A2,...An” , với  - requestId là chuỗi ngẫu nhiên duy nhất  - n là một số ngẫu nhiên nhỏ hơn
100.  -            A1, A2 ... Am (m <= n) là các giá trị ngẫu nhiên nhỏ hơn hoặc bằng n và có thể trùng nhau. */
package UDP;
import java.net.*;
import java.util.*;

public class UDP_0Th9QK7P {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("36.50.135.242");
        int serverPort = 2207;
        // gui
        String message = ";B22DCAT134;0Th9QK7P";
        DatagramPacket sendPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, serverPort);
        socket.send(sendPacket);
        // nhan
        byte[] buffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(receivePacket);
        String received = new String(receivePacket.getData(),0,receivePacket.getLength());
        System.out.println(received);
        // xu ly
        String[] parts = received.split(";");
        String requestId = parts[0];
        int n = Integer.parseInt(parts[1]);
        String[] arr = parts[2].split(",");
        boolean[] check = new boolean[n + 1];
        for(String x : arr){
            check[Integer.parseInt(x)] = true;
        }
        String missing = "";
        for(int i = 1; i <= n; i++){
            if(!check[i]){
                missing += i + ",";
            }
        }
        if(missing.endsWith(",")){
            missing = missing.substring(0, missing.length() - 1);
        }
        String result = requestId + ";" + missing;
        System.out.println(result);
        // gui ket qua
        DatagramPacket resultPacket = new DatagramPacket(result.getBytes(), result.length(), serverAddress, serverPort);
        socket.send(resultPacket);
        socket.close();
    }
}
