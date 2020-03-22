package Utils;

import Entities.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import lombok.SneakyThrows;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
public class UserInitializer {

    private static FileReader reader;
    public static List<User> usersList;
    public static List<Integer> usersId;
    private static final String USERS_JSON = "src/users.json";

    static {
        readFile();
        parseFile();
    }

    @SneakyThrows(FileNotFoundException.class)
    private static void readFile() {
        File file = new File(USERS_JSON);
        reader = new FileReader(file);
    }

    private static void parseFile() {
        Gson gson = new Gson();
        usersList = gson.fromJson(reader, new TypeToken<List<User>>() {
        }.getType());
    }

    public static List<Integer> getUsersId() {
        return usersList.stream().map(User::getId).collect(Collectors.toCollection(ArrayList::new));
    }
}

















   /* static {
        {
            try {
                reader = new FileReader("C:\\Users\\igord\\IdeaProjects\\ControlPay\\src\\main\\resources\\users.json");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Gson gson = new Gson();
        list = gson.fromJson(reader, new TypeToken<List<User>>(){}.getType());
    }

    public static List<User> getUserList()
    {
        return list;
    }*/


