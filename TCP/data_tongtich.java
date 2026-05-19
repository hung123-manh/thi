package TCP;
import java.io.*;
import java.net.*;

public class TCP_tJ7wbgN1 {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("36.50.135.242",2207);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        // gui
        String code = "B22DCAT134;tJ7wbgN1";
        out.writeUTF(code);
        out.flush();
        // nhan
        int a = in.readInt();
        int b = in.readInt();
        System.out.println(a);
        System.out.println(b);
        // xu ly
        int tong = a + b;
        int tich = a * b;
        System.out.println(tong);
        System.out.println(tich);
        // gui ket qua
        out.writeInt(tong);
        out.writeInt(tich);
        out.flush();
        in.close();
        out.close();
        socket.close();
    }
}
