
public class Ca1 {

    public static void main(String[] args) {
        System.out.println("");
        
        // set counters for fail and success
        int quantSucces = 0;
        int quantFails = 0;

        // set here inputfile
        String fileName = (args.length > 0) ? args[0] : "C:/Users/valter/Documents/CCT/Java/002_CA1/src/customers.txt";
        
        // object Customer
        Customer customerAux;
        
        // instance object that handle file
        HandlerFile oHandleFile = new HandlerFile(fileName);
        
        // instance object that handle financial
        HandleFinancial oHandleFinancial = new HandleFinancial();

        // call method that build scanner object
        if (oHandleFile.oFileReader == null || oHandleFile.oFileWritter == null ) {
            return;
        }

        // procced to fetch customers from file
        while (oHandleFile.oFileReader.hasNext()) {

            // fetch next customer
            customerAux = oHandleFile.getCustomerFromFile();

            // in something wrong happened, then
            if (customerAux != null) {
            
                quantSucces++;
            
                // calculate the discount
                customerAux = oHandleFinancial.calcFinalValue(customerAux);

                oHandleFile.doWriteOnFile(customerAux);
            } else {
                quantFails++;
            }
        }

        System.out.println("Process finish ");
        System.out.println("Customers proccessed: " + (quantSucces + quantFails));
        System.out.println("Success: " + quantSucces);
        System.out.println("Fails: " + quantFails);
        
        if( quantFails > 0) {
            System.out.println("All errors were written on this file: " + oHandleFile.getFileErrorLog());
        }
    }
}
