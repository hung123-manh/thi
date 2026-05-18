package UDP;

import java.io.*;
import java.net.*;

public class GHHPDKGx {
    public static void main(String[] args) throws Exception{

        DatagramSocket socket = new DatagramSocket();

        InetAddress serverAddress = InetAddress.getByName("36.50.135.242");

        int serverPort = 2209;

        String message = ";B22DCAT134;GHHPDKGx";

        DatagramPacket sendPacket = new DatagramPacket(
                message.getBytes(),
                message.length(),
                serverAddress,
                serverPort
        );

        socket.send(sendPacket);

        byte[] buffer = new byte[1024];

        DatagramPacket receivePacket = new DatagramPacket(
                buffer,
                buffer.length
        );

        socket.receive(receivePacket);

        byte[] data = receivePacket.getData();

        String requestId = new String(data,0,8);

        ByteArrayInputStream bis = new ByteArrayInputStream(
                data,
                8,
                receivePacket.getLength() - 8
        );

        ObjectInputStream ois = new ObjectInputStream(bis);

        PricedProduct p = (PricedProduct) ois.readObject();

        double fee = 0;

        if(p.getBasePrice() >= 1000){
            fee = 25;
        }

        double finalPrice = p.getBasePrice()
                * (1 + p.getTaxRate() / 100.0)
                * (1 - p.getDiscountRate() / 100.0)
                + fee;

        finalPrice = Math.round(finalPrice * 100.0) / 100.0;

        p.setFinalPrice(finalPrice);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(p);

        oos.flush();

        byte[] objectData = bos.toByteArray();

        ByteArrayOutputStream finalBos = new ByteArrayOutputStream();

        finalBos.write(requestId.getBytes());

        finalBos.write(objectData);

        byte[] sendData = finalBos.toByteArray();

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
