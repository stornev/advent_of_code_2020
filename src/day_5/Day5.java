package day_5;

import java.util.ArrayList;

import template.DayX;

public class Day5 extends DayX {
	
	public Day5(int year_number, int day_number, String session_cookie) {
		super(year_number, day_number, session_cookie);
	}
	
	public static void main(String[] args) {
		Day5 day = new Day5(2020, 5, "(your own cookie here)" +
                "");
		
		System.out.println(day.partOne());
		System.out.println(day.partTwo());
	}

	@Override
	public String partOne() {
		var input_lines = getInputLines();
		
		int max = Integer.MIN_VALUE;
		for (String line : input_lines) {
			line = line.replaceAll("(B|R)", "1").replaceAll("(F|L)", "0");
			int current = Integer.valueOf(line, 2);
			if (current > max) max = current;
		}
		
		return "" + max;
	}

	@Override
	public String partTwo() {
		var input_lines = getInputLines();
		
		// making the input into binary and then converting to seatId
		ArrayList<Integer> seatIds = new ArrayList<>();
		for (String line : input_lines) {
			line = line.replaceAll("(B|R)", "1").replaceAll("(F|L)", "0");
			seatIds.add(Integer.valueOf(line, 2));
		}
		
		// insertion sort
		for (int i = 1; i < seatIds.size(); i++) {
			int current = seatIds.get(i);
			int j = i - 1;
			while (j > -1 && seatIds.get(j) > current) {
				seatIds.set(j + 1, seatIds.get(j));
				j--;
			}
			seatIds.set(j + 1, current);
		}
		
		// finding the missing duck
		int missing = 0;
		for (int i = 1; i < seatIds.size(); i++) {
			int before = seatIds.get(i - 1);
			int current = seatIds.get(i);
			if (current - 1 != before) {
				missing = current - 1;
				break;
			}
		}
		
		return "" + missing;
	}
}
