/*b. Nhận dữ liệu từ server gồm đúng 3 frame liên tiếp. Payload của mỗi frame là một phần của cùng một HTTP request, client phải nối 3 payload theo đúng thứ tự để thu được chuỗi HTTP request hoàn chỉnh (các dòng phân tách bởi "\r\n" và kết thúc bằng "\r\n\r\n").
c. Từ chuỗi HTTP request hoàn chỉnh, trích xuất và gửi lại lên server theo định dạng "METHOD;PATH;HOST" trong đó PATH luôn bao gồm query-string.*/
package TCP;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
public class TCP_uwCXDZwi {
    public static void readFully(SocketChannel sc, ByteBuffer bb) throws Exception{
        while(bb.hasRemaining()){
            sc.read(bb);
        }
    }
    public static void writeFully(SocketChannel sc, ByteBuffer bb) throws Exception{
        while(bb.hasRemaining()){
            sc.write(bb);
        }
    }
    public static void main(String[] args) throws Exception{
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("36.50.135.242",2211));
        // gui
        String code = "B22DCAT134;uwCXDZwi";
        byte[] data = code.getBytes("UTF-8");
        ByteBuffer sendBuffer = ByteBuffer.allocate(4 + data.length);
        sendBuffer.putInt(data.length);
        sendBuffer.put(data);
        sendBuffer.flip();
        writeFully(sc,sendBuffer);
        // nhan 3 frame
        String http = "";
        for(int i = 0; i < 3; i++){
            ByteBuffer lenBuffer = ByteBuffer.allocate(4);
            readFully(sc,lenBuffer);
            lenBuffer.flip();
            int len = lenBuffer.getInt();
            ByteBuffer dataBuffer = ByteBuffer.allocate(len);
            readFully(sc,dataBuffer);
            dataBuffer.flip();
            byte[] bytes = new byte[len];
            dataBuffer.get(bytes);
            http += new String(bytes,"UTF-8");
        }
        System.out.println(http);
        // xu ly
        String[] lines = http.split("\\r\\n");
        String[] first = lines[0].split("\\s+");
        String method = first[0];
        String path = first[1];
        String host = "";
        for(String x : lines){
            if(x.startsWith("Host:")){
                host = x.substring(5).trim();
            }
        }
        String ans = method + ";" + path + ";" + host;
        System.out.println(ans);
        // gui ket qua
        byte[] result = ans.getBytes("UTF-8");
        ByteBuffer resultBuffer = ByteBuffer.allocate(4 + result.length);
        resultBuffer.putInt(result.length);
        resultBuffer.put(result);
        resultBuffer.flip();
        writeFully(sc,resultBuffer);
        sc.close();
    }
}
