package day_1;

import template.DayX;

import java.util.ArrayList;

public class Day1 extends DayX {

    public Day1(int year_number, int day_number, String session_cookie) {
        super(year_number, day_number, session_cookie);
    }

    public static void main(String[] args) {
        // To get session cookie when you're logged in, inspect -> go to network tab -> click on day# -> click on day#
        // in network tab -> scroll down into request headers -> copy & paste the session= cookie into the string below
        Day1 day = new Day1(2020, 1, "(your cookie here)" +
                "");

        System.out.println(day.partOne());
        System.out.println(day.partTwo());
    }

    @Override
    public String partOne() {
        ArrayList<String> input = getInputLines();

        ArrayList<Integer> inputInts = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            inputInts.add(Integer.parseInt(input.get(i)));
        }

        ArrayList<String> output = new ArrayList<>();
        ArrayList<Integer> dupe = new ArrayList<>();
        for (int i = 0; i < inputInts.size() - 1; i++) {
            for (int x = 1; x < inputInts.size(); x++) {
                if (inputInts.get(i) + inputInts.get(x) == 2020 &&
                    dupe.indexOf(inputInts.get(i) * inputInts.get(x)) == -1)
                {
                    int value_one = inputInts.get(i);
                    int value_two = inputInts.get(x);
                    int product = value_one * value_two;
                    dupe.add(product);

                    output.add(value_one + " + " + value_two + " = 2020");
                    output.add(value_one + " * " + value_two + " = " + product);
                }
            }
        }

        return output.toString();
    }

    @Override
    public String partTwo() {
        ArrayList<String> input = getInputLines();

        ArrayList<Integer> inputInts = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            inputInts.add(Integer.parseInt(input.get(i)));
        }

        ArrayList<String> output = new ArrayList<>();
        ArrayList<Integer> dupe = new ArrayList<>();

        for (int one = 0; one < inputInts.size(); one++) {
            for (int two = 1; two < inputInts.size(); two++) {
                for (int three = 2; three < inputInts.size(); three++) {
                    if (inputInts.get(one) + inputInts.get(two) + inputInts.get(three) == 2020)
                    {
                        int first = inputInts.get(one);
                        int second = inputInts.get(two);
                        int third = inputInts.get(three);

                        int sum = first + second + third;
                        int product = first * second * third;

                        if (dupe.contains(product)) {
                            continue;

                        } else {
                            dupe.add(product);

                            output.add(first + " + " + second + " + " + third + " = " + sum);
                            output.add(first + " * " + second + " * " + third + " = " + product);
                        }
                    }
                }
            }
        }

        return output.toString();
    }
}
