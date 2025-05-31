package testCases;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;
import routes.Routes;
import utils.ConfigReader;
import io.restassured.RestAssured;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BaseClass {
    ConfigReader configReader;

    //Filters for logs
    RequestLoggingFilter requestLoggingFilter;
    ResponseLoggingFilter responseLoggingFilter;
    @BeforeClass
    public void setup() throws FileNotFoundException {
        RestAssured.baseURI= Routes.BASE_URL;
        configReader=new ConfigReader();

        // Setup filters for logging
        FileOutputStream fos = new FileOutputStream(".\\logs\\test_logging.log");
        PrintStream log = new PrintStream(fos, true);

        requestLoggingFilter = new RequestLoggingFilter(log);
        responseLoggingFilter = new ResponseLoggingFilter(log);
        RestAssured.filters(requestLoggingFilter, responseLoggingFilter);
    }
    // Helper method to check if a list is sorted in descending order

    boolean isSortedDesceding(List<Integer> list)
    {
        for(int i=0;i<list.size()-1;i++)
        {
            if(list.get(i)<list.get(i+1))
            {
                return false;
            }
        }
        return true;
    }

    // Helper method to check if a list is sorted in asceding order

    boolean isSortedAsceding(List<Integer> list)
    {
        for(int i=0;i<list.size()-1;i++)
        {
            if(list.get(i)>list.get(i+1))
            {
                return false;
            }
        }
        return true;
    }
    public boolean validateCartDatesWithinRange(List<String> cartDates, String startDate, String endDate)
    {
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start=LocalDate.parse(startDate, formatter);
        LocalDate end=LocalDate.parse(endDate, formatter );
         for(String dateTime:cartDates)
         {
             LocalDate date=LocalDate.parse(dateTime.substring(0,10), formatter);
             if(date.isBefore(start)||date.isAfter(end))
             {
                 return false; // Immediately return false if any cart date is out of range
             }

         }
        return true; // All dates are within range
    }
}
