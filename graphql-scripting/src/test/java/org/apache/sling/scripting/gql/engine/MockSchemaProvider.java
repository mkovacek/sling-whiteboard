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
package org.apache.sling.scripting.gql.engine;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.graphql.api.SchemaProvider;

class MockSchemaProvider implements SchemaProvider {

    private static final String MOCK_SCHEMA = 
        "type Query {\n"
        + "    ## fetch:test/echo\n"
        + "    currentResource : SlingResource\n"
        + "    ## fetch:test/static\n"
        + "    staticContent: Test"
        + "}\n"
        + "type SlingResource { path: String resourceType: String }\n"
        + "type Test { test: Boolean }";

    @Override
    public String getSchema(Resource r, String[] selectors) throws SchemaProviderException {
        return MOCK_SCHEMA;
    }

}
