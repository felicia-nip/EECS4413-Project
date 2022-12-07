package com.imooc.mall.util;

import org.apache.poi.ss.usermodel.Cell;

/**
 * Processing Excel
 */
public class ExcelUtil {

    /**
     * get current cell's value in the cell.
     * @param cell
     * @return Object type value in cell.
     */
    public static Object getCellValue(Cell cell) {
        switch (cell.getCellTypeEnum()) {
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case NUMERIC:
                return cell.getNumericCellValue();
        }

        return null;
    }
}
