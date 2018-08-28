/*
 * (C) Copyright 2017 Code-House and others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.code_house.eaio.uuid.jackson.adapter;

import com.eaio.uuid.UUID;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * Deserializer for eaio uuid.
 *
 * @author Łukasz Dywicki &lt;luke@code-house.org&gt;
 */
public class EaioUUIDDeserializer extends StdDeserializer<UUID> {

    public EaioUUIDDeserializer() {
        super(UUID.class);
    }

    @Override
    public UUID deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return new UUID(p.getValueAsString());
    }

}
