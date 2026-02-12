public class Subscriber {
    private static int counter = 1000;
    private int s_id;
    private String s_name;
    private int s_age;
    private double s_limit;
    private ServiceProvider s_provider;
    private Invoice invoice;

    public Subscriber(String s_name, int s_age, double s_limit, ServiceProvider s_provider) {
        this.s_id = counter++;
        this.s_name = s_name;
        this.s_age = s_age;
        this.s_limit = s_limit;
        this.s_provider = s_provider;
        this.invoice = new Invoice();
    }

    public void makeVoiceCall(int minute, Subscriber from) {
        double cost = s_provider.calculateVoiceCallCost(minute, from);
        invoice.updateInvoice("voice", cost);
    }

    public void sendMessage(int count, Subscriber from) {
        double cost = s_provider.calculateMessagingCost(count, from);
        invoice.updateInvoice("message", cost);
    }

    public void buyInternet(double gb, Subscriber from) {
        double cost = s_provider.calculateInternetCost(gb, from);
        invoice.updateInvoice("internet", cost);
    }

    public int getS_id() {
        return s_id;
    }

    public String getS_name() {
        return s_name;
    }

    public int getS_age() {
        return s_age;
    }

    public double getS_limit() {
        return s_limit;
    }

    public ServiceProvider getS_provider() {
        return s_provider;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    @Override
    public String toString() {
        return "Subscriber ID: " + s_id +
               ", Name: " + s_name +
               ", Age: " + s_age +
               ", Limit: " + s_limit +
               ", Provider: " + s_provider.getP_Name();
    }
}