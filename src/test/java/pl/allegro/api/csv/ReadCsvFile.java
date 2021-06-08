package pl.allegro.api.csv;

import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadCsvFile {

    public List<CategoryCsvFile> readCategoryCsvFile(InputStream fileStream) throws Exception {
        List<CategoryCsvFile> categoryList;
        ICsvMapReader mapReader = null;
        try (ICsvBeanReader beanReader = new CsvBeanReader(new InputStreamReader(fileStream),
                CsvPreference.STANDARD_PREFERENCE)) {

            final String[] headers = beanReader.getHeader(true);
            CellProcessor[] processors = new CellProcessor[headers.length];
            CategoryCsvFile category;
            categoryList = new ArrayList<>();

            while ((category = beanReader.read(CategoryCsvFile.class, headers, processors)) != null) {
                CategoryCsvFile finalCategory = category;
                categoryList.add(category);
            }

        } finally {
            if (mapReader != null) {
                mapReader.close();
            }
        }
        return categoryList;
    }
}