import java.time.LocalDate;

/*
 *  this class is responsible for calculating the discounts.
*/
public class HandleFinancial {

    public Customer calcFinalValue(Customer customer) {
        double discount = 0;
        
        // Object return date/time
        final LocalDate localDate = LocalDate.now();
        
        //fetching year
        int year = localDate.getYear();

        // get discount when last purchase was in this year
        if ( year == customer.getLastPurchaseYear()) {
            switch ( customer.getClasse()) {
                case 1:
                    discount = 30;
                break;
                case 2:
                    discount = 15;
                break;            
                case 3:
                    discount = 3;
                break;            
            } 
        // get discount when last purchase was at least five years ago
        } else if ( (year - customer.getLastPurchaseYear()) <= 5) {
            switch ( customer.getClasse()) {
                case 1:
                    discount = 5;
                break;
                case 2:
                    discount = 13;
                break;            
            }
        // get discount when last purchase was before five years ago
        } else {
            switch ( customer.getClasse()) {
                case 1:
                    discount = 10;
                break;
                case 2:
                    discount = 5;
                break;            
            }
        }

        // set final value
        customer.setFinalValue( customer.getInitialValue() - ( customer.getInitialValue() * (discount/100)) );

        return customer;
    }
}
