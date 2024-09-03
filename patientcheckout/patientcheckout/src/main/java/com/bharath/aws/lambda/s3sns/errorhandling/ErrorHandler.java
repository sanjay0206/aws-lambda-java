package com.bharath.aws.lambda.s3sns.errorhandling;

import com.bharath.aws.lambda.s3sns.PatientCheckoutLambda;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.amazonaws.services.lambda.runtime.events.SNSEvent;

public class ErrorHandler {

    public void handler(SNSEvent event) {
        Logger logger = LogManager.getLogger(ErrorHandler.class);
        event.getRecords().forEach(record -> logger.info("Dead Letter Queue Event" + record.toString()));
    }

}
