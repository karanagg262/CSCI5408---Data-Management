package com.assignment.Assignment_3;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import scala.Tuple2;

public class WordCounter {

	public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setMaster("local").setAppName("WordCounter");
        JavaSparkContext sparkContext = new JavaSparkContext(sparkConf);
		readspark("Canada.txt", sparkContext);
		readspark("Halifax.txt", sparkContext);
		readspark("hockey.txt", sparkContext);
		readspark("hurricane.txt", sparkContext);
		readspark("electricity.txt", sparkContext);
		readspark("house.txt", sparkContext);
		readspark("inflation.txt", sparkContext);

	}
	
	public static void readspark(String fileName, JavaSparkContext sparkContext ) {
        JavaRDD<String> inputFile = sparkContext.textFile(fileName);	
        JavaRDD<String> wordsFromFile = inputFile.flatMap(content -> Arrays.asList(content.split("[\\\" ,.-?:-]")).iterator());
        countWord(fileName, wordsFromFile);
	}
	
	public static void countWord(String fileName, JavaRDD<String> wordsFromFile) {
        int canadaCount=0, HalifaxCount=0, hockeyCount=0, hurricaneCount=0, electricityCount=0, houseCount=0, inflationCount=0;
        for(String word:wordsFromFile.collect()){
        	if(word.equals("Canada")) {
        		canadaCount++;
        	}  
        	if(word.equals("Halifax")) {
        		HalifaxCount++;
        	}  
        	if(word.equals("hockey")) {
        		hockeyCount++;
        	}  
        	if(word.equals("hurricane")) {
        		hurricaneCount++;
        	}  
        	if(word.equals("electricity")) {
        		electricityCount++;
        	}  
        	if(word.equals("house")) {
        		houseCount++;
        	}  
        	if(word.equals("inflation")) {
        		inflationCount++;
        	}  
        }
        
        System.out.println("Canada: " + canadaCount);
        System.out.println("Halifax: " + HalifaxCount);
        System.out.println("hockey: " + hockeyCount);
        System.out.println("hurricane: " + hurricaneCount);
        System.out.println("electricity: " + electricityCount);
        System.out.println("house: " + houseCount);
        System.out.println("inflation: " + inflationCount);
	}

}
