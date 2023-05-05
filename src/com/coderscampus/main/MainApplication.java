package com.coderscampus.main;

import java.io.IOException;

public class MainApplication {

	public static void main(String[] args) throws IOException { 
		
		ReportFilterService reportFilterService = new ReportFilterService();
		reportFilterService.assignFileNames();
	}

}
