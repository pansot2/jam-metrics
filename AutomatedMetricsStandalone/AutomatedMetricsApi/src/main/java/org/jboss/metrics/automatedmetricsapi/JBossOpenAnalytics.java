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
package org.jboss.metrics.automatedmetricsapi;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

/**
 *
 * @author Panagiotis Sotiropoulos
 */
@Inherited
@InterceptorBinding
@Target({METHOD, TYPE})
@Retention(RUNTIME)
public @interface JBossOpenAnalytics {
    @Nonbinding
    boolean serverIpRecord() default true;
    @Nonbinding
    boolean serverLocationRecord() default false;
    @Nonbinding
    boolean numAccessRecord() default false;
    @Nonbinding
    boolean timeAccessRecord() default false;
    @Nonbinding
    boolean userRecord() default false;
    @Nonbinding
    String className() default "className";
    @Nonbinding
    String methodName() default "methodName";
    @Nonbinding
    String userName() default "default";
    @Nonbinding
    String recordDbName() default "";
    @Nonbinding
    String recordTableName() default "";
    @Nonbinding
    String dbStatement() default "";
    @Nonbinding
    String groupName() default "testGroup";
}
