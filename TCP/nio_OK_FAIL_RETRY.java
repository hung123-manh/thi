package TCP;

import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class ncTsAcGB {

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

        String message = "B22DCAT134;ncTsAcGB";

        byte[] sendData =
                message.getBytes("UTF-8");

        ByteBuffer sendBuffer =
                ByteBuffer.allocate(
                        4 + sendData.length
                );

        sendBuffer.putInt(sendData.length);

        sendBuffer.put(sendData);

        sendBuffer.flip();

        while(sendBuffer.hasRemaining()){
            channel.write(sendBuffer);
        }

        // =========================
        // Nhận 2 frame
        // =========================

        String total = "";

        for(int i = 0; i < 2; i++){

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

            byte[] frameData =
                    new byte[len];

            dataBuffer.get(frameData);

            total += new String(
                    frameData,
                    "UTF-8"
            );
        }

        System.out.println(total);

        // =========================
        // Xử lý dữ liệu
        // =========================

        ArrayList<String> ok =
                new ArrayList<>();

        ArrayList<String> fail =
                new ArrayList<>();

        ArrayList<String> retry =
                new ArrayList<>();

        String[] records =
                total.split("\\|");

        for(String record : records){

            String[] parts =
                    record.split(",");

            String id = parts[0];

            String status = parts[2];

            if(status.equals("OK")){
                ok.add(id);
            }
            else if(status.equals("FAIL")){
                fail.add(id);
            }
            else if(status.equals("RETRY")){
                retry.add(id);
            }
        }

        String result =
                "OK=" + String.join(",", ok)
                + ";FAIL=" + String.join(",", fail)
                + ";RETRY=" + String.join(",", retry);

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

        resultBuffer.putInt(
                resultData.length
        );

        resultBuffer.put(resultData);

        resultBuffer.flip();

        while(resultBuffer.hasRemaining()){
            channel.write(resultBuffer);
        }

        channel.close();
    }
}
