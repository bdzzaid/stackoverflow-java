package zaid.opencsv.translatemappingstrategy;

import com.opencsv.*;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;

@Slf4j
@Component
public class HeaderColumnNameTranslateMappingStrategyIssue implements CommandLineRunner
{

    private Map<Class<?>, Map<String, String> > parser = new HashMap<>();

    public static final File RESOURCES_DIRECTORY = new File("/Users/zaid/IdeaProjects/stackoverflow-java/spring-boot/","src/main/resources/zaid/opencsv/translatemappingstrategy");

    //https://stackoverflow.com/questions/59774588/opencsv-problem-using-headercolumnnametranslatemappingstrategy-with-foreign-keys
    public void run(String... strings)  {

        Map<String, String> articleCols = new HashMap<>();
        articleCols.put("ean","ean");

        Map<String, String> transactionCols = new HashMap<>();
        transactionCols.put("TRANSACTIONID","transactionId");
        transactionCols.put("TIMESTAMP","timestamp");
        transactionCols.put("RECEIPTNO","receiptNo");
        transactionCols.put("RECEIPTPOSNO", "receiptPosNo");
        transactionCols.put("EAN", "ean");

        parser.put(Article.class, articleCols);
        parser.put(Transaction.class, transactionCols);


        for(Map.Entry<Class<?>, Map<String, String> > entry : parser.entrySet())
        {

            List<Transaction> transaction = new ArrayList<>();
            List<Article> articles = new ArrayList<>();


            Reader reader = null;
            try
            {
                if (entry.getKey().equals(Transaction.class))
                {
                    reader = new FileReader(new File(RESOURCES_DIRECTORY,"transaction.csv"));
                }
                else
                {
                    reader = new FileReader(new File(RESOURCES_DIRECTORY,"article.csv"));
                }
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
            Objects.requireNonNull(reader, "File reader is null");
            CSVParser parser = new CSVParserBuilder().withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                    .withSeparator(';').withIgnoreQuotations(false).build();
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(0).withCSVParser(parser).build();
            CsvToBean<?> csvToBean = new CsvToBean<>();
            csvToBean.setThrowExceptions(false);
            HeaderColumnNameTranslateMappingStrategy strategy = new HeaderColumnNameTranslateMappingStrategy();
            strategy.setType(entry.getKey());
            strategy.setColumnMapping(entry.getValue());
            csvToBean.setMappingStrategy(strategy);
            csvToBean.setCsvReader(csvReader);
            if (entry.getKey().equals(Article.class))
            {
                articles.addAll((Collection<? extends Article>)csvToBean.parse());
                articles.forEach(e -> System.out.println(e));
            }
            if (entry.getKey().equals(Transaction.class))
            {
                transaction.addAll((Collection<? extends Transaction>)csvToBean.parse());
                transaction.forEach(e -> System.out.println(e));
            }
        }
    }
}
