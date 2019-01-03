/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package de.afriese.iot.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConsoleLoggerService {

    private Map<String, String> oldValues = new HashMap<>();

    public void logConsole(Map<String, String> values) {
        
        for (String valueId : values.keySet()) {
            String oldValue = oldValues.get(valueId);
            String newValue = values.get(valueId);
            
            if (oldValue != null && !oldValue.equals(newValue)) {
                log.info("Value changed {}: {} -> {}", valueId, oldValue, newValue);
            }
        }
        oldValues = values;

    }


}
