package automation.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	public List<HashMap<String,String>> getJsonDataToMap() throws IOException {
		//read json to string
		String jsconContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "\\src\\test\\java\\automation\\data\\PurchaseOrder.json"), 
				StandardCharsets.UTF_8);
		
		
		//String to hashmap Jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>>data = mapper.readValue(jsconContent, new TypeReference<List<HashMap<String, String>>>(){});
		
		//returns the data in List format
		return data;
	}
}
