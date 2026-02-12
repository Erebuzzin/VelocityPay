import java.util.ArrayList;
import java.util.Date;

public class ServiceProvider {
    private int p_id =500; // must be unique 500-600
    private String p_name ;
    private double voiceCallCost;
    private double messagingCost;
    private double internetCost;
    private int discountRatio;
    private static int NumberOfServiceProviders;
    private ArrayList<Subscriber> subscribersList;
    public ServiceProvider(String name,int discountRatio,double voiceCallCost,double messagingCost,double internetCost){
        if(getNumberOfServiceProviders()==100){ // 600-500
            System.out.println("You reached limit of the maximum service provider number cannot apply one more");
            return ;

        }
        this.internetCost = internetCost;
        NumberOfServiceProviders +=1;
        this.p_name= name;
        this.discountRatio = discountRatio;
        this.p_id = getP_id() + NumberOfServiceProviders;
        NumberOfServiceProviders +=1;
        this.messagingCost = messagingCost;
        this.voiceCallCost = voiceCallCost;
        this.subscribersList = new ArrayList<Subscriber>();

    }

    public static int getNumberOfServiceProviders() {
        return NumberOfServiceProviders;
    }

    public static void setNumberOfServiceProviders(int numberOfServiceProviders) {
        NumberOfServiceProviders = numberOfServiceProviders;
    }


    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_Name() {
        return p_name;
    }

    public void setName(String name) {
        this.p_name = name;
    }

    public double getVoiceCallCost() {
        return voiceCallCost;
    }

    public void setVoiceCallCost(double voiceCallCost) {
        this.voiceCallCost = voiceCallCost;
    }

    public double getMessagingCost() {
        return messagingCost;
    }

    public void setMessagingCost(double messagingCost) {
        this.messagingCost = messagingCost;
    }

    public double getInternetCost() {
        return internetCost;
    }

    public void setInternetCost(double internetCost) {
        this.internetCost = internetCost;
    }

    public int getDiscountRatio() {
        return discountRatio;
    }

    public void setDiscountRatio(int disciountRatio) {
        this.discountRatio = disciountRatio;
    }

    public ArrayList<Subscriber> getSubscribersList() {
        return subscribersList;
    }

    public void setSubscribersList(ArrayList<Subscriber> subscribersList) {
        this.subscribersList = subscribersList;
    }
    public double calculateVoiceCallCost(int minute , Subscriber caller){
        Date date = new Date();
        double cost =caller.getS_provider().voiceCallCost*minute;
        int hour =date.getHours(); // to learn hour of date
        // will calculate the total amount of call
      if(hour>=22 || hour <=6){
          if(caller.getAge()<18){
              return caller.getS_provider().voiceCallCost * minute * (1 - (double)(discountRatio + 10) / 100); // discountratio + 10 is if the user is beneffiting from 2 discounts ( hour and age )
          }if(caller.getAge()>65){
              return caller.getS_provider().voiceCallCost * minute * (1 - (double)(discountRatio + 10) / 100);
          }else{
              return caller.getS_provider().voiceCallCost * minute * (1 - (double)(discountRatio ) / 100); // Discount of only hour
          }
      }
        if(caller.getAge()<18){ // Which are can use only discount of age
            return caller.getS_provider().voiceCallCost * minute * (1 - (double)(discountRatio ) / 100);
        }if(caller.getAge()>65){
            return caller.getS_provider().voiceCallCost * minute * (1 - (double)(discountRatio ) / 100);
        }
        return minute*caller.getS_provider().voiceCallCost;


    }
    public double calculateMessagingCost(int quantity,Subscriber sender){
        // Original cost  2.13
        // Will calculate the quantity of Message

        if(sender.getAge()<18){ // STUDENT DİSCOUNT
            if(quantity<=10){ // ONLY STUDENT DİSCOUNT
                double cost = sender.getS_provider().messagingCost * quantity * (1 - (double)discountRatio / 100);
                return cost;
            }
            if(quantity>10){ // DİSCOUNT Which is student and quantity bonus
                int discountedmessages = quantity-10;
                double discountedmessagescost = discountedmessages * sender.getS_provider().messagingCost * (1 - (double)(discountRatio + 10) / 100);
                double cost = (sender.getS_provider().messagingCost*10) + discountedmessagescost; // To discount messages longer than 10
                return cost;
            }



        }
       else if(quantity>10){// ONLY QUANTİTY DİSCOUNT
           if(sender.getAge()>65){
               int discountedmessages = quantity-10; // Whıch uses age and quantity  discount
               double discountedmessagescost = discountedmessages * sender.getS_provider().messagingCost * (1 - (double)(discountRatio + 10) / 100);
               double cost = (sender.getS_provider().messagingCost*10) + discountedmessagescost; // To discount messages longer than 10
               return cost;

           }
            int discountedmessages = quantity-10;
            double discountedmessagescost = discountedmessages * sender.getS_provider().messagingCost * (1 - (double)(discountRatio ) / 100); // Which uses only quantity discount
            double cost = (sender.getS_provider().messagingCost*10)+discountedmessagescost; // To discount messages longer than 10
            return cost;


        }
       double cost =sender.getS_provider().messagingCost *quantity; // Uses Anything
       return cost;


    }
    public double calculateInternetCost(double amount,Subscriber buyer){//
        Date today = new Date();
        int hour = today.getHours();
        if(hour<6 ){ // 0-6 AM
            if(buyer.getAge()<18){
                return buyer.getS_provider().getInternetCost() * amount * (1 - (double)(discountRatio + 10) / 100);
                // The Discount of 18- students and Discountive hours
            }else if(buyer.getAge()>65){
                return buyer.getS_provider().getInternetCost() * amount * (1 - (double)(discountRatio + 10) / 100);
                // The Discount of Senior Citizens and Discountive hours
            }

        }
        if(buyer.getAge()<18){ // The only 18- Student Discount
            return buyer.getS_provider().getInternetCost() * amount * (1 - (double)(discountRatio ) / 100);


        }
        if(buyer.getAge()>65){ // Only Senior citizens discount
            return buyer.getS_provider().getInternetCost() * amount * (1 - (double)(discountRatio ) / 100);

        }

        return buyer.getS_provider().getInternetCost()*amount;
    }
    public boolean AddSubscriber(Subscriber s){


        for(int i = 0 ; i<subscribersList.size();i++){
            if(s.getName().equals(subscribersList.get(i).getName())){
                return false;
            }
        }
        subscribersList.add(s);
        return true;

    }
    public boolean removeSubscriber(Subscriber s){
        subscribersList.remove(s);
        for(int i = 0 ; i<subscribersList.size();i++){
            if(s.getName().equals(subscribersList.get(i).getName())){
                subscribersList.remove(subscribersList.get(i));
                return true;
            }
        }
        return false;
    }

}