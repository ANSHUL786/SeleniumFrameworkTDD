package anshFramework.testsComponent;

import java.util.List;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class Readdatafromjson {

	public List<HashMap<String, String>> getJSONData(String path) throws IOException {

		// read from file
		//String path = System.getProperty("user.dir") + "/src/test/java/anshFramework/dataFiles/data.json";
		String jsonData = FileUtils.readFileToString(new File(path));

		// convert to Hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonData,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}
}
