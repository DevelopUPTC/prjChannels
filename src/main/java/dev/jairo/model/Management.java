package dev.jairo.model;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class Management {
    private ArrayList<Channel> channels;
    private ArrayList<Subscriber> subscribers;

    public Management() {
        channels = new ArrayList<>();
        subscribers = new ArrayList<>();
    }

    public Channel findChannel( String title ){
        Optional<Channel> channel = channels.stream()
                .filter( student -> title.equals( student.getTitle()))
                .findFirst();

        return channel.isPresent() ? channel.get() : null;
    }

    public void addChannel( Channel channel ){
        channels.add( channel );
    }

    public Subscriber findSubscriber( String email ){
        Optional<Subscriber> subscriber = subscribers.stream()
                .filter(subs -> email.equals(subs.getEmail()))
                .findFirst();

        return subscriber.isPresent() ? subscriber.get() : null;
    }

    public void addSubscriber( Subscriber subscriber){
        subscribers.add( subscriber);
    }

    public void addSubscription( Channel channel, Subscriber subscriber, boolean notify ){
        channel.addSubscriber(subscriber,notify);
        subscriber.addChannel(channel,notify);
    }

    public int countNotify( Channel channel, boolean notify ){

        return channel.countNotify( notify );
    }

    public int countNotify(Subscriber subscriber, boolean notify ){
        return subscriber.countNotify( notify );
    }

    public int countGender( Channel channel, boolean gender ){

        return channel.countGender( gender );
    }

    public ArrayList<Channel> getChannels(Subscriber subscriber ){

        return  (ArrayList<Channel>) subscriber.getSubscriptions()
                .stream().map( subscription -> subscription.getChannel())
                .collect(Collectors.toList());
    }

    public ArrayList<Subscriber> getSubscribers( Channel channel ){
        return (ArrayList<Subscriber>) channel.getSubscriptions()
                .stream().map( subscription -> subscription.getSubscriber() )
                .collect(Collectors.toList());
    }

    public ArrayList<Channel> getChannels() {
        return (ArrayList<Channel>) channels.clone();
    }

    public ArrayList<Subscriber> getSubscribers() {
        return (ArrayList<Subscriber>) subscribers.clone();
    }
}
