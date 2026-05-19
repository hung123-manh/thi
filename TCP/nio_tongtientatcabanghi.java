package TCP;

import java.net.*;
import java.nio.*;
import java.nio.channels.*;

public class bbyyLM6s {

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
                "B22DCAT134;bbyyLM6s";

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
        // Nhận 3 frame
        // =========================

        String totalData = "";

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

        String[] records =
                totalData.split("\\|");

        double sum = 0;

        int large = 0;

        for(String record : records){

            String[] parts =
                    record.split(":");

            int quantity =
                    Integer.parseInt(parts[1]);

            double unitPrice =
                    Double.parseDouble(parts[2]);

            double total =
                    quantity * unitPrice;

            sum += total;

            if(total >= 500){
                large++;
            }
        }

        sum =
                Math.round(sum * 100.0)
                / 100.0;

        String result =
                "total="
                + String.format("%.2f", sum)
                + ";large="
                + large;

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
