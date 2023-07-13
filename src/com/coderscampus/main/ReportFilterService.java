package com.coderscampus.main;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReportFilterService {

	public static final String MODEL_3 = "model3.csv";
	public static final String MODEL_S = "modelS.csv";
	public static final String MODEL_X = "modelX.csv";
	// Assigning variables to the filenames. Final keyword means they're final and
	// can not be changed. Also by convention. when there is a final keyword,
	// variables should be capitalized to distinguish these final keywords.

	public void assignFileNames() throws IOException {

		FileService fileService = new FileService();

		List<SalesReport> model3Data = fileService.readReport(MODEL_3);
		List<SalesReport> modelSData = fileService.readReport(MODEL_S);
		List<SalesReport> modelXData = fileService.readReport(MODEL_X);
		// Creates an instance of FileService and assigns it to fileService,
		// then readReport is called 3 times, passing the filenames stored in our
		// MODEL variables. These MODEL variables are then assigned to Data variables.

		formattedSalesReport(model3Data, "Model 3");
		formattedSalesReport(modelSData, "Model S");
		formattedSalesReport(modelXData, "Model X");

	}

	public void formattedSalesReport(List<SalesReport> reports, String modelName) {
		
		Map<Integer, List<SalesReport>> salesByYear = reports.stream()
															 .collect(Collectors.groupingBy(d -> d.getDate().getYear()));
															// Grouping the sales report by year
															// This is setting up our year and sales K/V map
		

		String totalYearlySales = salesByYear.entrySet() //retrieves entries from salesByYear map
											 .stream().map(x -> x.getKey() + " -> " + x.getValue() //for each entry, we extract the year(key) and reports(value). 
											 .stream()
											 .collect(Collectors.summingInt(SalesReport::getSales))) //calculate the total sales using 'summingInt'
											 .collect(Collectors.joining("\n")); //joins them with a delimiter that separates each sales report by going to the next line
		System.out.println(modelName + " yearly sales report" + ":\n" + totalYearlySales + "\n");
		// Reminder - entry set is the key and value pairs - or an ENTRY. and when we Map the getKey and getValue, 
		// it returns a string, thats why totalYearlySales is of type String. Hover over map to read it.
		// The code takes the map of yearly sales data, iterates over its entries, 
		// calculates the total sales for each year, and creates a formatted string 
		// representation of the yearly sales report.
		
		
		Optional<SalesReport> maxSalesData = reports.stream()
													.max((SalesReport o1, SalesReport o2) -> o1.getSales().compareTo(o2.getSales())); //streaming original reports and comparing sales

		System.out.println("The best month for " + modelName + " was: \n"
								+ maxSalesData.orElse(new SalesReport("Jan-00", "00")).getDate()); 
			//DEFAULT OPTIONAL VALUE - There is no strict recommended format for the default value. The default value should 
		    //be chosen to accurately represent the absence of a meaningful value or to provide a reasonable default value in case of absence.
		// Note - the optional value is recommended when finding the highest value of a collection, there is always a chance
		// that there will be equal values (then none would be greater) or there are no elements in a list to compare.

		
		Optional<SalesReport> minSalesData = reports.stream()
													.min((SalesReport o1, SalesReport o2) -> o1.getSales().compareTo(o2.getSales()));

		System.out.println("The worst month for " + modelName + " was: \n"
				+ minSalesData.orElse(new SalesReport("Jan-00", "00")).getDate() + "\n");
		//This is the same as the explanation above, just using min.
		
		System.out.println("---------------");
		System.out.println();
	}
}
