package utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.UUID;

/**
 * Created by Hades on 21/02/17.
 */
public class Utils {

    public static Boolean isDev = true;

    public static Boolean isDev(String env) {
        return env == null || env.equalsIgnoreCase("test");
    }

    public static String getUniqueRandomId() {
        return UUID.randomUUID().toString();
    }

    public static String getMessageJSONFromSNSNotification(String snsMessageJson) {
        JsonElement snsJsonElement = new JsonParser().parse(snsMessageJson);
        JsonObject snsJsonObject = snsJsonElement.getAsJsonObject();
        JsonElement snsJsonMessage = snsJsonObject.get("Message");
        JsonElement jsonMessage = new JsonParser().parse(snsJsonMessage.getAsString());
        return jsonMessage.toString();
    }
}
