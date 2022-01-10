package com.exercise.refactoring;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;

public class FundingRaisedRefactored {
    public static List<Map<String, String>> where(Map<String, String> options) throws IOException
    {
    List<String[]> csvData = new ArrayList<>();
    CSVReader reader = new CSVReader(new FileReader("C:\\Users\\syedf\\eclipse-workspace\\Reengineering\\ReEngineeringExercises\\startup_funding.csv"));
    String[] row;

    while((row = reader.readNext()) != null) {
        csvData.add(row);
    }

    reader.close();
    csvData.remove(0);

    if(options.containsKey("company_name")) {
        List<String[]> results = new ArrayList<>();

        for (String[] csvDatum : csvData) {
            if (csvDatum[1].equals(options.get("company_name"))) {
                results.add(csvDatum);
            }
        }
        csvData = results;
    }

    if(options.containsKey("city")) {
        List<String[]> results = new ArrayList<>();

        for (String[] csvDatum : csvData) {
            if (csvDatum[4].equals(options.get("city"))) {
                results.add(csvDatum);
            }
        }
        csvData = results;
    }

    if(options.containsKey("state")) {
        List<String[]> results = new ArrayList<>();

        for (String[] csvDatum : csvData) {
            if (csvDatum[5].equals(options.get("state"))) {
                results.add(csvDatum);
            }
        }
        csvData = results;
    }

    if(options.containsKey("round")) {
        List<String[]> results = new ArrayList<>();

        for (String[] csvDatum : csvData) {
            if (csvDatum[9].equals(options.get("round"))) {
                results.add(csvDatum);
            }
        }
        csvData = results;
    }

    List<Map<String, String>> output = new ArrayList<>();

    for (String[] csvDatum : csvData) {

        output.add(FundingRaisedRefactored.Mapping(csvDatum));
    }

    return output;
}
//
public static Map<String, String> Mapping(String[] value){
    Map<String, String> mapped = new HashMap<>();
    mapped.put("permalink", value[0]);
    mapped.put("company_name", value[1]);
    mapped.put("number_employees", value[2]);
    mapped.put("category", value[3]);
    mapped.put("city", value[4]);
    mapped.put("state", value[5]);
    mapped.put("funded_date", value[6]);
    mapped.put("raised_amount", value[7]);
    mapped.put("raised_currency", value[8]);
    mapped.put("round", value[9]);
    return mapped;
}

public static Map<String, String> findBy(Map<String, String> options) throws IOException, NoSuchEntryException {
    List<String[]> csvData = new ArrayList<>();
    CSVReader reader = new CSVReader(new FileReader("startup_funding.csv"));
    String[] row;

    while((row = reader.readNext()) != null) {
        csvData.add(row);
    }

    reader.close();
    csvData.remove(0);


    for (String[] csvDatum : csvData) {
        if (options.containsKey("company_name")) {
            if (csvDatum[1].equals(options.get("company_name"))) return FundingRaisedRefactored.Mapping(csvDatum);
        }

        if (options.containsKey("city")) {
            if (csvDatum[4].equals(options.get("city"))) return FundingRaisedRefactored.Mapping(csvDatum);
        }

        if (options.containsKey("state")) {
            if (csvDatum[5].equals(options.get("state"))) return FundingRaisedRefactored.Mapping(csvDatum);
        }

        if (options.containsKey("round")) {
            if (!csvDatum[9].equals(options.get("round"))) return FundingRaisedRefactored.Mapping(csvDatum);

        }


    }

    throw new NoSuchEntryException();
}

public static void main(String[] args) {
    try {
        Map<String, String> options = new HashMap<>();
        options.put("company_name", "Kyte");
        options.put("round", "b");
        System.out.print(FundingRaisedRefactored.where(options).size());
    } catch(IOException e) {
        System.out.print(e.getMessage());
        System.out.print("error");
    }
}
}

class NoSuchEntryException extends Exception {}