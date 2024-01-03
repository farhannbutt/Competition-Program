import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CompetitorList {
    private ArrayList<Competitor> competitors;

    public CompetitorList(ArrayList<Competitor> competitors) {
        this.competitors = competitors;
    }

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

    public void printAdditionalInfo() {
        System.out.println("\nAdditional Information:");

        printTotals();
        printAverages();
        printMaxMin();
        printFrequencyReport();
    }

    private void printTotals() {
        System.out.println("Total Scores:");

        int[] totalScores = new int[5];
        for (Competitor competitor : competitors) {
            int[] scores = competitor.getScores();
            for (int i = 0; i < scores.length; i++) {
                totalScores[i] += scores[i];
            }
        }

        System.out.println(Arrays.toString(totalScores));
    }

    private void printAverages() {
        System.out.println("\nAverage Scores:");

        int[] totalScores = new int[5];
        int totalCompetitors = competitors.size();

        for (Competitor competitor : competitors) {
            int[] scores = competitor.getScores();
            for (int i = 0; i < scores.length; i++) {
                totalScores[i] += scores[i];
            }
        }

        for (int i = 0; i < totalScores.length; i++) {
            double average = (double) totalScores[i] / totalCompetitors;
            System.out.printf("Score %d: %.2f%n", i + 1, average);
        }
    }

    private void printMaxMin() {
        System.out.println("\nMax and Min Scores:");

        int maxScore = Integer.MIN_VALUE;
        int minScore = Integer.MAX_VALUE;

        for (Competitor competitor : competitors) {
            int[] scores = competitor.getScores();
            for (int score : scores) {
                maxScore = Math.max(maxScore, score);
                minScore = Math.min(minScore, score);
            }
        }

        System.out.println("Max Score: " + maxScore);
        System.out.println("Min Score: " + minScore);
    }

    private void printFrequencyReport() {
        System.out.println("\nFrequency Report:");

        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (Competitor competitor : competitors) {
            int[] scores = competitor.getScores();
            for (int score : scores) {
                frequencyMap.put(score, frequencyMap.getOrDefault(score, 0) + 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            System.out.printf("Score %d was awarded %d times.%n", entry.getKey(), entry.getValue());
        }
    }
}
