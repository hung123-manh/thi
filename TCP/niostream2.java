/*b. Nhận dữ liệu từ server gồm đúng 2 frame liên tiếp. Payload của mỗi frame là một phần của cùng một query-string, client phải nối 2 payload theo đúng thứ tự để thu được chuỗi query-string hoàn chỉnh dạng "k1=v1&k2=v2&..."; các key có thể không theo thứ tự và value có thể được URL-encode.
c. Giải mã URL-encoding và gửi lại chuỗi chuẩn hóa theo định dạng "k1=v1;k2=v2;..." với các key được sắp xếp tăng dần theo thứ tự từ điển.*/
package TCP;

import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.net.URLDecoder;
import java.util.*;

public class _9DWO0CVq {

    public static void readFully(SocketChannel channel, ByteBuffer buffer) throws Exception{
        while(buffer.hasRemaining()){
            channel.read(buffer);
        }
    }

    public static void writeFully(SocketChannel channel, ByteBuffer buffer) throws Exception{
        while(buffer.hasRemaining()){
            channel.write(buffer);
        }
    }

    public static void main(String[] args) throws Exception{

        SocketChannel channel = SocketChannel.open();

        channel.connect(new InetSocketAddress("36.50.135.242", 2211));

        // gui ma sv + qCode
        String message = "B22DCAT134;9DWO0CVq";

        byte[] data = message.getBytes("UTF-8");

        ByteBuffer outBuffer = ByteBuffer.allocate(4 + data.length);

        outBuffer.putInt(data.length);

        outBuffer.put(data);

        outBuffer.flip();

        writeFully(channel, outBuffer);

        // nhan frame 1
        ByteBuffer lenBuffer1 = ByteBuffer.allocate(4);

        readFully(channel, lenBuffer1);

        lenBuffer1.flip();

        int len1 = lenBuffer1.getInt();

        ByteBuffer dataBuffer1 = ByteBuffer.allocate(len1);

        readFully(channel, dataBuffer1);

        dataBuffer1.flip();

        String part1 = new String(dataBuffer1.array(), "UTF-8");

        // nhan frame 2
        ByteBuffer lenBuffer2 = ByteBuffer.allocate(4);

        readFully(channel, lenBuffer2);

        lenBuffer2.flip();

        int len2 = lenBuffer2.getInt();

        ByteBuffer dataBuffer2 = ByteBuffer.allocate(len2);

        readFully(channel, dataBuffer2);

        dataBuffer2.flip();

        String part2 = new String(dataBuffer2.array(), "UTF-8");

        // noi query string
        String query = part1 + part2;

        System.out.println(query);

        // xu ly
        String[] pairs = query.split("&");

        TreeMap<String,String> map = new TreeMap<>();

        for(String x : pairs){

            String[] kv = x.split("=");

            String key = URLDecoder.decode(kv[0], "UTF-8");

            String value = URLDecoder.decode(kv[1], "UTF-8");

            map.put(key, value);
        }

        String ans = "";

        for(Map.Entry<String,String> e : map.entrySet()){
            ans += e.getKey() + "=" + e.getValue() + ";";
        }

        ans = ans.substring(0, ans.length() - 1);

        System.out.println(ans);

        // gui ket qua
        byte[] sendData = ans.getBytes("UTF-8");

        ByteBuffer sendBuffer = ByteBuffer.allocate(4 + sendData.length);

        sendBuffer.putInt(sendData.length);

        sendBuffer.put(sendData);

        sendBuffer.flip();

        writeFully(channel, sendBuffer);

        channel.close();
    }
}
