/*
 * Copyleft 2015 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.metrics.automatedmetricsjavaseapitest;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jboss.metrics.javase.automatedmetricsjavaseapi.MetricsCacheApi;
import org.jboss.metrics.javase.automatedmetricsjavaseapi.MetricsPropertiesApi;
import org.jboss.metrics.jbossautomatedmetricslibrary.MetricsCacheCollection;
import org.jboss.metrics.jbossautomatedmetricsproperties.MetricProperties;

/**
 *
 * @author Panagiotis Sotiropoulos
 */
public class AutomatedMetricsJavaSeApiTest {

    private static String groupName = "myTestGroup";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            initializeMetricProperties();
            MetricsApiSeTestClass mTC = new MetricsApiSeTestClass();
            MetricsThreads mTreads =  new MetricsThreads("1",mTC);
            mTreads.start();
         
            MetricsThreads mTreads2 =  new MetricsThreads("2",mTC);
            mTreads2.start();
            
            MetricsThreads mTreads3 =  new MetricsThreads("3",mTC);
            mTreads3.start();
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(AutomatedMetricsJavaSeApiTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (MetricsCacheCollection.getMetricsCacheCollection().getMetricsCacheInstance(groupName)!=null)
                System.out.println(MetricsCacheApi.printMetricsCache(groupName));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void initializeMetricProperties() {
        HashMap<String,String> rhqScheduleIds = new HashMap<String,String>();
        rhqScheduleIds.put("count", "11401");
        rhqScheduleIds.put("count2", "11402");
        MetricProperties metricProperties = new MetricProperties();
        metricProperties.setRhqMonitoring("false");
        metricProperties.setCacheStore("true");
        metricProperties.setRhqServerUrl("lz-panos-jon33.bc.jonqe.lab.eng.bos.redhat.com");
        metricProperties.setRhqScheduleIds(rhqScheduleIds);
        metricProperties.setDatabaseStore("false");
        MetricsPropertiesApi.storeProperties(groupName, metricProperties);
    }
    
}