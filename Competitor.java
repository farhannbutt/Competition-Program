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

    @Override
    public String toString() {
        return String.format("Competitor %03d: %s %s, Email: %s, DOB: %s, Category: %s, Level: %s, Scores: %s",
                competitorNumber, firstName, lastName, email, dateOfBirth, category, level, Arrays.toString(scores));
    }
}
