package com.assignment.Assignment_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class NewsExtraction {

	public static void main(String[] args) throws IOException, ParseException {
		getDataForString("Canada", "Canada.txt");
		getDataForString("Halifax", "Halifax.txt");
		getDataForString("hockey", "hockey.txt");
		getDataForString("hurricane", "hurricane.txt");
		getDataForString("electricity", "electricity.txt");
		getDataForString("house", "house.txt");
		getDataForString("inflation", "inflation.txt");
	}
	
	public static void getDataForString(String text, String FileName)  throws IOException, ParseException {
		String apiUrl = "https://newsapi.org/v2/everything?q=" + text + "&apiKey=4082f0837df84c9bba5991e215dd6c97";
		URL url = new URL(apiUrl);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		
		BufferedReader in = new BufferedReader(
				  new InputStreamReader(con.getInputStream()));
		
	    StringBuilder sb = new StringBuilder();
	    JSONParser parser = new JSONParser(); 

		String inputLine;
		
		while ((inputLine = in.readLine()) != null) {
			sb.append(inputLine);
		}
		
		in.close();
		
		JSONObject json = (JSONObject) parser.parse(sb.toString());
		
		//JSONArray ja_data = (JSONArray) json.get("articles");
		System.out.println(json);
	
		BufferedWriter out = new BufferedWriter(new FileWriter(FileName));
		out.write(json.toString());
		out.flush();
		out.close();
		
		con.disconnect();
				
	}
}
