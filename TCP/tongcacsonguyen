package TCP;
import java.io.*;
import java.net.*;

public class TCP_77u9Q9dI {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("36.50.135.242",2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        // gui
        String code = "B22DCAT134;77u9Q9dI";
        out.write(code.getBytes());
        out.flush();
        // nhan
        byte[] buffer = new byte[1024];
        int bytesRead = in.read(buffer);
        String s = new String(buffer,0,bytesRead);
        System.out.println(s);
        // xu ly
        String[] arr = s.trim().split("\\|");
        int sum = 0;
        for(String x : arr){
            sum += Integer.parseInt(x);
        }
        String ans = String.valueOf(sum);
        System.out.println(ans);
        // gui ket qua
        out.write(ans.getBytes());
        out.flush();
        in.close();
        out.close();
        socket.close();
    }
}
