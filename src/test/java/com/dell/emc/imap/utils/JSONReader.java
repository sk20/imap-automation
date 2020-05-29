package com.dell.emc.imap.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.parser.ParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONReader {

	public static Map<String, Object> readJSONData(String file) throws FileNotFoundException, IOException, ParseException{
		Map<String, Object> dataMap = new HashMap<String, Object>();
//		String path = System.getProperty("user.dir");
		ObjectMapper mapper = new ObjectMapper();
		try {
             dataMap = mapper.readValue(new File(file), new TypeReference<Map<String, Object>>() {
            });
            
		} catch (Exception e) {
            e.printStackTrace();
        }
		return dataMap;
	}
}
