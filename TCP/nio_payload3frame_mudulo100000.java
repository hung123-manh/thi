package TCP;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;

public class h59RwR7T {

    public static void main(String[] args) throws Exception{

        SocketChannel channel = SocketChannel.open();

        channel.connect(
                new InetSocketAddress(
                        "36.50.135.242",
                        2211
                )
        );

        // =========================
        // Gửi studentCode;qCode
        // =========================

        String message = "B22DCAT134;h59RwR7T";

        byte[] data = message.getBytes("UTF-8");

        ByteBuffer sendBuffer = ByteBuffer.allocate(
                4 + data.length
        );

        sendBuffer.putInt(data.length);

        sendBuffer.put(data);

        sendBuffer.flip();

        while(sendBuffer.hasRemaining()){
            channel.write(sendBuffer);
        }

        // =========================
        // Nhận 3 frame
        // =========================

        String total = "";

        for(int i = 0; i < 3; i++){

            ByteBuffer lenBuffer =
                    ByteBuffer.allocate(4);

            while(lenBuffer.hasRemaining()){
                channel.read(lenBuffer);
            }

            lenBuffer.flip();

            int len = lenBuffer.getInt();

            ByteBuffer dataBuffer =
                    ByteBuffer.allocate(len);

            while(dataBuffer.hasRemaining()){
                channel.read(dataBuffer);
            }

            dataBuffer.flip();

            byte[] frameData = new byte[len];

            dataBuffer.get(frameData);

            String part =
                    new String(frameData,"UTF-8");

            total += part;
        }

        System.out.println(total);

        // =========================
        // Tính checksum
        // =========================

        int checksum = 0;

        for(char c : total.toCharArray()){

            checksum += (int)c;

            checksum %= 100000;
        }

        String result =
                "len="
                + total.length()
                + ";checksum="
                + checksum;

        System.out.println(result);

        // =========================
        // Gửi kết quả
        // =========================

        byte[] resultData =
                result.getBytes("UTF-8");

        ByteBuffer resultBuffer =
                ByteBuffer.allocate(
                        4 + resultData.length
                );

        resultBuffer.putInt(resultData.length);

        resultBuffer.put(resultData);

        resultBuffer.flip();

        while(resultBuffer.hasRemaining()){
            channel.write(resultBuffer);
        }

        channel.close();
    }
}
