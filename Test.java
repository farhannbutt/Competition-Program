import java.util.Arrays;
import java.util.ArrayList;

public class Test {
    private Competitor createSampleCompetitor() {
        // You can customize these values to create different sample competitors
        int competitorNumber = 100;
        String firstName = "Keith";
        String lastName = "Talbot";
        String email = "keith.talbot@example.com";
        String dateOfBirth = "1990-01-15";
        String category = "Novice";
        String level = "Intermediate";
        int[] scores = { 5, 4, 5, 4, 3 };

        return new Competitor(competitorNumber, new Name(firstName, lastName), email, dateOfBirth, category, level,
                scores);
    }

    public void testDetails() {
        // Create a sample competitor
        Competitor sampleCompetitor = createSampleCompetitor();

        // Print short details
        System.out.println("Short Details:");
        System.out.println(sampleCompetitor.generateShortDetails());

        // Print full details
        System.out.println("\nFull Details:");
        System.out.println(sampleCompetitor.generateFullDetails());
    }
}
