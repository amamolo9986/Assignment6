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

	public void assignFileNames() throws IOException {

		FileService fileService = new FileService();

		List<SalesReport> model3Data = fileService.readReport(MODEL_3);
		List<SalesReport> modelSData = fileService.readReport(MODEL_S);
		List<SalesReport> modelXData = fileService.readReport(MODEL_X);
		
		formattedSalesReport(model3Data, "Model 3");
		formattedSalesReport(modelSData, "Model S");
		formattedSalesReport(modelXData, "Model X");

	}

	public void formattedSalesReport(List<SalesReport> reports, String modelName) {
		Map<Integer, List<SalesReport>> salesByYear = reports.stream()
															 .collect(Collectors.groupingBy(d -> d.getDate().getYear()));

		String totalYearlySales = salesByYear.entrySet().stream()
											 .map(x -> x.getKey() + " -> " + x.getValue()
											 .stream()
											 .collect(Collectors.summingInt(SalesReport::getSales)))
											 .collect(Collectors.joining("\n"));
		System.out.println(modelName + " yearly sales report" + ":\n" + totalYearlySales + "\n");
		
		Optional<SalesReport> maxSalesData = reports.stream()
				                                    .max((SalesReport o1, SalesReport o2) -> o1.getSales().compareTo(o2.getSales()));
		
		System.out.println("The best month for " + modelName + " was: \n"
				+ maxSalesData.orElse(new SalesReport("Jan-00", "00")).getDate());
		
		Optional<SalesReport> minSalesData = reports.stream()
													.min((SalesReport o1, SalesReport o2) -> o1.getSales().compareTo(o2.getSales()));
		
		System.out.println("The worst month for " + modelName + " was: \n"
				+ minSalesData.orElse(new SalesReport("Jan-00", "00")).getDate() + "\n");
		System.out.println("---------------");
		System.out.println();


	}


}
