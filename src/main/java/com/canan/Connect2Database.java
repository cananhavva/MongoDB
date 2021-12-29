package com.canan;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Connect2Database {
	
	public static void main(String[] args) {
		
		try (MongoClient client = new MongoClient("localhost", 27017);) {
			MongoDatabase database = client.getDatabase("mongoDB");
			
			System.out.println("Mongo'ya başlandım");
			
			MongoCollection<Document> firstCollection = database.getCollection("firstCollection");
			
			System.out.println("ilk koleksiyonumu yarattım");
			
			Document document = new Document("title", "--------");
			firstCollection.insertOne(document);
			System.out.println(document.toJson());
			System.out.println("ilk dökümanımı ekledim");
			
			List<Document> myDocuments = new ArrayList<>();
			document = new Document("type", "araba").append("brand", "fiat").append("model", "124");
			System.out.println(document.toString());
			System.out.println(document.toJson());
			myDocuments.add(document);
			
			myDocuments.add(new Document("firstName", "canan").append("surName", "arslan"));
			myDocuments.add(new Document("title", "merhaba"));
			myDocuments.add(
					new Document("title", "Bildim").append("description", "bir gün okula giderken").append("myId", 15));
			// firstCollection.insertMany(myDocuments);
			
			System.out.println("Verileri ekledim");
			
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}