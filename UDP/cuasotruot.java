package UDP;

import java.net.*;
import java.util.*;

public class UDP_Q1cWcYhO {

    public static void main(String[] args) throws Exception {

        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("36.50.135.242");
        int serverPort = 2207;

        // gui
        String message = ";B22DCAT134;Q1cWcYhO";

        DatagramPacket sendPacket = new DatagramPacket(
                message.getBytes(),
                message.length(),
                serverAddress,
                serverPort
        );

        socket.send(sendPacket);

        // nhan
        byte[] buffer = new byte[4096];

        DatagramPacket receivePacket = new DatagramPacket(
                buffer,
                buffer.length
        );

        socket.receive(receivePacket);

        String received = new String(
                receivePacket.getData(),
                0,
                receivePacket.getLength()
        );

        System.out.println(received);

        // xu ly
        String[] parts = received.split(";");

        String requestId = parts[0];
        int n = Integer.parseInt(parts[1]);
        int k = Integer.parseInt(parts[2]);

        String[] arrStr = parts[3].split(",");

        int[] arr = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(arrStr[i]);
        }

        Deque<Integer> dq = new LinkedList<>();
        String resultValue = "";

        for(int i = 0; i < n; i++){

            while(!dq.isEmpty() && dq.peekFirst() <= i - k){
                dq.pollFirst();
            }

            while(!dq.isEmpty() && arr[dq.peekLast()] <= arr[i]){
                dq.pollLast();
            }

            dq.offerLast(i);

            if(i >= k - 1){
                resultValue += arr[dq.peekFirst()] + ",";
            }
        }

        if(resultValue.endsWith(",")){
            resultValue = resultValue.substring(0, resultValue.length() - 1);
        }

        String result = requestId + ";" + resultValue;

        System.out.println(result);

        // gui ket qua
        DatagramPacket resultPacket = new DatagramPacket(
                result.getBytes(),
                result.length(),
                serverAddress,
                serverPort
        );

        socket.send(resultPacket);

        socket.close();
    }
}
