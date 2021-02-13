package template;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;

@SuppressWarnings("unused")
public abstract class DayX {

    private ArrayList<String> input_lines = new ArrayList<>();
	private String session_cookie;
    private int year_number;
    private int day_number;

    public DayX(int year_number, int day_number, String session_cookie) {
        this.year_number = year_number;
        this.day_number = day_number;
        this.session_cookie = session_cookie;

        try {
            Document doc = Jsoup.connect("https://adventofcode.com/" + year_number + "/day/" + day_number + "/input")
                    .cookie("session", session_cookie)
                    .get();

            String[] input = doc.body().wholeText().split("\n");

            for (int i = 0; i < input.length; i++) {
                input_lines.add(input[i]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getInputLines() {
        return input_lines;
    }

    public void setInputLines(ArrayList<String> input_lines) {
        this.input_lines = input_lines;
    }

    public abstract String partOne();

    public abstract String partTwo();
}
