package parsing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class CsvToSQL {

    private static final SimpleDateFormat CSV_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
    private static final SimpleDateFormat SQL_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final Set<SecurityPrice> securityPrices;

    public CsvToSQL(InputStream input) {
        securityPrices = new LinkedHashSet<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                securityPrices.addAll(readCsvLine(currentLine));

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sql = new StringBuilder();
        Date previousDate = null;
        for (SecurityPrice securityPrice : securityPrices) {
            if (!securityPrice.date.equals(previousDate)) {
                if (sql.length() > 0) {
                    sql.append(";\n");
                }
                sql.append("insert into price (date, value, security_ticker) values");
            } else {
                sql.append(',');
            }
            sql.append(securityPrice.toString());
            previousDate = securityPrice.date;
        }
        sql.append(";");
        return sql.toString();
    }

    private Set<SecurityPrice> readCsvLine(String line) {
        final Set<SecurityPrice> securityPrices = new LinkedHashSet<>();
        Date date = null;
        SecurityPrice securityPrice = null;
        for (String element : line.split(",")) {
            if (date == null) {
                try {
                    date = CSV_DATE_FORMAT.parse(element);
                } catch (ParseException e) {
                    throw new IllegalArgumentException("csv doesn't starts with well formed date");
                }
            } else if (securityPrice == null) {
                securityPrice = new SecurityPrice(date, element);
            } else {
                securityPrice.setPrice(element);
                securityPrices.add(securityPrice);
                securityPrice = null;
            }
        }
        return securityPrices;
    }

    private class SecurityPrice {

        private final Date date;
        private String security;
        private Float price;

        public SecurityPrice(Date date, String security) {
            this.date = date;
            this.security = security;
        }

        public void setPrice(String price) {
            this.price = new Float(price);
        }

        @Override
        public String toString() {
            return " ('" + SQL_DATE_FORMAT.format(date) + "', " + price + ", '" + security + "')";
        }

    }

    public static void main(String[] args) {
        System.out.println(new CsvToSQL(System.in).toString());
    }

}
