package controller;

import view.GUI;
import java.util.ArrayList;

public class Manager {
    public static void main(String[] args) {
        // generateReport();
        launchGUI();
    }

    public static void generateReport() {
        String file = "CompetitorList.csv";

        CompetitorList competitorList = new CompetitorList(file);
        competitorList.saveReportToFile("ResultReport.txt");
    }

    public static void launchGUI() {
        CompetitorList competitorList = new CompetitorList("CompetitorList.csv");

        GUI gui = new GUI(competitorList);
        gui.launchGUI();
    }
}
