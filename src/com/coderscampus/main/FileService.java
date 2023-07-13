package com.coderscampus.main;

import java.util.List;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;

public class FileService {

	public List<SalesReport> readReport(String fileName) throws IOException {

		List<SalesReport> storeReport = new ArrayList<>();
		
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(fileName));
				String line = reader.readLine();
				
				while ((line = reader.readLine()) != null) {
					String[] data = line.split(",");
					String date = data[0];
					String sales = data[1];

					storeReport.add(new SalesReport(date, sales));

				}

			} finally {
				if (reader != null) {
					reader.close();
				}
			}

		return storeReport;

	}

}
