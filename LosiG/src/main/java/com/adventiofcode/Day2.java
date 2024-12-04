package com.adventiofcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.print.DocFlavor.INPUT_STREAM;

public class Day2 {
    static final String INPUT_PATH = "input-02.txt";

    public static void main(String[] args) {
        ArrayList<String> data = new ArrayList<>();

        try {
            File inputFile = new File(INPUT_PATH);
            Scanner myReader = new Scanner(inputFile);
            while (myReader.hasNextLine()) {
                data.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // exercise1(data);
        exercise2(data);

    }

    private static void exercise1(ArrayList<String> data) {
        ArrayList<int[]> reports = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            int[] reportValues = Arrays.stream(data.get(i).split(" ")).mapToInt(Integer::parseInt).toArray();

            reports.add(reportValues);
        }

        int correctReports = 0;
        for (int i = 0; i < reports.size(); i++) {
            int[] report = reports.get(i);

            Boolean isReportDescising = report[0] > report[report.length - 1];
            Boolean isReportCorrect = true;

            for (int j = 0; j < report.length - 1; j++) {
                int currentDelta = 0;

                if (Boolean.TRUE.equals(isReportDescising)) {
                    currentDelta = report[j] - report[j + 1];
                } else {
                    currentDelta = report[j + 1] - report[j];
                }

                isReportCorrect = currentDelta >= 1 && currentDelta <= 3;

                if (!Boolean.TRUE.equals(isReportCorrect))
                    break;
            }
            if (Boolean.TRUE.equals(isReportCorrect))
                correctReports++;
        }
        System.out.println(correctReports);

    }

    private static void exercise2(ArrayList<String> data) {
        ArrayList<int[]> reports = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            int[] reportValues = Arrays.stream(data.get(i).split(" ")).mapToInt(Integer::parseInt).toArray();

            reports.add(reportValues);
        }

        int correctReports = 0;

        for (int i = 0; i < reports.size(); i++) {
            int[] report = reports.get(i);

            int errorThreshold = 0;
            Boolean isReportDescising = report[0] > report[report.length - 1];
            Boolean isReportCorrect = true;

            for (int j = 0; j < report.length - 1; j++) {
                int currentDelta = 0;
                final Integer currentIndex = j;

                if (Boolean.TRUE.equals(isReportDescising)) {
                    currentDelta = report[j] - report[j + 1];
                } else {
                    currentDelta = report[j + 1] - report[j];
                }

                isReportCorrect = currentDelta >= 1 && currentDelta <= 3;

                if (!Boolean.TRUE.equals(isReportCorrect))
                    if (errorThreshold == 0) {
                        int[] cleanedReport = Arrays.stream(report).filter(value -> value != currentIndex).toArray();
                        int cleanedDelta = 0;
                        Boolean isCleanedReportCorrect = true;

                        for (int k = 0; k < cleanedReport.length; k++) {
                            if (Boolean.TRUE.equals(isReportDescising)) {
                                cleanedDelta = cleanedReport[j] - cleanedReport[j + 1];
                            } else {
                                cleanedDelta = cleanedReport[j + 1] - cleanedReport[j];
                            }
                            isCleanedReportCorrect = cleanedDelta >= 1 && cleanedDelta <= 3;

                        }
                        errorThreshold++;
                        if (!Boolean.TRUE.equals(isCleanedReportCorrect))
                            break;

                    } else {
                        break;

                    }
            }
            if (Boolean.TRUE.equals(isReportCorrect))
                correctReports++;
        }
        System.out.println(correctReports);
    }
}