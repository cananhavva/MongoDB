package com.canan;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class RetrieveSystemInformation {
	public static void main(String[] args) {
		/* Total number of processors or cores available to the JVM */
		System.out.println("Available processors (cores): " + Runtime.getRuntime().availableProcessors());
		
		/* Total amount of free memory available to the JVM */
		System.out.println("Free memory (bytes): " + Runtime.getRuntime().freeMemory());
		
		/* This will return Long.MAX_VALUE if there is no preset limit */
		long maxMemory = Runtime.getRuntime().maxMemory();
		/* Maximum amount of memory the JVM will attempt to use */
		System.out.println("Maximum memory (bytes): " + (maxMemory == Long.MAX_VALUE ? "no limit" : maxMemory));
		
		/* Total memory currently available to the JVM */
		System.out.println("Total memory available to JVM (bytes): " + Runtime.getRuntime().totalMemory());
		
		/* Get a list of all filesystem roots on this system */
		File[] roots = File.listRoots();
		
		/* For each filesystem root, print some info */
		for (File root : roots) {
			System.out.printf("File system root: %s\n", root.getAbsolutePath());
			System.out.printf("Total space (bytes): %,d\n", root.getTotalSpace());
			System.out.printf("Free space (bytes): %,d\n", root.getFreeSpace());
			System.out.printf("Usable space (bytes): %,d\n\n", root.getUsableSpace());
		}
		
		String[][] commands = { { "CMD", "/C", "WMIC OS GET Installdate,SerialNumber" },
				{ "CMD", "/C", "WMIC BASEBOARD GET SerialNumber" }, { "CMD", "/C", "WMIC CPU GET ProcessorId" },
				{ "CMD", "/C", "WMIC COMPUTERSYSTEM GET Name" },
				{ "CMD", "/C", "WMIC diskdrive GET Name, Manufacturer, Model, InterfaceType, MediaLoaded, MediaType" },
				{ "CMD", "/C", "WMIC memphysical GET Manufacturer, Model, SerialNumber, MaxCapacity, MemoryDevices" },
				{ "CMD", "/C", "wmic os get osarchitecture" } };
		String data = "";
		try {
			for (String[] command : commands) {
				
				Process process = Runtime.getRuntime().exec(command);
				process.getOutputStream().close();
				// Reading successful output of the command
				BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String s;
				while ((s = reader.readLine()) != null) {
					data += s;
				}
				data += "\n";
			}
			System.out.println(data.trim().replaceAll(" +", "-"));
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
}