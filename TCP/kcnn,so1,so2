package TCP;
import java.io.*;
import java.net.*;
import java.util.*;

public class TCP_KainYykL {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("36.50.135.242",2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        // gui
        String code = "B22DCAT134;KainYykL";
        out.write(code.getBytes());
        out.flush();
        // nhan
        byte[] buffer = new byte[1024];
        int bytesRead = in.read(buffer);
        String s = new String(buffer,0,bytesRead);
        System.out.println(s);
        // xu ly
        String[] arr = s.trim().split(",");
        ArrayList<Integer> a = new ArrayList<>();
        for(String x : arr){
            a.add(Integer.parseInt(x));
        }
        int minDiff = Integer.MAX_VALUE;
        int x = 0;
        int y = 0;
        for(int i = 0; i < a.size(); i++){
            for(int j = i + 1; j < a.size(); j++){
                int diff = Math.abs(a.get(i) - a.get(j));
                if(diff < minDiff){
                    minDiff = diff;

                    if(a.get(i) > a.get(j)){
                        x = a.get(i);
                        y = a.get(j);
                    }
                    else{
                        x = a.get(j);
                        y = a.get(i);
                    }
                }
            }
        }
        String ans = minDiff + "," + y + "," + x;
        System.out.println(ans);
        // gui ket qua
        out.write(ans.getBytes());
        out.flush();
        in.close();
        out.close();
        socket.close();
    }
}
