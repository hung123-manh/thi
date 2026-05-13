/* b. Nhận thông điệp từ server theo định dạng “requestId; data”   - requestId là một chuỗi ngẫu nhiên duy nhất  - data là chuỗi dữ liệu đầu vào cần xử lý
Ex: “requestId;Qnc8d5x78aldSGWWmaAAjyg3”
c. Tìm kiếm ký tự xuất hiện nhiều nhất trong chuỗi và gửi lên server theo định dạng “requestId;ký tự xuất hiện nhiều nhất: các vị trí xuất hiện ký tự đó”   ví dụ: “requestId;8:4,9,” */
package UDP;
import java.net.*;
import java.util.*;

public class UDP_LRm0VFAQ {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("36.50.135.242");
        int serverPort = 2208;
        // gui
        String message = ";B22DCAT134;LRm0VFAQ";
        DatagramPacket sendPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, serverPort);
        socket.send(sendPacket);
        // nhan
        byte[] buffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        socket.receive(receivePacket);
        String received = new String(receivePacket.getData(),0,receivePacket.getLength());
        System.out.println(received);
        // xu ly
        String[] parts = received.split(";");
        String requestId = parts[0];
        String s = parts[1];
        HashMap<Character,Integer> map = new HashMap<>();
        for(char c : s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        char maxChar = s.charAt(0);
        int maxFreq = 0;
        for(char c : s.toCharArray()){
            if(map.get(c) > maxFreq){
                maxFreq = map.get(c);
                maxChar = c;
            }
        }
        String pos = "";
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == maxChar){
                pos += (i + 1) + ",";
            }
        }
        String result = requestId + ";" + maxChar + ":" + pos;
        System.out.println(result);
        // gui ket qua
        DatagramPacket resultPacket = new DatagramPacket(result.getBytes(), result.length(), serverAddress, serverPort);
        socket.send(resultPacket);
        socket.close();
    }
}
