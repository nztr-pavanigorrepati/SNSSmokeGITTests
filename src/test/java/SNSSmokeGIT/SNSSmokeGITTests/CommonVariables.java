package SNSSmokeGIT.SNSSmokeGITTests;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Random;

public class CommonVariables {
    public static String futureDateString;

    static {
        LocalDate futureDate = LocalDate.now().plusDays(9);
        futureDateString = futureDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
           
    }
    
    public static String anotherFutureDateString;
   
    static {
        LocalDate futureDate = LocalDate.now().plusDays(40);
        anotherFutureDateString = futureDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));         
    }
    
    public static String prevDate ;
    
    static {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        prevDate = dateFormat.format(cal.getTime());
    }
    
    public static String currentDate;
    
    static {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate now = LocalDate.now();
        currentDate = now.format(dtf);
    }

}

