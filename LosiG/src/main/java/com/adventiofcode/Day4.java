package com.adventiofcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.DocFlavor.INPUT_STREAM;

public class Day4 {
    static final String INPUT_PATH = "input-04.txt";

    public static void main(String[] args) {
        ArrayList<String[]> data = new ArrayList<>();

        try {
            File inputFile = new File(INPUT_PATH);
            Scanner myReader = new Scanner(inputFile);
            while (myReader.hasNextLine()) {
                data.add(myReader.nextLine().split(""));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        exercise1(data);
        exercise2(data);

    }

    private static void exercise1(ArrayList<String[]> data) {
        checkIfWordIsInAnyDirectionOfAStringMatrix(data, "XMAS");

        ArrayList<String> linedData = tranformMatrixToLinedDataOfPossibleDirections(data);

        Pattern patternXmas = Pattern.compile("XMAS");
        Pattern patternSamx = Pattern.compile("SAMX");

        int matches = 0;
        for (int i = 0; i < linedData.size(); i++) {
            String line = linedData.get(i);

            Matcher matcherXmas = patternXmas.matcher(line);
            while (matcherXmas.find()) {
                System.out.println(matcherXmas.group());
                matches++;
            }

            Matcher matcherSamx = patternSamx.matcher(line);
            while (matcherSamx.find()) {
                System.out.println(matcherSamx.group());
                matches++;
            }

        }
        System.out.println(matches);
    }

    private static ArrayList<String> tranformMatrixToLinedDataOfPossibleDirections(ArrayList<String[]> data) {
        ArrayList<String> linedData = new ArrayList<>();

        String[][] matrix = new String[data.size()][data.get(0).length];

        // horizontal
        for (int i = 0; i < data.size(); i++) {
            String newLine = "";
            String[] line = data.get(i);

            // Uses this func to populate matrix but not actually related
            matrix[i] = line;

            for (int j = 0; j < line.length; j++) {
                newLine = newLine.concat(line[j]);
            }
            linedData.add(newLine);
        }

        // vertical
        for (int i = 0; i < matrix[0].length; i++) {
            String newLine = "";
            for (int j = 0; j < matrix.length; j++) {
                newLine = newLine.concat(matrix[j][i]);
            }
            linedData.add(newLine);
        }

        // diagonalAsc
        int yMax = 0;
        int xMax = 0;
        boolean hasYReachedMax = false;
        while (yMax != matrix.length - 1 || xMax != matrix[0].length - 1) {
            if (yMax == matrix.length - 1) {
                hasYReachedMax = true;
            }

            String newLine = "";
            int j = xMax;
            for (int i = yMax; i >= 0 && j <= yMax; i--) {
                newLine = matrix[i][j].concat(newLine);
                j++;
            }
            linedData.add(newLine);

            if (hasYReachedMax) {
                xMax++;
            } else {
                yMax++;
            }
        }

        // diagonalDesc
        yMax = 0;
        xMax = matrix.length - 1;
        hasYReachedMax = false;
        while (yMax != matrix.length - 1 || xMax != 0) {
            if (yMax == matrix.length - 1) {
                hasYReachedMax = true;
            }

            String newLine = "";
            int j = xMax;
            for (int i = yMax; i >= 0 && j >= 0; i--) {
                newLine = matrix[i][j].concat(newLine);
                j--;
            }
            linedData.add(newLine);

            if (hasYReachedMax) {
                xMax--;
            } else {
                yMax++;
            }
        }

        return linedData;
    }

    private static void checkIfWordIsInAnyDirectionOfAStringMatrix(ArrayList<String[]> data, String word) {

    }

    private static void exercise2(ArrayList<String[]> data) {

    }
}