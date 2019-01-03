/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package de.afriese.iot.nibe.connector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import de.afriese.iot.converter.SimpleDataResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NibeuplinkDataResponse extends SimpleDataResponse {

    @Data
    public static class Value {
        private String variableId;
        private String currentValue;
    }

    private String isOffline;
    private String onlineImage;
    private String date;
    private String fuzzyDate;
    @JsonProperty("values")
    private List<Value> rawValues = new ArrayList<>();

    public String getValue(UplinkDataConstants key) {
        for (Value value : rawValues) {
            if (value.variableId.equals(key.getId())) {
                return value.currentValue;
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see de.afriese.iot.converter.SimpleDataResponse#checkDataOK()
     */
    @Override
    protected boolean checkDataOK() {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see de.afriese.iot.converter.SimpleDataResponse#fillDataIntoMap(java.util.Map, java.lang.String)
     */
    @Override
    protected void fillDataIntoMap(Map<String, String> valueMap, String key) {
        for (Value value : rawValues) {
            valueMap.put(value.variableId, value.currentValue);
        }
    }

}
