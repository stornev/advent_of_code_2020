package day_7;

import java.util.ArrayList;

import template.DayX;

public class Day7 extends DayX {

	public Day7(int year_number, int day_number, String session_cookie) {
		super(year_number, day_number, session_cookie);
	}
	
	public static void main(String[] args) {
		Day7 day = new Day7(2020, 7, "53616c7465645f5fa95426beb597d2320309271a121f" +
                "7fdc4c51565c51b85044a2a8d328c7a7441b1e3c3da19685af6908eb4492a84daf51ce1b1ec35328b346");
		
		System.out.println(day.partOne());
		System.out.println(day.partTwo());
	}

	@Override
	public String partOne() {
		ArrayList<String> input_lines = getInputLines();
		ArrayList<String> containing_shiny_gold = new ArrayList<String>();
		
		// find all bags that directly contain shiny gold
		for (int i = 0; i < input_lines.size(); i++) {
			String[] bags = input_lines.get(i).split("contain");
			String name = bags[0].replace("bags", "").trim();
			String contents = bags[1];
			if (contents.contains("shiny gold") && !containing_shiny_gold.contains(name)) {
				containing_shiny_gold.add(name);
			}
		}
		
		// add the bags that contain shiny gold to the list until the sizes
		// don't change anymore (so when there is any left)
		int before = 1;
		int after = 0;
		while (before != after) {
			ArrayList<String> temp = new ArrayList<String>();
			before = containing_shiny_gold.size();
			
			for (int i = 0; i < input_lines.size(); i++) {
				String[] bags = input_lines.get(i).split("contain");
				String name = bags[0].replace("bags", "").trim();
				String contents = bags[1];
				for (String shinybags : containing_shiny_gold) {
					if (contents.contains(shinybags) && 
						!containing_shiny_gold.contains(name) && 
						!temp.contains(name)) 
					{
						temp.add(name);
					}
				}
			}
			containing_shiny_gold.addAll(temp);
			after = containing_shiny_gold.size();
		}
		
		return "" + containing_shiny_gold.size();
	}

	@Override
	public String partTwo() {
		
		return null;
	}
}
