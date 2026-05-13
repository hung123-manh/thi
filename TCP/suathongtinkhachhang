package TCP;
import java.io.*;
import java.net.*;

public class TCP_7wI2sHjM {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("36.50.135.242",2209);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        // gui
        String code = "B22DCAT134;7wI2sHjM";
        out.writeObject(code);
        out.flush();
        // nhan
        Customer x = (Customer) in.readObject();
        System.out.println(x.getName());
        System.out.println(x.getDayOfBirth());
        // xu ly name
        String[] arr = x.getName().trim().toLowerCase().split("\\s+");
        String ho = arr[arr.length - 1].toUpperCase();
        String ten = "";
        for(int i = 0; i < arr.length - 1; i++){
            ten += Character.toUpperCase(arr[i].charAt(0))
                    + arr[i].substring(1) + " ";
        }
        ten = ten.trim();
        String newName = ho + ", " + ten;
        // xu ly ngay sinh
        String[] date = x.getDayOfBirth().split("-");
        String newDate = date[1] + "/" + date[0] + "/" + date[2];
        // tao username
        String user = "";
        for(int i = 0; i < arr.length - 1; i++){
            user += arr[i].charAt(0);
        }
        user += arr[arr.length - 1];
        // gan lai
        x.setName(newName);
        x.setDayOfBirth(newDate);
        x.setUserName(user);
        System.out.println(x.getName());
        System.out.println(x.getDayOfBirth());
        System.out.println(x.getUserName());
        // gui ket qua
        out.writeObject(x);
        out.flush();
        in.close();
        out.close();
        socket.close();
    }
}
