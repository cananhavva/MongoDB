package com.canan;

import java.util.ArrayList;

import org.bson.codecs.pojo.annotations.BsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Country {
	@BsonProperty(value = "countryname")
	private String name;
	@BsonProperty(value = "capital")
	private City capital;
	@BsonProperty(value = "telcode")
	private int telCode;
	@BsonProperty(value = "countrycode")
	private String countryCode;
	@BsonProperty(value = "cities")
	private ArrayList<City> cities;
	@BsonProperty(value = "countrypopulation")
	private long population;
}