
public class Ca1 {

    public static void main(String[] args) {
        String fileName = "C:\\Users\\valter\\Documents\\CCT\\Java\\002_CA1\\src\\customers.txt";
        Customer customer;

        // instance object that handle file
        HandlerFile oHandleFile = new HandlerFile();

        // instance object that handle financial
        HandleFinancial oHandleFinancial = new HandleFinancial();

        // call method that build scanner object
        if (!oHandleFile.buildScanner(fileName)) {
            return;
        }

        // procced to fetch customers from file
        while (oHandleFile.oScanner.hasNext()) {

            // fetch next customer
            customer = oHandleFile.GetCustomerFromFile();

            // in something wrong happened, then
            if (customer != null) {
                // calculate the discount
                customer = oHandleFinancial.calcFinalValue(customer);

                // print results
                System.out.println("\n------------------------------------------------");
                System.out.println(customer.getFirstName() + " - " + customer.getSecondName());
                System.out.println(String.format("%.2f", customer.getFinalValue()));

            }

        }

    }
}
