package UDP;
import java.io.*;
import java.net.*;
import java.util.*;
public class TongHaiSoNhiPhan{
    public static void main(String[] args) throws IOException{
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int sP = 2208;
        //a.
        String code = ";B22DCAT134;lIQVug9S";
        DatagramPacket dpGui = new DatagramPacket(code.getBytes(), code.length(), sA, sP);
        socket.send(dpGui);
        //b.
        byte []buffer = new byte[1024];
        DatagramPacket dpNhan = new DatagramPacket(buffer, buffer.length);
        socket.receive(dpNhan);
        //In chuỗi đề cho
        String st = new String(dpNhan.getData());
        System.out.println(st);
        st = st.replace(",", " "); 
        st = st.replace(";", " ");
        //Tách dữ liệu
        String []tmp = st.trim().split("\\s+");
        String rqID = tmp[0]; 
        long b1 = Integer.parseInt(tmp[1], 2); 
        long b2 = Integer.parseInt(tmp[2], 2);

        long tong = b1 + b2;

        String ans = String.format("%s;%d", rqID, tong);

        System.out.println(ans);

        //Gửi
        DatagramPacket dpGui1 = new DatagramPacket(ans.getBytes(), ans.length(), sA, sP);
        socket.send(dpGui1);
    }
}
