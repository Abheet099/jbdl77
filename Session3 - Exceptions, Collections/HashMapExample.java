import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class HashMapExample {

    public static void main(String[] args) {

        // create phone directory
        Map<Long, String> phoneDirectory = new HashMap<>();
        phoneDirectory.put(9875833432L, "Tom");
        phoneDirectory.put(9788333432L, "Jerry");

        for (Map.Entry<Long, String> entry : phoneDirectory.entrySet()) {
            System.out.println(entry);
        }
        phoneDirectory.getOrDefault(98756677L, "Contact not found");
    }
}
