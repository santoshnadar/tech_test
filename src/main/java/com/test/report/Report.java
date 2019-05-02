package com.test.report;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.test.basic.DataDTO;
import com.test.basic.Flag;
import com.test.report.utils.DataLoader;
import com.test.report.utils.UtilHelper;

public class Report {
	private static String TABLE_FORMAT = "|%1$-8s|%2$-8s|%3$-8s|%4$-8s|%5$-15s|%6$-18s|%7$-18s|%8$-6s|%9$-15s|%10$-14s|%11$-10s|%12$-18s|\n";

	public static void main(String[] args) throws IOException {

		// Load data
		List<DataDTO> dataList = new DataLoader().loadData();

		// Update settlement date and USD amount.
		updateSettlementDateAndUSDAmount(dataList);

		// Ranking outgoing amount
		updateRank(dataList, Flag.B);
		// Ranking incoming amount
		updateRank(dataList, Flag.S);

		// Print output
		System.out.format(TABLE_FORMAT, "Entity", "Buy/Sell", "AgreedFx", "Currency", "InstructionDate", "Old SettlementDate", "New SettlementDate", "Units", "Price per unit", "Amount in USD", "In/Out", "Rank");
		for (DataDTO data : dataList) {
			System.out.format(TABLE_FORMAT, data.getEntity(), data.getFlag(), data.getAgreedFx(), data.getCurrency(), UtilHelper.DDMMMYYYY.format(data.getInstructionDate()), UtilHelper.DDMMMYYYY.format(data.getSettlementDate()),
					data.getNewSettlementDate() != null ? UtilHelper.DDMMMYYYY.format(data.getNewSettlementDate()) : "-NA-", data.getUnits(), data.getPricePerUnit(), data.getUsdAmout(), data.getFlag().getTypeDesc(), data.getRank());
		}
	}

	/**
	 * Update settlement date if it falls in weekend and calculate USD amount
	 * from Units, PricePerUnit & AgreedFx.
	 */
	private static void updateSettlementDateAndUSDAmount(List<DataDTO> dataList) {
		for (DataDTO data : dataList) {

			String day = UtilHelper.EE.format(data.getSettlementDate());
			// Check if the settlement date is in weekend.
			if (data.getCurrency().getWeekEndDays().contains(day)) {
				data.setNewSettlementDate(UtilHelper.getNextWorkingDay(data.getSettlementDate(), data.getCurrency()));
			}

			// USD amount of a trade = Price per unit * Units * Agreed Fx
			BigDecimal usdAmoutTrade = data.getPricePerUnit().multiply(BigDecimal.valueOf(data.getUnits())).multiply(data.getAgreedFx());
			data.setUsdAmout(usdAmoutTrade);

		}
	}

	/**
	 * Rank USD amount only for data with given flag filter.
	 */
	private static void updateRank(List<DataDTO> dataList, Flag flag) {
		int rank = 1;
		List<DataDTO> updatedList = dataList.stream().filter(d -> flag.equals(d.getFlag())).sorted(Comparator.comparing(DataDTO::getUsdAmout).reversed()).collect(Collectors.toCollection(ArrayList::new));
		for (DataDTO data : updatedList) {
			data.setRank(data.getFlag().getTypeDesc() + " Rank " + rank++);
		}
	}
}
