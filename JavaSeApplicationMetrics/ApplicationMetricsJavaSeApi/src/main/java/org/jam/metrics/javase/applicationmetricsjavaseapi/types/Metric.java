/*
 * Copyright 2016  by Red Hat.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jam.metrics.javase.applicationmetricsjavaseapi.types;

import java.util.concurrent.Callable;
import org.jam.metrics.javase.applicationmetricsjavaseapi.ApplicationJavaSeMetrics;
import org.jam.metrics.javase.applicationmetricsjavaseapi.ApplicationJavaSeMetricsSyncDbStore;

/**
 *
 * @author panos
 */
public class Metric implements MetricInterface {
    private String metricName;
    private Object metricValue;
    private MetricProperties metricProperties;

    public Metric(String metricName, Object metricValue, MetricProperties metricProperties) {
        this.metricName = metricName;
        this.metricValue = metricValue;
        this.metricProperties = metricProperties;
    }

    public synchronized MetricProperties getMetricProperties() {
        return metricProperties;
    }

    public synchronized void setMetricProperties(MetricProperties metricProperties) {
        this.metricProperties = metricProperties;
    }

    public synchronized String getMetricName() {
        return metricName;
    }

    public synchronized void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public synchronized Object getMetricValue() {
        return metricValue;
    }

    public synchronized void setMetricValue(Object metricValue) {
        this.metricValue = metricValue;
    }

    public synchronized void applyMetricFeatures(Callable<Object> applyMetric) {
        try {
            applyMetric.call();
            ApplicationJavaSeMetrics.metric(this,metricValue, metricName,metricProperties.groupName);
            ApplicationJavaSeMetricsSyncDbStore.metricsDbStore(this, new Object[]{metricValue}, metricProperties.groupName, metricProperties.statementName, new String[]{metricProperties.queryString, metricName}, metricProperties.userName);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    
}