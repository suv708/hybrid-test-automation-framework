package utils;
import java.util.HashMap;
import java.util.Map;

public class DataParser {

    public static Map<String, String> parseData(String data) {

        Map<String, String> map = new HashMap<>();

        if (data == null || data.trim().isEmpty()) {
            return map;
        }

        // Example: username=abc,password=123
        String[] pairs = data.split(",");

        for (String pair : pairs) {

            String[] keyValue = pair.split("=");

            if (keyValue.length == 2) {
                String key = keyValue[0].trim().toLowerCase();
                String value = keyValue[1].trim();
                map.put(key, value);
            }
        }

        return map;
    }
}