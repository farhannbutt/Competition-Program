import java.util.ArrayList;

public class Manager {
    public static void main(String[] args) {
        String filePath = "CompetitorList.csv";
        ArrayList<Competitor> competitors = CompetitorList.readCompetitorsFromCSV(filePath);

        System.out.println("List of Competitors:");
        CompetitorList.printCompetitors(competitors);

        Test testInstance = new Test();
        testInstance.testDetails();
    }
}
