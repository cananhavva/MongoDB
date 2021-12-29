package com.canan;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class CountryManagement {
	private MongoClient client;
	private MongoDatabase database;
	private MongoCollection<Document> collection;
	private List<Country> countries;
	
	public CountryManagement() {
		this.client = new MongoClient("localhost", 27017);
		this.database = client.getDatabase("mongoDB");
		this.collection = database.getCollection("countries");
		this.countries = new ArrayList<>();
	}
	
	public static void main(String[] args) {
		CountryManagement countryManager = new CountryManagement();
		countryManager.init();
		countryManager.insertData();
	}
	
	private void insertData() {
		PojoCodecProvider codecProvider = PojoCodecProvider.builder().automatic(true).build();
		CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
				CodecRegistries.fromProviders(codecProvider));
		
		MongoCollection<Country> countryDocuments = database.withCodecRegistry(pojoCodecRegistry)
				.getCollection("country", Country.class);
		countryDocuments.insertMany(countries);
	}
	
	private void init() {
		City city = City.builder().name("Ankara").cityCode("06").population(5639076).altitude(938).latitude(39.925533)
				.longitude(32.866287).build();
		
		ArrayList<City> cities = new ArrayList<>();
		cities.add(city);
		cities.add(City.builder().name("istanbul").cityCode("34").population(15462452).altitude(40).latitude(41.015137)
				.longitude(28.979530).build());
		cities.add(City.builder().name("izmir").cityCode("35").population(4367251).altitude(2).latitude(38.423733)
				.longitude(27.142826).build());
		cities.add(City.builder().name("Malatya").cityCode("44").population(806156).altitude(977).latitude(38.356869)
				.longitude(38.309669).build());
		
		Country country = Country.builder().capital(city).countryCode("TR").name("Türkiye").population(84_342_846)
				.cities(cities).telCode(90).build();
		this.countries.add(country);
	}
	
}