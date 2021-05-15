package dev.jairo.controll;

import dev.jairo.model.Channel;
import dev.jairo.model.Management;
import dev.jairo.model.Subscriber;
import dev.jairo.model.Subscription;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Controller {
    private Management management;
    public static final int CHANNEL_NOT_FOUND = 0;
    public static final int SUBSCRIPTOR_NOT_FOUND = 1;
    public static final int RESTRICT_AGE = 2;
    public static final int SUCCESS = 7;

    public Controller() {
        management = new Management();
    }

    public String[] findChannel(String title){
        Channel channel = management.findChannel( title );
        if( channel != null ){
            String tit = channel.getTitle();
            String desc = channel.getDescription();
            String date = channel.getDateBegin().toString();
            String rest = channel.isRestrictionAge() ? "T" : "F";
            String[] out = {tit, desc, date, rest };

            return out;
        }

        return null;
    }

    public String[] findSubscriptor( String emailFind ){
        Subscriber subscriber = management.findSubscriber( emailFind );
        if( subscriber != null ){
            String name = subscriber.getName();
            String email = subscriber.getEmail();
            String date = subscriber.getDateBirthday().toString();
            String gender = subscriber.isGender() ? "T" : "F";

            return new String[]{name, email, date, gender };
        }

        return null;
    }

    public boolean addChannel( String ... params ){

        if( management.findChannel( params[0]) == null ){
            String title = params[0];
            String description = params[1];
            String date = params[ 2 ].replaceAll("[/]","-");
            LocalDate dateBegin = LocalDate.parse(date);
            boolean notify = params[ 3 ].equals("T") ? true : false;
            management.addChannel( new Channel(title,description,dateBegin,notify));

            return true;
        }

        return false;
    }

    public boolean addSubscriber( String ... params ){
        if( management.findSubscriber( params[1]) == null ){
            String name = params[0];
            String email = params[1];
            String date = params[ 2 ].replaceAll("[/]","-");
            LocalDate dateBirthday = LocalDate.parse(date);
            boolean gender = params[ 3 ].equals("T") ? true : false;
            management.addSubscriber( new Subscriber(name,email,dateBirthday,gender));

            return true;
        }

        return false;
    }

    public int addSubscription(String channel, String email, boolean notify ){
        Channel channelFind = management.findChannel( channel );
        if( channelFind != null ){
            Subscriber subscriber = management.findSubscriber( email );
            if( subscriber != null ){
                if (channelFind.isRestrictionAge( )  ){
                    if( subscriber.getAge() < 18 ){
                        return RESTRICT_AGE;
                    }
                }
                management.addSubscription( channelFind,subscriber,notify );
            }else{
                return SUBSCRIPTOR_NOT_FOUND;
            }
        }else{
            return CHANNEL_NOT_FOUND;
        }

        return SUCCESS;
    }

    public Object[][] getChannels(){
        ArrayList<Channel> channels = management.getChannels();
        if( channels.size() > 0 ){
            Object[][] out = new Object[channels.size()][4];
            int row = 0;
            for (Channel channel : channels) {
                out[row][0] = channel.getTitle();
                out[row][1] = channel.getDescription();
                out[row][2] = channel.getDateBegin().toString();
                out[row++][3] = channel.isRestrictionAge() ? "T" : "F";
            }

            return out;
        }

        return null;

    }

    public Object[][] getSubscribers(){
        ArrayList<Subscriber> subscribers = management.getSubscribers();
        if( subscribers.size() > 0 ){
            Object[][] out = new Object[subscribers.size()][4];
            int row = 0;
            for (Subscriber subscriber : subscribers) {
                out[row][0] = subscriber.getName();
                out[row][1] = subscriber.getEmail();
                out[row][2] = subscriber.getDateBirthday().toString();
                out[row++][3] = subscriber.isGender() ? "T" : "F";
            }

            return out;
        }

        return null;
    }

    public Object[][] getChannels(String subscriber){

        return null;

    }

    public Object[][] getSubscribers(Channel channel){

        return null;
    }

    public int countNotifyChannel(String channel, boolean notify ){

        Channel channelFind = management.findChannel( channel );
        if( channelFind != null ){

            return management.countNotify( channelFind, notify );
        }

        return -1;
    }

    public int countNotifySubscriber( String subscriber, boolean notify ){

        Subscriber subscriberFind = management.findSubscriber( subscriber );
        if( subscriberFind != null ){

            return management.countNotify( subscriberFind, notify );
        }

        return -1;
    }

    public int countGender( String channel, boolean gender ){

        return 0;
    }

    public Object[][] getSubscriptionsChannel( String channel ){
        Channel channelFind = management.findChannel( channel );
        if( channelFind != null ){
           Object[][] out = new Object[ channelFind.getSubscriptions().size()][4];
           int row = 0;
            for( Subscription subscription : channelFind.getSubscriptions()){
                String chn = subscription.getChannel().getTitle();
                String sub = subscription.getSubscriber().getEmail();
                String date = subscription.getDate().toString();
                String ntf = subscription.isNotify() ? "T" : "F";
                out[row][0] = chn;
                out[row][1] = sub;
                out[row][2] = date;
                out[row++][3] = ntf;
            }

            return out;
        }

        return null;
    }

    public Object[][] getSubscriptionsSubscriptor( String email ){
        Subscriber subscriber = management.findSubscriber(email);
        if( subscriber != null ){
            Object[][] out = new Object[ subscriber.getSubscriptions().size()][4];
            int row = 0;
            for( Subscription subscription : subscriber.getSubscriptions()){
                String chn = subscription.getChannel().getTitle();
                String sub = subscription.getSubscriber().getEmail();
                String date = subscription.getDate().toString();
                String ntf = subscription.isNotify() ? "T" : "F";
                out[row][0] = chn;
                out[row][1] = sub;
                out[row][2] = date;
                out[row++][3] = ntf;
            }

            return out;
        }

        return null;
    }

}
