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

/*
 *  ΙΔΕΑ : Everything is a potential metric .
 */
package org.jam.metrics.applicationmetrics;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import org.jam.metrics.applicationmetricsapi.Metric;
import org.jam.metrics.applicationmetricsapi.Plot;
import org.jam.metrics.applicationmetricslibrary.DeploymentMetricProperties;
import org.jam.metrics.applicationmetricsproperties.MetricProperties;

/**
 *
 * @author Panagiotis Sotiropoulos
 */
@Plot
@Interceptor
public class PlotInterceptor {
   
    @AroundInvoke
    public Object plotInterceptor(InvocationContext ctx) throws Exception {
        Object result = ctx.proceed();
        Method method = ctx.getMethod();
        final Object target = ctx.getTarget();

        try {
            final Plot plotAnnotation = method.getAnnotation(Plot.class);
            if (plotAnnotation != null) {
                int fieldNameSize = plotAnnotation.fieldData().length;
                final String group = plotAnnotation.groupName();
                final boolean threeD = plotAnnotation.threeD();

                final MetricProperties properties = DeploymentMetricProperties.getDeploymentMetricProperties().getDeploymentMetricProperty(group);
                String metricPlot = properties.getMetricPlot();
                final int refreshRate = properties.getPlotRefreshRate();

                if (metricPlot.compareTo("true")==0) {
                    for (int i = 0; i < fieldNameSize; i++) {
                        JMathPlotAdapter.jMathPlotAdapter(metricPlot, group, target, method, plotAnnotation.fieldData()[i], refreshRate, i, properties, plotAnnotation, threeD);  
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


}
