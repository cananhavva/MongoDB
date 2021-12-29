package com.canan;

import org.bson.codecs.pojo.annotations.BsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class City {
	@BsonProperty(value = "cityname")
	private String name;
	@BsonProperty(value = "latitude")
	private double latitude;
	@BsonProperty(value = "longitude")
	private double longitude;
	@BsonProperty(value = "altitude")
	private double altitude;
	@BsonProperty(value = "cityCode")
	private String cityCode;
	@BsonProperty(value = "citypopulation")
	private long population;
}