/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package de.afriese.iot.converter;

import java.util.Map;

import de.afriese.iot.DataConstants;

public interface DataResponse {
    public Map<String, String> getValues();

    public Map<String, String> getValues(DataConstants type);

    public Map<String, String> getValues(DataConstants type, String id);
}
