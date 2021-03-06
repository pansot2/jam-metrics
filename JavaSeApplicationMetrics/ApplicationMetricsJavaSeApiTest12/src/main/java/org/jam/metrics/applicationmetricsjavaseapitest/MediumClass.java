/*
 * Copyright 2016 panos.
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
package org.jam.metrics.applicationmetricsjavaseapitest;

import org.jam.metrics.applicationmetricsapi.ApplicationJavaSeHawkularApm;
import org.jam.metrics.applicationmetricsapi.HawkularApm;

/**
 *
 * @author panos
 */
public class MediumClass {

    EndingClass endClass;
    
    OtherClass otherClass;

    public MediumClass() {
        this.endClass = new EndingClass();
        this.otherClass = new OtherClass();
    }

    public void mediumClass() throws Exception {
        ApplicationJavaSeHawkularApm.hawkularApm("mediumClass", "myTestGroup", new String[]{"endClass","otherClass"}, false);
        System.out.println("Here is the middle ...");
        endClass.endClass();
        otherClass.otherClass();
    }
}
