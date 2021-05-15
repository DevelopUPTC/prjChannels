package dev.jairo.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Subscription {
    private Channel channel;
    private Subscriber subscriber;
    private LocalDate date;
    private boolean notify;

    public Subscription(Channel channel, Subscriber subscriber, LocalDate date, boolean notify) {
        this.channel = channel;
        this.subscriber = subscriber;
        this.date = date;
        this.notify = notify;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }
}
