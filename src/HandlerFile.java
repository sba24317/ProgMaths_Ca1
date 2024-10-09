import java.io.File;
import java.util.Scanner;

/*
 * this class will be resposible for operations
 */
public class HandlerFile {
    // object Scanner
    Scanner oScanner;
    
    String aux;

    // indicates line was read.
    int linePosition;

    // classe constructor
    HandlerFile() {
        this.linePosition = 0;
    }

    /* 
    *  method to instate object scanner
    *  return true if success, false if fails 
    */
    public boolean buildScanner(String fileName) {
        File file;
        boolean result = true;

        // try instaces one object Scanner
        try {
            file = new File(fileName);

            this.oScanner = new Scanner(file);

        } catch (Exception e) {
            System.out.println("\nError fetching file: <" + fileName + "> Error is:" + e.getMessage());
            result = false;
        }
        return result;
    }

    /*
     * this method will intereact with file and returns a Customer object
     */
    public Customer GetCustomerFromFile() {
        String aux;
        String[] names;
        Customer myCustomer = new Customer();

        try {
            // read from file name ans second name
            names = this.oScanner.nextLine().split(" ");
            this.linePosition++;

            // test if is a valid string otherwise assing values
            if (names.length < 1) {
                throw new Exception("Could not find a line with first and second name");
            } else {
                // check if content for first name is valid
                if (names[0].matches("[a-zA-Z]+"))
                    myCustomer.setFirstName(names[0]);
                else
                    throw new Exception("First name should be letters");

                // check if content for second name is valid
                if (names[1].matches("[a-zA-Z0-9]+"))
                    myCustomer.setSecondName(names[1]);
                else
                    throw new Exception("Second name should be letter/number");
            }

            // read file again to fetch value
            aux = this.oScanner.nextLine();
            linePosition++;

            // check if content for initial value is valid
            if (aux.matches("[0-9.]+"))
                myCustomer.setInitialValue(Double.parseDouble(aux));
            else
                throw new Exception("Value for inicital value should be numeric");

            // read file again to fetch value for classe
            aux = oScanner.nextLine();
            linePosition++;

            // check if content for classe value is valid
            if (aux.matches("[1-3]"))
                myCustomer.setClasse(Integer.parseInt(aux));
            else
                throw new Exception("Value for classe should be 1/2/3");

            // read file again to fetch value for last purchase
            aux = this.oScanner.nextLine();
            linePosition++;

            // check if content for last purchase is valid
            if (aux.matches("^(19|20)\\d{2}$"))
                myCustomer.setLastPurchaseYear(Integer.parseInt(aux));
            else
                throw new Exception("Value for year should be numeric from 1900 to 2099");

        } catch (Exception e) {
            // print error to console
            System.out.println("----------------------------");
            System.out.println("An error occur while fetchin customers, Error reading line: " + linePosition + " Error: " + e.getMessage());

            // push the pointer in file to next line that represents a customer
            for (int i = (linePosition % 4); i < 4; i++) {
                if (oScanner.hasNext()) {
                    oScanner.nextLine();
                    linePosition++;
                }
            }
            myCustomer = null;
        }
        return myCustomer;
    }
}
