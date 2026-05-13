package RMI;
import java.rmi.registry.*;
public class RMI_e579C3tX {
    public static void main(String[] args) throws Exception{
        Registry registry = LocateRegistry.getRegistry("36.50.135.242",1099);
        ByteService service = (ByteService) registry.lookup("RMIByteService");
        String studentCode = "B22DCAT134";
        String qCode = "e579C3tX";
        // request
        byte[] data = service.requestData(studentCode,qCode);
        System.out.println(new String(data));
        // xor
        String key = "PTIT";
        byte[] k = key.getBytes();
        byte[] ans = new byte[data.length];
        for(int i = 0;i < data.length;i++){
            ans[i] = (byte)(data[i] ^ k[i % k.length]);
        }
        // submit
        service.submitData(studentCode,qCode,ans);
        System.out.println("Done");
    }
}
