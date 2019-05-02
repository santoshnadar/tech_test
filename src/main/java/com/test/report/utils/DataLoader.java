package com.test.report.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.test.basic.Currency;
import com.test.basic.DataDTO;
import com.test.basic.Flag;
import com.test.report.Report;

public class DataLoader {

	/**
	 * Load data from data.csv
	 */
	public List<DataDTO> loadData() {
		boolean firstRowSkipped = false;
		List<DataDTO> dataList = new ArrayList<DataDTO>();
		InputStream inputStream = Report.class.getClassLoader().getResourceAsStream("data.csv");
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line = br.readLine();

			while (line != null) {
				if (!firstRowSkipped) {
					firstRowSkipped = true;
					line = br.readLine();
					continue;
				}

				String[] attributes = line.split(",");
				DataDTO data = new DataDTO();
				data.setEntity(attributes[0].toString());
				data.setFlag(Flag.valueOf(attributes[1].toString()));
				data.setAgreedFx(new BigDecimal(attributes[2].toString()));
				data.setCurrency(Currency.valueOf(attributes[3].toString()));
				data.setInstructionDate(UtilHelper.DDMMMYYYY.parse(attributes[4].toString()));
				data.setSettlementDate(UtilHelper.DDMMMYYYY.parse(attributes[5].toString()));
				data.setUnits(Integer.valueOf(attributes[6].toString()));
				data.setPricePerUnit(new BigDecimal(attributes[7].toString()));
				dataList.add(data);

				line = br.readLine();
			}
		} catch (IOException | ParseException ioe) {
			ioe.printStackTrace();
		}
		return dataList;
	}
}
