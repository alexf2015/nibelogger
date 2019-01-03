/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package de.afriese.iot.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.afriese.iot.configuration.MainConfiguration;
import de.afriese.iot.nibe.connector.NibeuplinkConnector;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DataLoggerService {

    @Autowired
    private MainConfiguration config;

    @Autowired
    private NibeuplinkConnector nibeConnector;

    @Autowired
    private ExcelWriterService excelWriterService;
    
    @Autowired
    private ConsoleLoggerService consoleLoggerService;

    public void run() {

        for (int iter = 1; iter < 100; iter++) {
            log.info("iteration #{}", iter);
            Map<String, String> values = nibeConnector.getValues();
            log.info("finished data retrieval from Nibe Uplink...");

            // consoleLoggerService.logConsole(values);
            consoleLoggerService.logConsole(values);
            excelWriterService.writeToExcel(iter, values);
            
            try {
                Thread.sleep(60000);
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
