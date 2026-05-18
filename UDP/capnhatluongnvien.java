package UDP;

import java.io.*;
import java.net.*;

public class GtD8Degt {
    public static void main(String[] args) throws Exception{

        DatagramSocket socket = new DatagramSocket();

        InetAddress serverAddress = InetAddress.getByName("36.50.135.242");

        int serverPort = 2209;

        String message = ";B22DCAT134;GtD8Degt";

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

        Employee emp = (Employee) ois.readObject();

        // chuan hoa ten
        String[] words = emp.getName().toLowerCase().trim().split("\\s+");

        String newName = "";

        for(String x : words){
            newName += Character.toUpperCase(x.charAt(0))
                    + x.substring(1) + " ";
        }

        emp.setName(newName.trim());

        // tang luong 8%
        double newSalary = emp.getSalary() * 1.08;

        newSalary = Math.round(newSalary * 100.0) / 100.0;

        emp.setSalary(newSalary);

        // ghi object
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(emp);

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
