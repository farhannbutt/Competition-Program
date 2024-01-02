import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CompetitorList {
    public static ArrayList<Competitor> readCompetitorsFromCSV(String filePath) {
        ArrayList<Competitor> competitors = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int competitorNumber = Integer.parseInt(data[0]);
                String firstName = data[1];
                String lastName = data[2];
                String email = data[3];
                String dateOfBirth = data[4];
                String category = data[5];
                String level = data[6];
                int[] scores = {
                        Integer.parseInt(data[7].trim()),
                        Integer.parseInt(data[8].trim()),
                        Integer.parseInt(data[9].trim()),
                        Integer.parseInt(data[10].trim()),
                        Integer.parseInt(data[11].trim())
                };

                Competitor competitor = new Competitor(competitorNumber, firstName, lastName, email,
                        dateOfBirth, category, level, scores);

                competitors.add(competitor);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return competitors;
    }

    public static void printCompetitors(ArrayList<Competitor> competitors) {
        printHeading();
        // Print each competitor's information
        for (Competitor competitor : competitors) {
            System.out.printf("%-15d %-15s %-15s %-25s %-12s %-20s %-10s %-15s%n",
                    competitor.getCompetitorNumber(), competitor.getFirstName(),
                    competitor.getLastName(), competitor.getEmail(), competitor.getDateOfBirth(),
                    competitor.getCategory(), competitor.getLevel(), Arrays.toString(competitor.getScores()));
        }
    }

    public static void printHeading() {
        // Print table header
        System.out.printf("%-15s %-15s %-15s %-25s %-12s %-20s %-10s %-15s%n",
                "Competitor #", "First Name", "Last Name", "Email", "DOB", "Category", "Level", "Scores");
    }
}
