package cinema;

public class Statistics {

    private int income;
    private int available;
    private int purchased;

    public Statistics(){
    }

    public Statistics(int income, int available, int purchased ){
        this.income = income;
        this.available = available;
        this.purchased = purchased;
    }

    static int calculateIncome(Purchase purchase){
        if (purchase == null){
            return 0;
        }

        return purchase.getPurchasedSeatMap().values().stream()
                .map(Seats::getPrice)
                .mapToInt(Integer::intValue)
                .sum();
    }

    static int calculateAvailability(Cinema seats){
        return seats.getCinema().size();
    }

    static int calculateNumPurchased(Purchase purchase){
        if (purchase == null){
            return 0;
        }

        return purchase.getPurchasedSeatMap().size();
    }


    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getPurchased() {
        return purchased;
    }

    public void setPurchased(int purchased) {
        this.purchased = purchased;
    }
}
