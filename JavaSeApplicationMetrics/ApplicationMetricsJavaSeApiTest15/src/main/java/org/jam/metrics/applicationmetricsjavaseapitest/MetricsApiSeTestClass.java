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
package org.jam.metrics.applicationmetricsjavaseapitest;

import org.jam.metrics.applicationmetricsapi.ApplicationJavaSeMetrics;
import org.jam.metrics.applicationmetricsapi.JMathPlotAdapter;
import org.jam.metrics.applicationmetricsapi.Plot;
import org.jam.metrics.applicationmetricslibrary.DeploymentMetricProperties;
import org.jam.metrics.applicationmetricsproperties.MetricProperties;

/**
 *
 * @author Panagiotis Sotiropoulos
 */
public class MetricsApiSeTestClass {

    private double count[][];
    
    private double count2[][];
    
    private double count3[][];
    
    private double count4[][];
    
    final MetricProperties properties;

    public MetricsApiSeTestClass() {
        properties = DeploymentMetricProperties.getDeploymentMetricProperties().getDeploymentMetricProperty("myTestGroup");
    }

    public void countMethod() throws IllegalArgumentException, IllegalAccessException {
        count = new double[10][3];
        count2 = new double[10][3];
        count3 = new double[10][10];
        count4 = new double[10][10];
        
        for(int i=0; i<10; i++) {
            for(int j=0; j<3; j++) {
                count[i][j]=i*j+Math.random()*5;
                count2[i][j]=i*j +2*i+Math.random()*5;
            }
            
            for(int j=0; j<10; j++) {
                count3[i][j]=i*j+Math.random()*5;
                count4[i][j]=i*j+Math.random()*4;
            }
        }
        
        JMathPlotAdapter.jMathPlotAdapter(count3, "myTestGroup", properties, "plot1", "count3", "red", "box", true);
        JMathPlotAdapter.jMathPlotAdapter(count4, "myTestGroup", properties, "plot1", "count4", "blue", "box", true);
        JMathPlotAdapter.jMathPlotAdapter(count, "myTestGroup", properties, "plot2", "count", "blue", "scatter", true);
        JMathPlotAdapter.jMathPlotAdapter(count2, "myTestGroup", properties, "plot2", "count2", "green", "scatter", true);
        JMathPlotAdapter.jMathPlotAdapter(count3, "myTestGroup", properties, "plot3", "count3", "green", "histogram", true);
        JMathPlotAdapter.jMathPlotAdapter(count4, "myTestGroup", properties, "plot3", "count4", "magenta", "histogram", true);
        JMathPlotAdapter.jMathPlotAdapter(count3, "myTestGroup", properties, "plot4", "count3", "yellow", "grid", true);
        JMathPlotAdapter.jMathPlotAdapter(count4, "myTestGroup", properties, "plot4", "count4", "red", "grid", true);
        JMathPlotAdapter.jMathPlotAdapter(count2, "myTestGroup", properties, "plot5", "count2", "magenta", "line", true);
        JMathPlotAdapter.jMathPlotAdapter(count, "myTestGroup", properties, "plot5", "count", "blue", "line", true);
    }
}
