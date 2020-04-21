
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.sling.scripting.gql.schema;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FetcherDefinition {
    public final String typeName;
    public final String fieldName;
    public final String fetcherNamespace;
    public final String fetcherName;
    public final String fetcherOptions;
    public final String fetcherSourceExpression;

    /** Definitions are formatted like 
     *  fetch:test/digest:sha512/$.path
     */
    private static final Pattern REGEXP = Pattern.compile("fetch\\:(\\w+)/(\\w+)(/(\\S+))?( +(.*))?");

    /** Creates a definition from a formatted String like
     *  
      */
    public FetcherDefinition(String typeName, String fieldName, String fetcherDef) {
        if(typeName == null || fieldName == null || fetcherDef == null) {
            throw new IllegalArgumentException("Invalid input: " + typeName + "," + fieldName + "," + fetcherDef);
        }
        final Matcher m = REGEXP.matcher(fetcherDef);
        if(!m.matches()) {
            throw new IllegalArgumentException("Input does not match " + REGEXP + ": " + fetcherDef);
        }
        this.typeName = typeName;
        this.fieldName = fieldName;
        fetcherNamespace = m.group(1);
        fetcherName = m.group(2);
        fetcherOptions = optional(m.group(4));
        fetcherSourceExpression = optional(m.group(6));
    }

    private static final String optional(String input) {
        return input == null ? "" : input.trim();
    }

    @Override
    public String toString() {
        return String.format(
            "%s#%s#%s#%s#%s#%s#%s", 
            getClass().getSimpleName(), typeName, fieldName, 
            fetcherNamespace, fetcherName, 
            fetcherOptions, fetcherSourceExpression);
    }
}