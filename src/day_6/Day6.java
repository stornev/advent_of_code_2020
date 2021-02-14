package day_6;

import java.util.ArrayList;

import template.DayX;

public class Day6 extends DayX {

	public Day6(int year_number, int day_number, String session_cookie) {
		super(year_number, day_number, session_cookie);
	}

	public static void main(String[] args) {
		Day6 day = new Day6(2020, 6, "(your own cookie here)" +
                "");
		
		System.out.println(day.partOne());
		System.out.println(day.partTwo());
	}
	
	@Override
	public String partOne() {
		ArrayList<String> input_lines = getInputLines();
		input_lines.add("");
		
		ArrayList<String> groups = new ArrayList<>();
		String persons = "";
		
		for (String line : input_lines) {
			
			if (line.isBlank()) {
				persons = persons.trim();
				groups.add(persons);
				persons = "";
			} else {
				persons += line + " ";
			}
		}
		
		ArrayList<String> yes_questions = new ArrayList<>();
		ArrayList<Integer> sums = new ArrayList<>();
		
		for (String group : groups) {
			for (int i = 0; i < group.length(); i++) {
				String letter = group.substring(i, i + 1);
				if (!letter.equals(" ") && !yes_questions.contains(letter)) {
					yes_questions.add(letter);
				}
			}
			
			sums.add(yes_questions.size());
			yes_questions.clear();
		}
		
		int total = 0;
		for (int sum : sums) {
			total += sum;
		}
		
		return "" + total;
	}

	@Override
	public String partTwo() {
		ArrayList<String> input_lines = getInputLines();
		input_lines.add("");
		
		ArrayList<String> groups = new ArrayList<>();
		String persons = "";
		
		for (String line : input_lines) {
			
			if (line.isBlank()) {
				groups.add(persons);
				persons = "";
			} else {
				persons += line + " ";
			}
		}
		
		// counts the total number of questions for all groups that everyone answered yes to
		int total = 0;
		for (int i = 0; i < groups.size(); i++) {
			String group = groups.get(i);
			String[] people = group.split(" ");
			ArrayList<String> all_yes = new ArrayList<>();
			
			// adds all questions to list if not already in list
			for (int j = 0; j < people.length; j++) {
				String person = people[j];
				for (int t = 0; t < person.length(); t++) {
					String letter = person.substring(t, t+1);
					if (!all_yes.contains(letter)) {
						all_yes.add(letter);
					}
				}
			}
			
			// removes the ones that aren't in all people
			for (int j = 0; j < people.length; j++) {
				String person = people[j];
				for (int t = 0; t < all_yes.size(); t++) {
					String question = all_yes.get(t);
					if (!person.contains(question)) {
						all_yes.remove(question);
						t--;
					}
				}
			}
			total += all_yes.size();	// all_yes.size() is equal to # of q's answered yes to
		}
		
		return "" + total;
	}
}
