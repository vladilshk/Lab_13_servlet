package Data;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class DataBase {

    //private static Map<String, List<String>> users;


    public static boolean addUser(String name, String number) throws IOException {

        Map<String, List<String>> users = getData();
        if(number != null || number.equals("")) {
            List<String> numbers;
            if (!users.containsKey(name)) {
                numbers = new LinkedList<>();
            } else {
                numbers = users.get(name);
            }
            numbers.add(number);
            users.put(name, numbers);
            sendData(users);
            return true;
        }
        return false;

    }

    public static String getUsersForPrint() throws IOException {
        Map<String, List<String>> users = getData();
        StringBuilder st = new StringBuilder();
        List<String> numbers;
        for(String key : users.keySet()){
            st.append(key + ":<br>");
            numbers = users.get(key);
            for (int i = 0; i < numbers.size(); i++) {
                st.append("&ensp;" + numbers.get(i) + "<br>");
            }
        }
        st.append("<br>");

        return st.toString();
    }

    public static Map<String, List<String>> getData() throws IOException {
        String file = new String(Files.readAllBytes(Paths.get("data.txt")));
        Map<String, List<String>> users = new TreeMap<>();
        int idx = 0;
        while (idx < file.length()){
            StringBuilder name = new StringBuilder();
            while (file.charAt(idx) != ':'){
                name.append(file.charAt(idx));
                idx++;
            }
            idx += 4;

            List<String> numbers = new LinkedList<>();
            while (file.charAt(idx) != ';'){
                StringBuilder number = new StringBuilder();
                while (file.charAt(idx) != '\n' && file.charAt(idx) != ';'){
                    number.append(file.charAt(idx));
                    idx++;
                }
                if(file.charAt(idx) == '\n')
                    idx++;
                numbers.add(number.toString());
            }
            users.put(name.toString(), numbers);
            idx += 4;
        }
        return users;
    }

    public static void sendData(Map<String, List<String>> users) throws IOException {
        StringBuilder file = new StringBuilder();
        for(String key: users.keySet()){
            file.append(key + ":\n{\n");
            for (int i = 0; i < users.get(key).size(); i++) {
                if(i != users.get(key).size() - 1) {
                    file.append(users.get(key).get(i) + "\n");
                } else {
                    file.append(users.get(key).get(i) + ";\n}\n");                }
            }
        }
        FileWriter fileWriter = new FileWriter("data.txt");
        fileWriter.write(file.toString());
        fileWriter.close();
    }
}
