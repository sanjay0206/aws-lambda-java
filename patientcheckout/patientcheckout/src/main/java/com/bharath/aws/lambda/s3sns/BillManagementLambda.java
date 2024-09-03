package com.bharath.aws.lambda.s3sns;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BillManagementLambda {

	private final ObjectMapper objectMapper = new ObjectMapper();
	Logger logger = LogManager.getLogger(BillManagementLambda.class);

	public void handler(SNSEvent event, Context context) {

		event.getRecords().forEach(snsRecord -> {
			try {
				PatientCheckoutEvent patientCheckoutEvent = objectMapper.readValue(snsRecord
						.getSNS().getMessage(),
						PatientCheckoutEvent.class);

				logger.info(patientCheckoutEvent.toString());
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		});
	}

}
