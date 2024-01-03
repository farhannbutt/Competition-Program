import java.util.ArrayList;

public class Manager {
    public static void main(String[] args) {
        generateReport();
    }

    public static void generateReport() {
        String file = "CompetitorList.csv";

        CompetitorList competitorList = new CompetitorList(file);
        competitorList.saveReportToFile("ResultReport.txt");
    }

    public static void runTests() {
        Test testInstance = new Test();
        testInstance.testDetails();
    }
}
