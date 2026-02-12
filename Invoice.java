import java.util.Date;

public class Invoice {
    private double usageLimit;
    private double currentSpending;
    private Date lastDayToPay;

    public Invoice(double usageLimit){
        Date today = new Date();
        this.setUsageLimit(usageLimit);
        setCurrentSpending(0);
        long Oneday = 24*60*60*1000;
        this.setLastDayToPay(new Date(today.getTime() + (30L*Oneday)));
    }

    public Invoice(){
        setCurrentSpending(0);
    }

    public double getUsageLimit() {
        return usageLimit;
    }

    public void setUsageLimit(double usageLimit) {
        this.usageLimit = usageLimit;
    }

    public double getCurrentSpending() {
        return currentSpending;
    }

    public void setCurrentSpending(double currentSpending) {
        this.currentSpending = currentSpending;
    }

    public Date getLastDayToPay() {
        return lastDayToPay;
    }

    public void setLastDayToPay(Date lastDayToPay) {
        this.lastDayToPay = lastDayToPay;
    }

    public boolean isLimitExceeded(double amount){
        if(usageLimit - currentSpending < amount){
            return true;
        }
        return false;
    }

    public void addCost(double amount){
        setCurrentSpending(getCurrentSpending() + amount);
    }

    public void pay(double amount){
        currentSpending -= amount;
    }

    public void changeUsageLimit(double amount){
        this.setUsageLimit(amount);
    }
}