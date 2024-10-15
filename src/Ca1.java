
public class Ca1 {

    public static void main(String[] args) {
        System.out.println("");

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
            customerAux = oHandleFile.GetCustomerFromFile();

            // in something wrong happened, then
            if (customerAux != null) {
                // calculate the discount
                customerAux = oHandleFinancial.calcFinalValue(customerAux);

                // print results
                System.out.println("\n------------------------------------------------");
                System.out.println(customerAux.getFirstName() + " - " + customerAux.getSecondName());
                System.out.println(String.format("%.2f", customerAux.getFinalValue()));

                oHandleFile.doWriteOnFile(customerAux);
            }
        }
    }
}
