package day_4;

import template.DayX;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Day4 extends DayX{

    public Day4(int year_number, int day_number, String session_cookie) {
        super(year_number, day_number, session_cookie);
    }

    public static void main(String[] args) {
        // To get session cookie when you're logged in, inspect -> go to network tab -> click on day# -> click on day#
        // in network tab -> scroll down into request headers -> copy & paste the session= cookie into the string below
        Day4 day = new Day4(2020, 4, "(your cookie here)" +
                "");

        System.out.println(day.partOne());
        System.out.println(day.partTwo());
    }

    @Override
    public String partOne() { return "" + run(false); }

    @Override
    public String partTwo() { return "" + run(true); }

    private int run(boolean check_conditions) {
        ArrayList<String> input_lines = getInputLines();
        // needed so, it can count the last passport
        input_lines.add("");

        ArrayList<ArrayList<String>> passports = new ArrayList<>();
        ArrayList<String> passport_details = new ArrayList<>();

        // separates all the passports into a 2D arraylist
        for (String line : input_lines) {
            if (!line.isBlank()) {
                String[] details = line.split(" ");
                passport_details.addAll(Arrays.asList(details));
            } else {
                passports.add(passport_details);
                passport_details = new ArrayList<>();
            }
        }

        // all fields of passport excluding "cid" because it was not in the constraints of the problem
        ArrayList<String> fields = new ArrayList<>(Arrays.asList("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"));

        // making a template hashmap for the hashmap below
        Map<String, Boolean> template_map = new HashMap<>();
        for (String field : fields) template_map.put(field, false);

        int passports_valid = 0;
        for (int i = 0; i < passports.size(); i++) {
            ArrayList<String> current_passport = passports.get(i);
            // to count every field and their presence using booleans
            HashMap<String, Boolean> valid_per_passport = new HashMap<>(template_map);

            // checks every detail of the passport and puts in hashmap
            for (int x = 0; x < current_passport.size(); x++) {
                String current_detail = current_passport.get(x);
                String detail_name = current_detail.substring(0, current_detail.indexOf(":"));
                String detail = current_detail.substring(current_detail.indexOf(":") + 1).trim();

                if (valid_per_passport.containsKey(detail_name)) {
                    valid_per_passport.replace(detail_name, true);
                }

                if (check_conditions) {
                    switch(detail_name) {
                        case "byr":
                            valid_per_passport.replace(detail_name, checkBYR(detail)); break;
                        case "iyr":
                            valid_per_passport.replace(detail_name, checkIYR(detail)); break;
                        case "eyr":
                            valid_per_passport.replace(detail_name, checkEYR(detail)); break;
                        case "hgt":
                            valid_per_passport.replace(detail_name, checkHGT(detail)); break;
                        case "hcl":
                            valid_per_passport.replace(detail_name, checkHCL(detail)); break;
                        case "ecl":
                            valid_per_passport.replace(detail_name, checkECL(detail)); break;
                        case "pid":
                            valid_per_passport.replace(detail_name, checkPID(detail)); break;
                        default:
                            break;
                    }
                }
            }

            // checks how many fields are valid
            int fields_valid = 0;
            for (int x = 0; x < valid_per_passport.size(); x++) {
                if (valid_per_passport.get(fields.get(x))) {
                    fields_valid++;
                }
            }

            // increments the variable to count valid passports
            if (fields_valid == 7) {
                passports_valid++;
            }
        }

        return passports_valid;
    }

    private boolean checkBYR(String detail) {
        return detail.matches("(19[2-9][0-9]|200[0-2])");
    }

    private boolean checkIYR(String detail) {
        return detail.matches("(201[0-9]|2020)");
    }

    private boolean checkEYR(String detail) {
        return detail.matches("(202[0-9]|2030)");
    }

    private boolean checkHGT(String detail) {
        return detail.matches("(1[5-9][0-9]|19[0-3])cm") || detail.matches("(59|6[0-9]|7[0-6])in");
    }

    private boolean checkHCL(String detail) {
        return detail.matches("#[0-9a-f]{6}");
    }

    private boolean checkECL(String detail) {
        return detail.matches("(amb|blu|brn|gry|grn|hzl|oth)");
    }

    private boolean checkPID(String detail) {
        return detail.matches("[0-9]{9}");
    }
}
