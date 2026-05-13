package TCP;
import java.io.*;
import java.net.*;

public class TCP_DoAo93R4 {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("36.50.135.242",2207);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        // gui
        String code = "B22DCAT134;DoAo93R4";
        out.writeUTF(code);
        out.flush();
        // nhan
        String s = in.readUTF();
        int k = in.readInt();
        System.out.println(s);
        System.out.println(k);
        // xu ly
        String decode = "";
        for(char c : s.toCharArray()){

            if(c >= 'A' && c <= 'Z'){
                decode += (char)((c - 'A' - k + 26) % 26 + 'A');
            }
            else if(c >= 'a' && c <= 'z'){
                decode += (char)((c - 'a' - k + 26) % 26 + 'a');
            }
            else{
                decode += c;
            }
        }
        System.out.println(decode);
        // gui ket qua
        out.writeUTF(decode);
        out.flush();
        in.close();
        out.close();
        socket.close();
    }
}
