package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataProviders {
    @DataProvider(name = "JsonData")
    public Object[][] getJsonFileData() throws IOException {
        File file = new File("./src/test/resources/product.json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(file);

        List<Object[]> data = new ArrayList<>();

        for (JsonNode node : rootNode) {
            // Convert JsonNode to Map<String, String>
            Map<String, String> map = mapper.convertValue(node, new TypeReference<Map<String, String>>() {});
            data.add(new Object[]{map});
        }

        return data.toArray(new Object[0][]);
    }
}
