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
package org.jboss.metrics.javase.automatedmetricsjavaseapi;

import java.sql.Statement;
import org.jboss.metrics.automatedmetricsjavase.ParseDbQuery;
import org.jboss.metrics.jbossautomatedmetricslibrary.DeploymentMetricProperties;

/**
 *
 * @author Panagiotis Sotiropoulos
 */
public class JbossAutomatedJavaSeMetricsDbStore {

    public void metricsDbStore(Object instance, Object[] values, String group, String statementName, String[] queryUpdateDB) throws Exception {
        String dbStore = DeploymentMetricProperties.getDeploymentMetricProperties().getDeploymentMetricProperty(group).getDatabaseStore();
        
        try {
            if (dbStore != null && Boolean.parseBoolean(dbStore)) {
                String query = ParseDbQuery.parse(queryUpdateDB,values,instance,group);
                Statement stmt = DeploymentMetricProperties.getDeploymentMetricProperties().getDeploymentMetricProperty(group).getDatabaseStatement().get(statementName);
                stmt.executeUpdate(query);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}

