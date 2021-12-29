package com.canan;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class MongoDBIntroduction {
	MongoClient client;
	MongoDatabase database;
	MongoCollection<Document> collection;
	
	public MongoDBIntroduction() {
		this.client = null;
		this.database = null;
		this.collection = null;
	}
	
	public static void main(String[] args) {
		MongoDBIntroduction intro = new MongoDBIntroduction();
		intro.init();
		intro.addSingleDocument();
		intro.addMultipleDocuments();
		intro.deleteOneDocument();
		intro.deleteMultipleDocuments();
		intro.updateDocuments();
		intro.listDocuments();
	}
	
	private void updateDocuments() {
		this.collection.updateMany(Filters.eq("title", "hello"),
				new Document("$set", new Document("title", "Bilemedim")));
		this.collection.updateMany(Filters.eq("title", "Bilemedim"), Updates.set("firstName", "Cafer"));
		this.collection.updateMany(Filters.eq("title", "--------"), Updates.set("middleName", "Cafer"));
		System.out.println("bir sürü ver, güncelledim");
	}
	
	private void listDocuments() {
		FindIterable<Document> documents = this.collection.find();
		MongoCursor<Document> document = documents.iterator();
		while (document.hasNext()) {
			System.out.println(document.next().toJson());
		}
	}
	
	private void deleteOneDocument() {
		this.collection.deleteOne(Filters.eq("title", "hi"));
		System.out.println("tek bir veri sildim");
	}
	
	private void deleteMultipleDocuments() {
		this.collection.deleteMany(Filters.eq("type", "araba"));
		System.out.println("bir sürü veri sildim");
	}
	
	private void addMultipleDocuments() {
		List<Document> myDocuments = new ArrayList<>();
		Document document = new Document("type", "araba").append("brand", "fiat").append("model", "124");
		System.out.println(document.toString());
		System.out.println(document.toJson());
		myDocuments.add(document);
		
		myDocuments.add(new Document("firstName", "Canan").append("surName", "Arslan"));
		myDocuments.add(new Document("title", "Bilemedim"));
		myDocuments.add(
				new Document("title", "Bildim").append("description", "bir gün okula giderken").append("myId", 15));
		this.collection.insertMany(myDocuments);
		System.out.println("Verileri ekledim");
	}
	
	private void addSingleDocument() {
		Document document = new Document("title", "--------");
		this.collection.insertOne(document);
		System.out.println(document.toJson());
		System.out.println("ilk dokümanımı ekledim");
	}
	
	private void init() {
		this.client = new MongoClient("localhost", 27017);
		this.database = client.getDatabase("mongoDB");
		
		System.out.println("Mongo'ya başladım");
		
		this.collection = database.getCollection("trials");
		
		System.out.println("ilk koleksiyonumu yarattım");
	}
	
}