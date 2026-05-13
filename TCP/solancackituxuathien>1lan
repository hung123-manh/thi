package TCP;
import java.io.*;
import java.net.*;

public class TCP_MNouNV5K {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("36.50.135.242",2208);
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        // gui
        String code = "B22DCAT134;MNouNV5K";
        bw.write(code);
        bw.newLine();
        bw.flush();
        // nhan
        String s = br.readLine();
        System.out.println(s);
        // xu ly
        int[] cnt = new int[256];
        for(char x : s.toCharArray()){
            if(Character.isLetterOrDigit(x)){
                cnt[x]++;
            }
        }
        String ans = "";
        for(char x : s.toCharArray()){
            if(Character.isLetterOrDigit(x) && cnt[x] > 1){
                ans += x + ":" + cnt[x] + ",";
                cnt[x] = 0;
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
