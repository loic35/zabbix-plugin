package org.openbaton.monitoring.agent.interfaces;

import org.openbaton.catalogue.nfvo.Item;
import org.openbaton.monitoring.agent.exceptions.MonitoringException;
import org.openbaton.monitoring.agent.performance.management.catalogue.ObjectSelection;
import org.openbaton.monitoring.agent.performance.management.catalogue.ThresholdDetails;
import org.openbaton.monitoring.agent.performance.management.catalogue.ThresholdType;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by mob on 17.11.15.
 */
public interface VirtualisedResourcesPerformanceManagement {
    /*
    * This operation will create a PM job, enabling the NFVO to specify a resource or set of resources,
    * that the VIM is managing, for which it wants to receive performance information.
    * This will allow the requesting NFVO to specify its performance information requirements with the VIM. 
    */
    String createPMJob(ObjectSelection resourceSelector, List<String> performanceMetric, List<String> performanceMetricGroup, Integer collectionPeriod,
                     Integer reportingPeriod) throws MonitoringException;
    List<String> deletePMJob(List<String> itemIdsToDelete) throws MonitoringException;
    List<Item> queryPMJob(List<String> hostnames, List<String> metrics, String period) throws RemoteException;

    void subscribe();
    /*
    * This operation distributes notifications to subscribers.
    * It is a one-way operation issued by the VIM that cannot be invoked
    * as an operation by the consumer (NFVO). 
    * The following notifications can be notified/sent by this operation: 
    *   - PerformanceInformationAvailableNotification 
    *   - ThresholdCrossedNotification 
    */
    void notifyInfo();
    /*
    * This operation will allow the NFVO to create a threshold to specify
    * threshold levels on specified performance metric and resource(s) for
    * which notifications will be generated when crossed.
    * Creating a threshold does not trigger collection of metrics.
    * In order for the threshold to be active, there should be a PM job collecting
    * the needed metric for the selected entities.  
    */
    String createThreshold(List<ObjectSelection> objectSelectors, String performanceMetric, ThresholdType thresholdType, ThresholdDetails thresholdDetails) throws MonitoringException;
    /*
    * This operation will allow the NFVO to delete one or more existing threshold(s). 
    */
    List<String> deleteThreshold(List<String> thresholdIds) throws MonitoringException;
    /*
    * This operation will allow the NFVO to query the details of an existing threshold. 
    */
    void queryThreshold(String queryFilter);
}
