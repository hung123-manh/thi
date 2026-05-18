package UDP;

import java.io.*;
import java.net.*;

public class KpBJTnAj {
    public static void main(String[] args) throws Exception{

        DatagramSocket socket = new DatagramSocket();

        InetAddress serverAddress = InetAddress.getByName("36.50.135.242");

        int serverPort = 2209;

        String message = ";B22DCAT134;KpBJTnAj";

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

        // tinh tong chu so nam
        String year = emp.getHireDate().substring(0,4);

        int sum = 0;

        for(char x : year.toCharArray()){
            sum += x - '0';
        }

        // tang salary
        int newSalary = (int)Math.round(
                emp.getSalary() * (1 + sum / 100.0)
        );

        emp.setSalary(newSalary);

        // doi ngay
        String[] d = emp.getHireDate().split("-");

        String newDate = d[2] + "/" + d[1] + "/" + d[0];

        emp.setHireDate(newDate);

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
