/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.ts.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Arttu Mykkänen <arttu.mykkanen@outlook.com>
 */
public class Table implements CommandLineFragment {

    private final int maxWidth;
    private final int maxHeight;
    private final List<String[]> rows;

    private String[] headers;
    private int[] columnWidths;
    
    private int actualWidth;

    private static final String LINE_SEPARATOR = "-";

    public Table(int maxWidth, int maxHeight) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
        this.rows = new ArrayList<>();
    }

    public void setHeaders(String... headers) {
        this.headers = headers;
    }

    public void setColumnWidths(int... widths) {
        this.actualWidth = Arrays.stream(widths).sum();
        if (actualWidth > maxWidth) {
            throw new IllegalArgumentException();
        }
        this.columnWidths = widths;
    }

    public void addRow(String... columnVals) {
        if (rows.size() > maxHeight) {
            // do something if necessary
        }
        this.rows.add(columnVals);
    }

    private static String fixedLength(String s, int len) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            builder.append(' ');
        }
        return s + builder.substring(s.length());
    }

    private String formatRow(String[] row) {
        for (int i = 0; i < row.length; i++) {
            row[i] = fixedLength(row[i], columnWidths[i]);
        }
        return Arrays.stream(row).collect(Collectors.joining(""));
    }

    @Override
    public String toString() {
        assert headers != null && columnWidths != null;
        assert headers.length > 1 && columnWidths.length == headers.length;
        StringBuilder sb = new StringBuilder();
        sb.append(formatRow(headers)).append('\n');
        for (int i = 0; i < maxWidth; i++) {
            sb.append(LINE_SEPARATOR);
        }
        sb.append('\n');
        for (String[] row : rows) {
            sb.append(formatRow(row)).append('\n');
        }
        return sb.toString();
    }

    @Override
    public int getWidth() {
        return actualWidth;
    }

    @Override
    public int getHeight() {
        // headers + rows + line under each one
        return (1 + rows.size() * 2);
    }

}
