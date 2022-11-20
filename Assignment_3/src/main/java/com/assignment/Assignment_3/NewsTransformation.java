package com.assignment.Assignment_3;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.Scanner;

import org.bson.Document;
import org.json.simple.parser.ParseException;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase; 
import com.mongodb.MongoClient; 

public class NewsTransformation {

	public static void main(String[] args) throws ParseException {
	    try {
	    	
	    	readFile("Canada.txt");
	    	readFile("electricity.txt");
	    	readFile("Halifax.txt");
	    	readFile("hockey.txt");
	    	readFile("house.txt");
	    	readFile("hurricane.txt");
	    	readFile("inflation.txt");
	        
	      } catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }

	}
	
	public static void readFile(String fileName) throws FileNotFoundException {
        File myObj = new File(fileName);
        Scanner myReader = new Scanner(myObj);
        String data = myReader.nextLine();
        cleanFile(data, fileName);
        myReader.close();
	}
	
	public static void cleanFile(String data, String fileName) {
        data = data.replaceAll("\"url\":.*?\",", "");
        data = data.replace("\\\"", "");
        data = data.replaceAll("[^a-zA-Z0-9.\"{}:, \\[\\]]", "");
        MongoDB(data, fileName);
	}
	
	public static void MongoDB(String data, String fileName) {
        MongoClient mongo = new MongoClient( "localhost" , 27017 ); 
        MongoDatabase database = mongo.getDatabase("BigMongoNews"); 
        if(fileName.equals("Canada.txt")) {
            MongoCollection<Document> canadaCollection = database.getCollection("Canada");
            System.out.println(data);
    	    canadaCollection.insertOne(Document.parse(data));
        }
        if(fileName.equals("electricity.txt")) {
            MongoCollection<Document> electricityCollection = database.getCollection("electricity");
            System.out.println(data);
    	    electricityCollection.insertOne(Document.parse(data));      	
        }
        if(fileName.equals("Halifax.txt")) {
            MongoCollection<Document> halifaxCollection = database.getCollection("Halifax");
            System.out.println(data);
    	    halifaxCollection.insertOne(Document.parse(data));
        }
        if(fileName.equals("hockey.txt")) {
            MongoCollection<Document> hockeyCollection = database.getCollection("hockey");
            System.out.println(data);
    	    hockeyCollection.insertOne(Document.parse(data));
        }
        if(fileName.equals("house.txt")) {
            MongoCollection<Document> houseCollection = database.getCollection("house");
            System.out.println(data);
    	    houseCollection.insertOne(Document.parse(data));
        }
        if(fileName.equals("hurricane.txt")) {
            MongoCollection<Document> hurricaneCollection = database.getCollection("hurricane");
            System.out.println(data);
    	    hurricaneCollection.insertOne(Document.parse(data));
        }
        if(fileName.equals("inflation.txt")) {
            MongoCollection<Document> inflationCollection = database.getCollection("inflation");
            System.out.println(data);
    	    inflationCollection.insertOne(Document.parse(data));
        }
	}


}
