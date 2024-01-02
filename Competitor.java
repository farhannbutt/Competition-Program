import java.util.Arrays;

public class Competitor {
    private int competitorNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth;
    private String category;
    private String level;
    private int[] scores;

    public Competitor(int competitorNumber, String firstName, String lastName, String email,
            String dateOfBirth, String category, String level, int[] scores) {
        this.competitorNumber = competitorNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.category = category;
        this.level = level;
        this.scores = scores;
    }

    // Getter methods
    public int getCompetitorNumber() {
        return competitorNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCategory() {
        return category;
    }

    public String getLevel() {
        return level;
    }

    public int[] getScores() {
        return scores;
    }

    // Setter methods
    public void setCompetitorNumber(int competitorNumber) {
        this.competitorNumber = competitorNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setScores(int[] scores) {
        this.scores = scores;
    }

    public String generateFullDetails() {
        double averageScore = calculateAverageScore();
        String categoryAbbreviation = getCategoryAbbreviation();
        return String.format("Competitor number %03d, name %s %s %s.\n%s is a %s and received these scores: %s\n" +
                "This gives them an overall score of %.1f.",
                competitorNumber, firstName, middleInitial(), lastName, firstName, getCategory(),
                Arrays.toString(scores), averageScore);
    }

    // New method to generate short details
    public String generateShortDetails() {
        double averageScore = calculateAverageScore();
        String categoryAbbreviation = getCategoryAbbreviation();
        return String.format("CN %03d (%s%s) has an overall score of %.1f.",
                competitorNumber, categoryAbbreviation, getInitials(), averageScore);
    }

    private String middleInitial() {
        return (firstName.length() > 1) ? String.valueOf(firstName.charAt(1)) + "." : "";
    }

    private String getInitials() {
        return String.valueOf(firstName.charAt(0)) + String.valueOf(lastName.charAt(0));
    }

    private String getCategoryAbbreviation() {
        return getCategory().substring(0, 2).toUpperCase();
    }

    public double calculateAverageScore() {
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return (double) sum / scores.length;
    }

    @Override
    public String toString() {
        return String.format("Competitor %03d: %s %s, Email: %s, DOB: %s, Category: %s, Level: %s, Scores: %s",
                competitorNumber, firstName, lastName, email, dateOfBirth, category, level, Arrays.toString(scores));
    }
}
