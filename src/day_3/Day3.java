package day_3;

import template.DayX;

import java.util.ArrayList;

public class Day3 extends DayX {

    public Day3(int year_number, int day_number, String session_cookie) {
        super(year_number, day_number, session_cookie);
    }

    public static void main(String[] args) {
        // To get session cookie when you're logged in, inspect -> go to network tab -> click on day# -> click on day#
        // in network tab -> scroll down into request headers -> copy & paste the session= cookie into the string below
        Day3 day = new Day3(2020, 3, "(your cookie here)" +
                "");

        System.out.println(day.partOne());
        System.out.println(day.partTwo());
    }

    @Override
    public String partOne() {
        ArrayList<String> input_lines = getInputLines();

        int multiplications_of_start = findMultiplicationFactor(3, 1, input_lines);

        input_lines = multiplyLines(multiplications_of_start, input_lines);

        int trees_hit = countTreesHit(3, 1, input_lines);

        return "" + trees_hit;
    }

    @Override
    public String partTwo() {
        ArrayList<String> starting_input_lines = getInputLines();

        int[][] slopes = {{1, 1}, {3, 1}, {5, 1}, {7, 1}, {1, 2}};
        int[] trees_per_slope = new int[slopes.length];

        ArrayList<String> input_lines = getInputLines();
        for (int i = 0; i < slopes.length; i++) {

            int[] slope = slopes[i];

            int multiplications_of_start = findMultiplicationFactor(slope[0], slope[1], input_lines);

            input_lines = multiplyLines(multiplications_of_start, input_lines);

            int trees_hit = countTreesHit(slope[0], slope[1], input_lines);
            trees_per_slope[i] = trees_hit;

            input_lines = starting_input_lines;
        }

        // calculate answer by multiplying the trees hit on all slopes
        long answer = 1;
        for (int i = 0; i < trees_per_slope.length; i++) {
            answer *= trees_per_slope[i];
        }

        return "" + answer;
    }

    private int findMultiplicationFactor(int slope_run, int slope_rise, ArrayList<String> input_lines) {

        int lines_until_bottom = input_lines.size();
        int max_columns_needed = lines_until_bottom * slope_run * slope_rise;
        int starting_columns = input_lines.get(0).length();

        int multiplications_of_start = (int) Math.ceil((double) max_columns_needed / starting_columns);

        return multiplications_of_start;
    }

    /* multiplies every line to get the desired column count
       this allows for the calculation of number of trees hit */
    private ArrayList<String> multiplyLines(int multiplications_of_start, ArrayList<String> input_lines) {

        for (int i = 0; i < input_lines.size(); i++) {
            String line = input_lines.get(i);

            StringBuilder line_builder = new StringBuilder();
            for (int m = 0; m < multiplications_of_start; m++) {
                line_builder.append(line);
            }

            input_lines.set(i, line_builder.toString());
        }

        return input_lines;
    }

    private int countTreesHit(int slope_run, int slope_rise, ArrayList<String> input_lines) {

        // counts the number of trees hit on every line (1 max per)
        int index_overall = slope_run;
        int trees_hit = 0;
        for (int i = slope_rise; i < input_lines.size(); i += slope_rise) {
            String line = input_lines.get(i);
            String position = line.substring(index_overall, index_overall + 1);

            if (position.equals("#")) {
                trees_hit++;
            }
            // simulate going down the slope
            index_overall += slope_run;
        }

        return trees_hit;
    }
}
