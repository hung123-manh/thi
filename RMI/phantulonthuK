package RMI;

import java.rmi.registry.*;
import java.util.*;

public class RMI_FHhjbxzW {
    public static void main(String[] args) throws Exception{

        Registry registry = LocateRegistry.getRegistry("36.50.135.242",1099);

        DataService service = (DataService) registry.lookup("RMIDataService");

        String studentCode = "B22DCAT134";
        String qCode = "FHhjbxzW";

        // request
        String s = (String) service.requestData(studentCode,qCode);

        System.out.println(s);

        // xu ly
        String[] parts = s.split(";");

String[] arr = parts[0].split(",");

int k = Integer.parseInt(parts[1].trim());

ArrayList<Integer> a = new ArrayList<>();

for(String x : arr){
    a.add(Integer.parseInt(x.trim()));
}

Collections.sort(a,Collections.reverseOrder());

int ans = a.get(k - 1);


        // submit
        service.submitData(studentCode,qCode,ans);

        System.out.println("Done");
    }
}
