package com.adventiofcode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    static final String INPUT_PATH = "input-03.txt";

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

        exercise1(data);
        exercise2(data);

    }

    private static void exercise1(ArrayList<String> data) {
        int result = 0;
        for (int i = 0; i < data.size(); i++) {
            String corruptedMul = data.get(i);

            Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");

            Matcher matcher = pattern.matcher(corruptedMul);

            while (matcher.find()) {
                String values = matcher.group().split("\\(")[1];
                values = values.substring(0, values.length() - 1);

                int[] parsedValue = Arrays.stream(values.split(",")).mapToInt(Integer::parseInt).toArray();
                result += parsedValue[0] * parsedValue[1];

            }

        }
        System.out.println(result);
    }

    private static void exercise2(ArrayList<String> data) {
    }
}