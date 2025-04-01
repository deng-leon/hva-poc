package io.camunda.spring_zeebe_sdk.spring_zeebe_sdk_868;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.camunda.zeebe.spring.client.annotation.JobWorker;

@Component
public class CreateCaseInBackendSystem {
    @Autowired
    private ZeebeClient zeebeClient;
    private final static Logger LOG = LoggerFactory.getLogger(CreateCaseInBackendSystem.class);


    @JobWorker(type = "createNewCaseInBackendSystem")
    public void bookOrder(final ActivatedJob job) {

        String idNo = (String) job.getVariablesAsMap().get("IDNO");
        LOG.info("IDNO: " + idNo);

        //Correlate message

//        CorrelateMessageResponse correlateMessageResponse = zeebeClient.newCorrelateMessageCommand()
//                .messageName("orderReceived")
//                .correlationKey(order.getProduct())
//                .variables(order)
//                .send()
//                .join();

//        LOG.info("Order was correlated to process instance: {}", correlateMessageResponse.getProcessInstanceKey());

    }



}
