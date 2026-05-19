package TCP;

import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class mZEyp7wI {

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

        String message =
                "B22DCAT134;mZEyp7wI";

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

        String totalData = "";

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

            byte[] data =
                    new byte[len];

            dataBuffer.get(data);

            totalData +=
                    new String(data, "UTF-8");
        }

        System.out.println(totalData);

        // =========================
        // Xử lý dữ liệu
        // =========================

        ArrayList<String> pass =
                new ArrayList<>();

        ArrayList<String> review =
                new ArrayList<>();

        ArrayList<String> fail =
                new ArrayList<>();

        String[] records =
                totalData.split("\\|");

        for(String record : records){

            String[] parts =
                    record.split(",");

            String id = parts[0];

            int score =
                    Integer.parseInt(parts[2]);

            if(score >= 80){
                pass.add(id);
            }
            else if(score >= 50){
                review.add(id);
            }
            else{
                fail.add(id);
            }
        }

        String result =
                "PASS="
                + String.join(",", pass)
                + ";REVIEW="
                + String.join(",", review)
                + ";FAIL="
                + String.join(",", fail);

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
