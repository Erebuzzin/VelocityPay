import java.util.Scanner;
import java.util.ArrayList;

public class SimulateSystem {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        ArrayList<Subscriber> subscribers = new ArrayList<Subscriber>();
        ArrayList<ServiceProvider> providers = new ArrayList<ServiceProvider>();

        while (true) {
            System.out.println("------Menu------");
            System.out.println("1- Add Service Provider");
            System.out.println("2- Add Subscriber");
            System.out.println("3- Make a Voice Call");
            System.out.println("4- Send Message");
            System.out.println("5- Buy Internet");
            System.out.println("6- Show Subscriber Info");
            System.out.println("7- Show Invoice Info");
            System.out.println("8- Remove Subscriber");
            System.out.println("9- Remove Service Provider");
            System.out.println("0- Exit");

            int choice = scn.nextInt();

            switch (choice) {
                case 1: // Add service provider
                    System.out.println("Enter provider name:");
                    String name = scn.next();
                    System.out.println("Enter discount ratio:");
                    int ratio = scn.nextInt();
                    System.out.println("Enter voice call cost:");
                    double vcost = scn.nextDouble();
                    System.out.println("Enter messaging cost:");
                    double mcost = scn.nextDouble();
                    System.out.println("Enter internet cost:");
                    double icost = scn.nextDouble();
                    ServiceProvider provider = new ServiceProvider(name, ratio, vcost, mcost, icost);
                    providers.add(provider);
                    break;
                case 2: // Add subscriber
                    System.out.println("Enter name:");
                    String s_name = scn.next();
                    System.out.println("Enter age:");
                    int s_age = scn.nextInt();
                    System.out.println("Enter limit:");
                    double s_limit = scn.nextDouble();
                    System.out.println("Choose provider ID:");
                    for (int i = 0; i < providers.size(); i++) {
                        System.out.println("Provider ID: " + providers.get(i).getP_id() + ", Name: " + providers.get(i).getP_Name());
                    }
                    int prov_id = scn.nextInt();
                    ServiceProvider chosen = null;
                    for (ServiceProvider sp : providers) {
                        if (sp.getP_id() == prov_id) {
                            chosen = sp;
                        }
                    }
                    if (chosen != null) {
                        Subscriber sub = new Subscriber(s_name, s_age, s_limit, chosen);
                        if (chosen.AddSubscriber(sub)) {
                            subscribers.add(sub);
                            System.out.println("Subscriber added successfully.");
                        } else {
                            System.out.println("Subscriber already exists in this provider.");
                        }
                    } else {
                        System.out.println("Provider not found.");
                    }
                    break;
                case 3: //
                    System.out.println("Write your id . ");
                    int callerid = scn.nextInt();
                    Subscriber caller = null;
                    for(int i = 0 ; i<subscribers.size();i++){
                        if(subscribers.get(i).getS_id() == callerid) caller = subscribers.get(i);
                    }
                    if(caller != null){
                        System.out.println("Write recieverers id .");
                        int recieverid = scn.nextInt();
                        Subscriber recieverer = null;
                        for(int i = 0 ; i<subscribers.size();i++){
                            if(subscribers.get(i).getS_id() == recieverid) recieverer = subscribers.get(i);
                        }
                        if(recieverer !=null){
                            System.out.println("How long will your call take (minute) ");
                             int Minute  = scn.nextInt();
                             double voicecallcost = caller.getS_provider().calculateVoiceCallCost(Minute,caller);
                             if(!caller.getInvoice().isLimitExceeded(voicecallcost) && Minute>0) {
                                 caller.makeVoiceCall(Minute, caller);
                                 System.out.println("Voice call successfully occured  .");
                             }else{
                                 System.out.println("The voice call operation cannot be completed Due To İnvalid İnput or Exceeding limit");
                             }
                        }else{
                            System.out.println("Subscriber recieverer cannot be found ");
                        }
                    }else{
                        System.out.println("Caller subscriber cannot be found ...");
                    }
                    break;
                case 4: // Send Message
                    System.out.println("Enter your ID:");
                    int senderid = scn.nextInt();
                    Subscriber sender = null;
                    for (Subscriber s : subscribers) {
                        if (s.getS_id() == senderid) sender = s;
                    }
                    if (sender != null) {
                        System.out.println("Enter receiver ID:");
                        int recid = scn.nextInt();
                        Subscriber receiver = null;
                        for (Subscriber s : subscribers) {
                            if (s.getS_id() == recid) receiver = s;
                        }
                        if (receiver != null) {
                            System.out.println("Enter number of messages:");
                            int count = scn.nextInt();
                            double cost = sender.getS_provider().calculateMessagingCost(count, sender);
                            if (!sender.getInvoice().isLimitExceeded(cost) && count > 0) {
                                sender.sendMessage(count, sender);
                                System.out.println("Messages sent successfully.");
                            } else {
                                System.out.println("Cannot send message due to invalid input or exceeded limit.");
                            }
                        } else {
                            System.out.println("Receiver not found.");
                        }
                    } else {
                        System.out.println("Sender not found.");
                    }
                    break;
                case 5: // Buy Internet
                    System.out.println("Enter your ID:");
                    int buyerid = scn.nextInt();
                    Subscriber buyer = null;
                    for (Subscriber s : subscribers) {
                        if (s.getS_id() == buyerid) buyer = s;
                    }
                    if (buyer != null) {
                        System.out.println("Enter internet amount (GB):");
                        double gb = scn.nextDouble();
                        double cost = buyer.getS_provider().calculateInternetCost(gb, buyer);
                        if (!buyer.getInvoice().isLimitExceeded(cost) && gb > 0) {
                            buyer.buyInternet(gb, buyer);
                            System.out.println("Internet purchased successfully.");
                        } else {
                            System.out.println("Cannot complete purchase due to invalid input or exceeded limit.");
                        }
                    } else {
                        System.out.println("Subscriber not found.");
                    }
                    break;
                case 6: // Show Subscriber Info
                    for (Subscriber s : subscribers) {
                        System.out.println(s);
                    }
                    break;
                case 7: // Show Invoice Info
                    System.out.println("Enter subscriber ID:");
                    int sid = scn.nextInt();
                    Subscriber target = null;
                    for (Subscriber s : subscribers) {
                        if (s.getS_id() == sid) target = s;
                    }
                    if (target != null) {
                        System.out.println(target.getInvoice());
                    } else {
                        System.out.println("Subscriber not found.");
                    }
                    break;
                case 8: // Remove Subscriber
                    System.out.println("Enter subscriber ID to remove:");
                    int rid = scn.nextInt();
                    Subscriber toRemove = null;
                    for (Subscriber s : subscribers) {
                        if (s.getS_id() == rid) toRemove = s;
                    }
                    if (toRemove != null) {
                        toRemove.getS_provider().removeSubscriber(toRemove);
                        subscribers.remove(toRemove);
                        System.out.println("Subscriber removed.");
                    } else {
                        System.out.println("Subscriber not found.");
                    }
                    break;
                case 9: // Remove Service Provider
                    System.out.println("Enter provider ID to remove:");
                    int pid = scn.nextInt();
                    ServiceProvider toRemoveP = null;
                    for (ServiceProvider sp : providers) {
                        if (sp.getP_id() == pid) toRemoveP = sp;
                    }
                    if (toRemoveP != null) {
                        providers.remove(toRemoveP);
                        System.out.println("Provider removed.");
                    } else {
                        System.out.println("Provider not found.");
                    }
                    break;
                case 0:
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}