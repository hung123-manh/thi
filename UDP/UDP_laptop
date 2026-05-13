package UDP;

import java.net.*;
import java.io.*;
import java.util.*;

public class UDP_szU7OTTX {

    public static void main(String[] args) throws Exception {

        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("36.50.135.242");
        int serverPort = 2209;

        // gui
        String message = ";B22DCAT134;szU7OTTX";

        DatagramPacket sendPacket = new DatagramPacket(
                message.getBytes(),
                message.length(),
                serverAddress,
                serverPort
        );
        socket.send(sendPacket);

        // nhan
        byte[] buffer = new byte[4096];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(receivePacket);

        byte[] data = receivePacket.getData();

        // lay requestId
        String requestId = new String(data, 0, 8);

        // lay object
        ByteArrayInputStream bais = new ByteArrayInputStream(data, 8, receivePacket.getLength() - 8);
        ObjectInputStream ois = new ObjectInputStream(bais);

        Product p = (Product) ois.readObject();

        // xu ly name
        String[] arr = p.getName().split("\\s+");

String temp = arr[0];
arr[0] = arr[arr.length - 1];
arr[arr.length - 1] = temp;

String newName = "";

for(String x : arr){
    newName += x + " ";
}

newName = newName.trim();

        // xu ly quantity
        int q = p.getQuantity();
        int newQ = 0;

        while(q > 0){
            newQ = newQ * 10 + q % 10;
            q /= 10;
        }

        p.setName(newName);
        p.setQuantity(newQ);

        // gui ket qua
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        baos.write(requestId.getBytes());

        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(p);
        oos.flush();

        byte[] sendData = baos.toByteArray();

        DatagramPacket resultPacket = new DatagramPacket(
                sendData,
                sendData.length,
                serverAddress,
                serverPort
        );

        socket.send(resultPacket);

        socket.close();
    }
}
