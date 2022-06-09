public class Account{

    // Denotes the currencies "dollars" and "cents"
    private int dollars;
    private int cents;

    Account(){
        this.dollars=0;
        this.cents=0;
    }

    // This function converts the given amount to denominations of "dollars" and "cents"
    public int[] convertDollarsAndCents(String amount) throws Exception{
        // This is used to store the value of dollar at index "0"
        // and also the value of cent at index "1"
        int[] values=new int[2];

        if (amount.equals("")){
            return values;
        }

        String[] currencies=amount.split(" ");

        if (currencies.length > 2){
            throw new Exception("Improper denominations for the given amount");
        }

        // If there are two currencies
        if(currencies.length == 2){
            String firstCurrencyValue = currencies[0];
            String secondCurrencyValue = currencies[1];

            // Check Whether the last character for the first currency is 'D'
            if(firstCurrencyValue.charAt(firstCurrencyValue.length()-1) == 'D'){
                values[0]=Integer.parseInt(firstCurrencyValue.substring(0, firstCurrencyValue.length()-1));
            }
            else{
                throw new Exception("Improper input format");
            }

            // Check Whether the last character for the second currency is 'C'
            if(secondCurrencyValue.charAt(secondCurrencyValue.length()-1) == 'C'){
                values[1]=Integer.parseInt(secondCurrencyValue.substring(0, secondCurrencyValue.length()-1));
            }
            else{
                throw new Exception("Improper input format");
            }
        }
        else{
            String currencyValue=currencies[0];
            if(currencyValue.charAt(currencyValue.length()-1)=='D'){
                values[0]=Integer.parseInt(currencyValue.substring(0, currencyValue.length()-1));
            }
            else if(currencyValue.charAt(currencyValue.length()-1)=='C'){
                values[1]=Integer.parseInt(currencyValue.substring(0, currencyValue.length()-1));
            }
            else{
                throw new Exception("Improper input format");
            }
        }

        return values;
    }

    // This function adds money to the current balance by retrieving
    // the dollars and cents from the given amount
    public void creditBalance(String amount){
        try{
            int[] currentMoney=convertDollarsAndCents(amount);

            // All the denominations should be in between -100 to 100
            if(currentMoney[0] > 100 || currentMoney[1] > 100 || -100 > currentMoney[0] || -100 > currentMoney[1]){
                // throw new Exception("Bank doesn't accept the currency values");
                return;
            }

            // Update the current balance by adding money
            int currentDollarValue = this.getDollarValue() + currentMoney[0];
            int currentCentValue = this.getCentValue() + currentMoney[1];

            long totalAmount = (long)currentDollarValue*100L + currentCentValue;

            // Total amount should not exceed 10^9
            if(totalAmount > 1000 * 1000 * 1000 * 100){
                // throw new Exception("Bank deposit limit exceeded");
                return;
            }
            else{
                this.setAmount(currentDollarValue, currentCentValue);
                System.out.println("Successful!");
            }
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    // This function withdraws money from the current balance by retrieving
    // the dollars and cents from the given amount
    public void debitBalance(String amount){
        try{

            int[] currentMoney=convertDollarsAndCents(amount);

            // All the denominations should be in between -100 to 100
            // if(currentMoney[0] > 100 || currentMoney[1] > 100 || -100 > currentMoney[0] || -100 > currentMoney[1]){
            // throw new Exception("Bank doesn't withdraws the currency values");
            // return;
            // }

            // Update the current balance by withdrawing money
            int currentDollarValue = this.getDollarValue() - currentMoney[0];
            int currentCentValue = this.getCentValue() - currentMoney[1];

            this.setAmount(currentDollarValue, currentCentValue);
            System.out.println("Done!");
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    // This function retrieves the current balance of the user
    public String checkBalance(){
        return Long.toString(this.getDollarValue())+"D "+Integer.toString(this.getCentValue())+"C";
    }

    private int getDollarValue(){
        return this.dollars;
    }

    private int getCentValue(){
        return this.cents;
    }

    private void setAmount(int dollars, int cents){
        this.dollars=dollars;
        this.cents=cents;
    }
}