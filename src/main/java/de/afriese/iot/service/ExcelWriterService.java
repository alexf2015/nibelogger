/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package de.afriese.iot.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.afriese.iot.configuration.MainConfiguration;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExcelWriterService {

    @Autowired
    MainConfiguration config;

    public void writeToExcel(int iteration, Map<String, String> values) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        Workbook wb = null;
        try {
            File excel = new File(config.getExcelFile());

            fis = new FileInputStream(excel);
            wb = new XSSFWorkbook(fis);

            updateRows(wb, iteration, values);

            fis.close();
            fos = new FileOutputStream(excel);
            wb.setForceFormulaRecalculation(true);
            wb.write(fos);
        }
        catch (EncryptedDocumentException | IOException e) {
            log.error(e.getMessage(), e);

        }
        finally {
            try {
                if (fis != null)
                    fis.close();
            }
            catch (IOException e) {
            }
            try {
                if (fos != null)
                    fos.close();
            }
            catch (IOException e) {
            }
            try {
                if (wb != null)
                    wb.close();
            }
            catch (IOException e) {
            }

        }
    }

    /**
     * logs the data to all sheets, will overwrite existing values
     * 
     * @param wb
     * @param values
     */
    private void updateRows(Workbook wb, int iteration, Map<String, String> values) {
        int column = iteration + 1;

        Cell cell = findCell(wb, 1, column);
        cell.setCellValue(GregorianCalendar.getInstance().getTime().toString());

        if (iteration == 1) {
            log.info("write 'valueId-column'");
            for (String valueId : values.keySet()) {
                cell = findCell(wb, valueId, 0);
                cell.setCellValue(valueId);
            }
        }

        for (String valueId : values.keySet()) {
            String value = values.get(valueId);
            if (!value.equals("--")) {
                log.debug(valueId);
                cell = findCell(wb, valueId, column);
                cell.setCellValue(value);
            }
        }
    }

    /**
     * finds the row that corresponds to the current valueId in the given sheet
     * 
     * @param wb
     * @param sheetIdx
     * @return
     */
    private Row findCurrentRow(Workbook wb, String valueId) {
        // values start with 10000+
        int valId = Integer.valueOf(valueId);
        int rowId = valId - (valId >= 11000 ? 38999 : 9999);

        return findCurrentRow(wb, rowId);
    }

    private Row findCurrentRow(Workbook wb, int rowId) {
        Sheet sheet = wb.getSheetAt(wb.getActiveSheetIndex());
        Row r = sheet.getRow(rowId);
        if (r == null) {
            r = sheet.createRow(rowId);
        }
        return r;
    }

    private Cell findCell(Workbook wb, String valueId, int column) {
        Row row = findCurrentRow(wb, valueId);
        return findCell(wb, row, column);
    }

    private Cell findCell(Workbook wb, int rowId, int column) {
        Row row = findCurrentRow(wb, rowId);
        return findCell(wb, row, column);
    }

    private Cell findCell(Workbook wb, Row row, int column) {
        Cell cell = row.getCell(column);
        if (cell == null) {
            cell = row.createCell(column);
        }
        return cell;
    }

}
