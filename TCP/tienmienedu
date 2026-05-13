package TCP;
import java.io.*;
import java.net.*;
import java.util.*;

public class TCP_ZFO9fHXd {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("36.50.135.242",2208);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));       
        // gui
        String code = "B22DCAT134;ZFO9fHXd";
        bw.write(code);
        bw.newLine();
        bw.flush();
        // nhan
        String s = br.readLine();
        System.out.println(s);
        // xu ly
        String[] arr = s.trim().split(",");
        ArrayList<String> res = new ArrayList<>();
        for(String x : arr){
            if(x.endsWith(".edu")){
                res.add(x);
            }
        }
        String ans = "";
        for(int i = 0; i < res.size(); i++){
            ans += res.get(i);
            if(i != res.size() - 1){
                ans += ", ";
            }
        }
        System.out.println(ans);
        // gui ket qua
        bw.write(ans);
        bw.newLine();
        bw.flush();
        br.close();
        bw.close();
        socket.close();
    }
}
