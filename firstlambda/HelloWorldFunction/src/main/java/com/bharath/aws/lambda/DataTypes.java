package com.bharath.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

public class DataTypes {

    private Double instanceVariable = Math.random();
    private static Double staticVariable = Math.random();

	public DataTypes() {
		System.out.println("Inside Constructor");
	}

	static {
		System.out.println("Static Block executed");
	}

    public void coldstartBasics() throws InterruptedException {
        //	Thread.sleep(5000);
        double localVariable = Math.random();
        System.out.println("Instance: " + instanceVariable +
				" Static Variable: " + staticVariable +
				" localVariable " + localVariable);
    }

    public boolean getNumber(float number) {
        return number > 100;
    }

    public List<Integer> getScores(List<String> names) {
        Map<String, Integer> studentScores = new HashMap<>();
        studentScores.put("John", 90);
        studentScores.put("Bob", 80);
        studentScores.put("Ahmed", 100);

        List<Integer> matchingScores = new LinkedList<>();

        names.forEach(name -> {
            matchingScores.add(studentScores.get(name));
        });

        return matchingScores;
    }

    public void saveEmployeeData(Map<String, Integer> empData) {
        System.out.println(empData);
    }

    public Map<String, List<Integer>> getStudentScores() {
        Map<String, List<Integer>> studentScores = new HashMap<String, List<Integer>>();
        studentScores.put("John", Arrays.asList(80, 90, 100));
        studentScores.put("Bob", Arrays.asList(80, 70, 90));
        studentScores.put("Doug", Arrays.asList(80, 90, 20));
        return studentScores;
    }

    public ClinicalData getClinicals(Patient patient) {
        System.out.println(patient.getName());
        System.out.println(patient.getSsn());
        ClinicalData clinicalData = new ClinicalData();
        clinicalData.setBp("80/120");
        clinicalData.setHeartRate("80");
        return clinicalData;
    }

    public void getOutput(InputStream input, OutputStream output, Context context)
            throws IOException, InterruptedException {
		//Thread.sleep(4000);
        System.out.println(System.getenv("restapiurl")); // testurl
        System.out.println(context.getAwsRequestId()); // 3129e4ff-ea0c-48c3-a357-f26312118479
        System.out.println(context.getFunctionName()); // firstlambda-HelloWorldFunction-NM1zvICLlprj
        System.out.println(context.getRemainingTimeInMillis()); // 2998
        System.out.println(context.getMemoryLimitInMB()); // 512
        System.out.println(context.getLogGroupName()); // /aws/lambda/firstlambda-HelloWorldFunction-NM1zvICLlprj

        int data;
        while ((data = input.read()) != -1) {
            output.write(Character.toLowerCase(data));
        }
    }

}
