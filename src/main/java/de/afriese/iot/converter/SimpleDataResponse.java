/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package de.afriese.iot.converter;

import java.util.HashMap;
import java.util.Map;

import de.afriese.iot.DataConstants;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class SimpleDataResponse implements DataResponse {

    @Override
    public final Map<String, String> getValues(DataConstants type, String id) {
        Map<String, String> valueMap = new HashMap<>();
        if (checkDataOK()) {
            String key = type == null ? null : (id == null ? type.getId() : type.getId() + id);
            fillDataIntoMap(valueMap, key);
            return valueMap;
        }
        return null;
    }

    @Override
    public final Map<String, String> getValues(DataConstants type) {
        return getValues(type, null);
    }

    @Override
    public final Map<String, String> getValues() {
        return getValues(null, null);
    }

    protected abstract boolean checkDataOK();

    protected abstract void fillDataIntoMap(Map<String, String> valueMap, String key);

}
