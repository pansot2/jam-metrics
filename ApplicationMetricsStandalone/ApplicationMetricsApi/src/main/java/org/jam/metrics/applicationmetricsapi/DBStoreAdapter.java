/*
 * Copyleft 2016 Red Hat, Inc. and/or its affiliates
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
package org.jam.metrics.applicationmetricsapi;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Map;
import org.jam.metrics.applicationmetricsjavase.DBStoreCollection;
import org.jam.metrics.applicationmetricsjavase.DBStoreInstance;
import org.jam.metrics.applicationmetricslibrary2.CodeParams;
import org.jam.metrics.applicationmetricslibrary2.CodeParamsCollection;

/**
 *
 * @author panos
 */
class DBStoreAdapter {

    private final static Object dbLock = new Object();

    protected static void dBStoreAdapter(String dataBaseStorage, final Object instance, final Object[] values, final String group, final String statementName, final String[] queryUpdateDB, String metricUser) throws Exception {
        if (dataBaseStorage != null && Boolean.parseBoolean(dataBaseStorage)) {
            CodeParams cp = null;

            if (metricUser == null) {
                metricUser = "default";
            }

            final String mUser = metricUser;

            if (CodeParamsCollection.getCodeParamsCollection().existsCodeParamsInstance(mUser)) {
                cp = CodeParamsCollection.getCodeParamsCollection().getCodeParamsInstance(mUser);
            }

            final CodeParams cParams;

            if (cp != null) {
                cParams = cp.deepClone();
            } else {
                cParams = null;
            }

            new Thread() {
                public void run() {
                    DBStoreInstance dBStoreInstance;
                    synchronized (dbLock) {
                        dBStoreInstance = DBStoreCollection.getDBStoreCollection().getDbStoreInstance(group);
                        if (dBStoreInstance == null) {
                            dBStoreInstance = new DBStoreInstance();
                            DBStoreCollection.getDBStoreCollection().addDbStoreInstance(group, dBStoreInstance);
                        }
                    }

                    try {
                        dBStoreInstance.dbStore(queryUpdateDB, instance, values, statementName, group, cParams, mUser);
                    } catch (IllegalArgumentException | SQLException | IllegalAccessException ex) {
                        ex.printStackTrace();
                    } 
                }
            }.start();
        }
    }
    
}
