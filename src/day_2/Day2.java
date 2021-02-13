package day_2;

import template.DayX;

import java.util.ArrayList;

public class Day2 extends DayX {

    public Day2(int year_number, int day_number, String session_cookie) {
        super(year_number, day_number, session_cookie);
    }

    public static void main(String[] args) {
        Day2 day = new Day2(2020, 2, "53616c7465645f5f6f3daa0de4ff954b137ec76bb0" +
                "695216a07ebb94c00217b35a0bb4f776069b8b0957988f7c6074ff");

        System.out.println(day.partOne());
        System.out.println(day.partTwo());
    }

    @Override
    public String partOne() {
        ArrayList<String> input_lines = getInputLines();

        ArrayList<String> rules = new ArrayList<>();
        ArrayList<String> passwords = new ArrayList<>();

        // separating the rules and passwords in the input strings
        for (int i = 0; i < input_lines.size(); i++) {
            String[] temp = input_lines.get(i).split(":");
            rules.add(temp[0].trim());
            passwords.add(temp[1].trim());
        }

        // does all operations needed to calculate amount of passwords that are valid
        int amount_valid = 0;
        for (int i = 0; i < rules.size(); i++) {
            String rule = rules.get(i);
            String rule_letter = rule
                    .replaceAll("[\\d\\W]", "")
                    .trim();

            // how many times the rule letter occurred in the password
            int occurrences = 0;
            String password = passwords.get(i);
            for (int index = 0; index < password.length(); index++) {
                String current_letter = password.substring(index, index + 1);

                if (current_letter.equals(rule_letter)) {
                    occurrences++;
                }
            }

            // the range of the rule
            int at_least = Integer.parseInt(rule.substring(0, rule.indexOf("-")));
            int no_more_than = Integer.parseInt(rule.substring(rule.indexOf("-") + 1, rule.indexOf(" ")));

            // for it to be valid it needs to have occurred x times between the range of the rule
            if (occurrences >= at_least && occurrences <= no_more_than) {
                amount_valid++;
            }
        }

        return "" + amount_valid;
    }

    @Override
    public String partTwo() {
        ArrayList<String> input_lines = getInputLines();

        ArrayList<String> rules = new ArrayList<>();
        ArrayList<String> passwords = new ArrayList<>();

        for (int i = 0; i < input_lines.size(); i++) {
            String[] temp = input_lines.get(i).split(":");
            rules.add(temp[0].trim());
            passwords.add(temp[1].trim());
        }


        int amount_valid = 0;
        for (int i = 0; i < rules.size(); i++) {
            String rule = rules.get(i);
            String rule_letter = rule
                    .replaceAll("[\\d\\W]", "")
                    .trim();

            String password = passwords.get(i);
            // there is no index zero so have to account for that with a (-1)
            int position_one = Integer.parseInt(rule.substring(0, rule.indexOf("-"))) - 1;
            int position_two = Integer.parseInt(rule.substring(rule.indexOf("-") + 1, rule.indexOf(" "))) - 1;

            boolean position_one_valid = password.substring(position_one, position_one + 1).equals(rule_letter);
            boolean position_two_valid = password.substring(position_two, position_two + 1).equals(rule_letter);

            // the position one or two has to have the rule letter
            if ((position_one_valid == true && position_two_valid == false) ||
                (position_one_valid == false && position_two_valid == true))
            {
                amount_valid++;
            }
        }

        return "" + amount_valid;
    }
}
