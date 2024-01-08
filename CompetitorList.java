import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class CompetitorList {
    private ArrayList<Competitor> competitors;
    private String filePath;

    public CompetitorList(String file) {
        this.competitors = readCompetitorsFromCSV(file);
        this.filePath = file;
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

                Competitor competitor = new Competitor(competitorNumber,
                        new Name(firstName, lastName), email, dateOfBirth, category, level, scores);

                competitors.add(competitor);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return competitors;
    }

    public String generateFinalReport() {
        StringBuilder report = new StringBuilder();

        // Add table of competitors with full details
        report.append(generateCompetitorsTable());

        // Add details of the competitor with the highest overall score
        report.append(generateHighestOverallScoreDetails());

        // Add summary statistics
        report.append(generateSummaryStatistics());

        return report.toString();
    }

    public void saveReportToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(generateFinalReport());
            System.out.println("Final report has been saved to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String generateCompetitorsTable() {
        StringBuilder table = new StringBuilder();

        table.append("Competitors Table:\n");
        table.append(
                "-------------------------------------------------------------------------------------------------------------------------\n");
        table.append(String.format("%-15s %-15s %-15s %-25s %-12s %-20s %-15s%n",
                "Competitor #", "First Name", "Last Name", "Email", "DOB", "Category", "Scores"));
        table.append(
                "-------------------------------------------------------------------------------------------------------------------------\n");

        for (Competitor competitor : competitors) {
            table.append(String.format("%-15d %-15s %-15s %-25s %-12s %-20s %-15s%n",
                    competitor.getCompetitorNumber(), competitor.getName().getFirstName(),
                    competitor.getName().getLastName(), competitor.getEmail(), competitor.getDateOfBirth(),
                    competitor.getCategory(), Arrays.toString(competitor.getScores())));
        }

        table.append("\n");
        return table.toString();
    }

    public void sortCompetitorsByName() {
        competitors.sort(Comparator.comparing(c -> c.getName().getFirstName()));
    }

    public void sortCompetitorsByScores() {
        competitors.sort(Comparator.comparingDouble(c -> calculateWeightedAverage(c.getScores())));
    }

    private double calculateWeightedAverage(int[] scores) {
        return Arrays.stream(scores).average().orElse(0.0);
    }

    private String generateHighestOverallScoreDetails() {
        Competitor highestScorer = getCompetitorWithHighestOverallScore();
        return "Details of the Competitor with the Highest Overall Score:\n" +
                highestScorer.generateFullDetails() + "\n\n";
    }

    private Competitor getCompetitorWithHighestOverallScore() {
        return competitors.stream()
                .max(Comparator.comparingDouble(Competitor::calculateWeightedAverage))
                .orElse(null);
    }

    private String generateSummaryStatistics() {
        StringBuilder statistics = new StringBuilder();

        statistics.append("Summary Statistics:\n");
        statistics.append(generateTotals());
        statistics.append(generateAverages());
        statistics.append(generateMaxMin());
        statistics.append(generateFrequencyReport());

        return statistics.toString();
    }

    private String generateTotals() {
        StringBuilder totals = new StringBuilder();
        totals.append("Total Scores:\n");

        int[] totalScores = calculateTotalScores();
        totals.append(Arrays.toString(totalScores)).append("\n\n");

        return totals.toString();
    }

    private int[] calculateTotalScores() {
        int[] totalScores = new int[5];

        for (Competitor competitor : competitors) {
            int[] scores = competitor.getScores();
            for (int i = 0; i < scores.length; i++) {
                totalScores[i] += scores[i];
            }
        }

        return totalScores;
    }

    private String generateAverages() {
        StringBuilder averages = new StringBuilder();
        averages.append("Average Scores:\n");

        int[] totalScores = calculateTotalScores();
        int totalCompetitors = competitors.size();

        for (int i = 0; i < totalScores.length; i++) {
            double average = (double) totalScores[i] / totalCompetitors;
            averages.append(String.format("Score %d: %.2f%n", i + 1, average));
        }

        averages.append("\n");
        return averages.toString();
    }

    private String generateMaxMin() {
        StringBuilder maxMin = new StringBuilder();
        maxMin.append("Max and Min Scores:\n");

        int[] maxMinScores = calculateMaxMinScores();
        maxMin.append("Max Score: ").append(maxMinScores[0]).append("\n");
        maxMin.append("Min Score: ").append(maxMinScores[1]).append("\n\n");

        return maxMin.toString();
    }

    private int[] calculateMaxMinScores() {
        int maxScore = Integer.MIN_VALUE;
        int minScore = Integer.MAX_VALUE;

        for (Competitor competitor : competitors) {
            int[] scores = competitor.getScores();
            for (int score : scores) {
                maxScore = Math.max(maxScore, score);
                minScore = Math.min(minScore, score);
            }
        }

        return new int[] { maxScore, minScore };
    }

    private String generateFrequencyReport() {
        StringBuilder frequencyReport = new StringBuilder();
        frequencyReport.append("Frequency Report:\n");

        Map<Integer, Integer> scoreFrequency = calculateScoreFrequency();

        for (Map.Entry<Integer, Integer> entry : scoreFrequency.entrySet()) {
            frequencyReport.append("Score ").append(entry.getKey()).append(" awarded ").append(entry.getValue())
                    .append(" times\n");
        }

        frequencyReport.append("\n");
        return frequencyReport.toString();
    }

    private Map<Integer, Integer> calculateScoreFrequency() {
        Map<Integer, Integer> scoreFrequency = new HashMap<>();

        for (Competitor competitor : competitors) {
            int[] scores = competitor.getScores();
            for (int score : scores) {
                scoreFrequency.put(score, scoreFrequency.getOrDefault(score, 0) + 1);
            }
        }

        return scoreFrequency;
    }

    public Competitor searchCompetitorByEmailAndCategory(String email, String category) {
        for (Competitor competitor : competitors) {
            if (competitor.getEmail().equalsIgnoreCase(email) && competitor.getCategory().equalsIgnoreCase(category)) {
                return competitor;
            }
        }
        return null;
    }

    public void addCompetitorToCSV(Competitor newCompetitor) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            // Append the new competitor to the CSV file
            bw.write(newCompetitor.toCSVString());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add the new competitor to the ArrayList
        competitors.add(newCompetitor);
    }

    public void removeCompetitorByID(int competitorID) {
        competitors.removeIf(competitor -> competitor.getCompetitorNumber() == competitorID);
        updateCSVFile();
    }

    public void amendCompetitorByID(int competitorID, Competitor newCompetitor) {
        // Remove the old competitor
        removeCompetitorByID(competitorID);

        // Add the new competitor
        addCompetitorToCSV(newCompetitor);
    }

    public Competitor searchCompetitorByNumber(int competitorNumber) {
        for (Competitor competitor : competitors) {
            if (competitor.getCompetitorNumber() == competitorNumber) {
                return competitor;
            }
        }
        return null; // Competitor not found
    }

    public void recordCompetitorScores(int competitorID, int[] newScores) {
        Competitor competitor = searchCompetitorByNumber(competitorID);
        if (competitor != null) {
            competitor.setScores(newScores);
            updateCSVFile();
        }
    }

    private void updateCSVFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            // Rewrite the entire CSV file with the updated competitor data
            bw.write("competitor_number,first_name,last_name,email,date_of_birth,category,level,scores");
            bw.newLine();
            for (Competitor competitor : competitors) {
                bw.write(competitor.toCSVString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
