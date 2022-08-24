package ponomarev.andrei;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class ParseArchive {

    ClassLoader classLoader = ParseArchive.class.getClassLoader();



    @Test
    void zipCsvReaderTest() throws Exception {
        InputStream is = classLoader.getResourceAsStream("Test.zip");
        ZipInputStream zip = new ZipInputStream(is);
        ZipEntry entry;
        while ((entry = zip.getNextEntry()) != null) {
            if (entry.getName().contains("test.csv")) {
                try {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zip, UTF_8));
                    List<String[]> csv = csvReader.readAll();
                    assertThat(csv).contains(
                            new String[]{"phone", "model", "system"},
                            new String[]{"Iphone", "13", "ios"},
                            new String[]{"Samsung", "S10", "android"},
                            new String[]{"Alcatel", "test", "-"}
                    );
                } finally {
                    is.close();
                }
            }
        }
    }

    @Test
    void zipXLSReaderTest() throws Exception {
        InputStream is = classLoader.getResourceAsStream("Test.zip");
        ZipInputStream zis = new ZipInputStream(is);
        ZipFile zfile = new ZipFile(new File("src/test/resources/" + "Test.zip"));
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            if (entry.getName().contains("test-xls.xls")) {
                try (InputStream stream = zfile.getInputStream(entry)) {
                    XLS xls = new XLS(stream);
                    assertThat(
                            xls.excel.getSheetAt(0)
                                    .getRow(173)
                                    .getCell(2)
                                    .getStringCellValue()
                    ).contains("                Клейкие закладки");
                }
            }
        }

    }


    @Test
    void zipPdfReaderTest() throws Exception {
        InputStream is = classLoader.getResourceAsStream("Test.zip");
        ZipInputStream zip = new ZipInputStream(is);
        ZipEntry entry;
        ZipFile zfile = new ZipFile(new File("src/test/resources/" + "Test.zip"));
        while ((entry = zip.getNextEntry()) != null) {
            if (entry.getName().contains("junit-user-guide-5.9.0.pdf")) {
                try (InputStream stream = zfile.getInputStream(entry)) {
                    PDF pdf = new PDF(stream);
                    assertThat(pdf.numberOfPages).isEqualTo(179);

                }
            }
        }

    }
}

