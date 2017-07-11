import com.google.gson.Gson;
import data.ArrayTypeObject;
import data.InnerForTesting;
import data.Simple;
import org.junit.Test;
import ru.aryukov.json.JsonWriter;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by oaryukov on 04.07.2017.
 */
public class TestJsonWrite {
    @Test
    public void primitiveToJson() throws IOException, IllegalAccessException {
        Simple simple = new Simple();
        Gson mapper = new Gson();
        String expectedJsonStr = mapper.toJson(simple);

        JsonWriter writer = new JsonWriter();
        String testStr = writer.convertToJason(simple);
        System.out.println(expectedJsonStr);
        System.out.println(testStr);

        assertEquals("simple", expectedJsonStr, testStr);
    }

    @Test
    public void arrayToJson() throws IllegalAccessException {
        ArrayTypeObject arrayTypeObject = new ArrayTypeObject();
        Gson mapper = new Gson();
        String expectedJsonStr = mapper.toJson(arrayTypeObject);
        JsonWriter writer = new JsonWriter();
        String testStr = writer.convertToJason(arrayTypeObject);
        System.out.println(expectedJsonStr);
        System.out.println(testStr);

        assertEquals("array", expectedJsonStr, testStr);
    }

    @Test
    public void innerToJson() throws IllegalAccessException {
        InnerForTesting obj = new InnerForTesting();
        Gson mapper = new Gson();
        String expectedJsonStr = mapper.toJson(obj);

        JsonWriter writer = new JsonWriter();
        String testStr = writer.convertToJason(obj);
        System.out.println(expectedJsonStr);
        System.out.println(testStr);

        assertEquals("inner", expectedJsonStr, testStr);
    }
}
