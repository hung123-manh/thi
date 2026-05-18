package UDP;
import java.io.*;
import java.net.*;
import java.util.*;
public class SXTheoVTGoc{
    public static void main(String[] args) throws IOException{
        DatagramSocket socket = new DatagramSocket();
        InetAddress sA = InetAddress.getByName("36.50.135.242");
        int sP = 2207;
        //a.
        String code = ";B22DCAT134;aKZwZxWk";
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
        //Cho các xâu còn lại vào mảng
        TreeMap<Integer, String> mp = new TreeMap<>();
        for(int i = 1; i < tmp.length; i++){
            String []tmp_i = tmp[i].split(":");
            mp.put(Integer.parseInt(tmp_i[1]), tmp_i[0]);
        }
        //Tạo kết quả
        String ans = rqID + ";";
        int ok = 0;
        for(String x: mp.values()){
            if(ok == 1) ans += ",";
            ans += x;
            ok = 1;
        }
        System.out.println(ans);
        //Gửi
        DatagramPacket dpGui1 = new DatagramPacket(ans.getBytes(), ans.length(), sA, sP);
        socket.send(dpGui1);
    }
}
