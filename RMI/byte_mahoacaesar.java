package RMI;
import java.rmi.registry.*;
public class RMI_1Y4xaL3Y {
    public static void main(String[] args) throws Exception{
        Registry registry = LocateRegistry.getRegistry("36.50.135.242",1099);
        ByteService service = (ByteService) registry.lookup("RMIByteService");
        String studentCode = "B22DCAT134";
        String qCode = "1Y4xaL3Y";
        // request
        byte[] data = service.requestData(studentCode,qCode);
        System.out.println(new String(data));
        // tinh buoc dich
        int shift = data.length;
        // ma hoa Caesar
        byte[] ans = new byte[data.length];
        for(int i = 0;i < data.length;i++){
            ans[i] = (byte)(data[i] + shift);
        }
        System.out.println(new String(ans));
        // submit
        service.submitData(studentCode,qCode,ans);
        System.out.println("Done");
    }
}
