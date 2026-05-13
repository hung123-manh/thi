package TCP;

import java.io.*;
import java.net.*;

public class TCP_Bq2bmTBp {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("36.50.135.242",2209);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        // gui
        String code = "B22DCAT134;Bq2bmTBp";
        out.writeObject(code);
        out.flush();
        // nhan
        Laptop x = (Laptop) in.readObject();
        System.out.println(x.getName());
        System.out.println(x.getQuantity());
        // xu ly ten
        String[] arr = x.getName().split("\\s+");
        String tmp = arr[0];
        arr[0] = arr[arr.length - 1];
        arr[arr.length - 1] = tmp;
        String newName = "";
        for(String s : arr){
            newName += s + " ";
        }
        newName = newName.trim();
        // xu ly quantity
        int q = x.getQuantity();
        int newQ = 0;
        while(q > 0){
            newQ = newQ * 10 + q % 10;
            q /= 10;
        }
        x.setName(newName);
        x.setQuantity(newQ);
        System.out.println(x.getName());
        System.out.println(x.getQuantity());
        // gui ket qua
        out.writeObject(x);
        out.flush();
        in.close();
        out.close();
        socket.close();
    }
}
