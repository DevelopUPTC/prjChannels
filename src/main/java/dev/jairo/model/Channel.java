package dev.jairo.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Channel {
    private String title;
    private String description;
    private LocalDate dateBegin;
    private boolean restrictionAge;
    private ArrayList<Subscription> subscriptions;

    public Channel(String title, String description, LocalDate dateBegin, boolean restrictionAge) {
        this.title = title;
        this.description = description;
        this.dateBegin = dateBegin;
        this.restrictionAge = restrictionAge;
        subscriptions = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
    }

    public boolean isRestrictionAge() {
        return restrictionAge;
    }

    public void setRestrictionAge(boolean restrictionAge) {
        this.restrictionAge = restrictionAge;
    }

    public void addSubscriber(Subscriber subscriber, boolean notify ){
        subscriptions.add( new Subscription(this, subscriber, LocalDate.now(), notify ) );
    }

    public int getAge(){
        return Period.between( dateBegin,LocalDate.now()  ).getYears();
    }

    public int countGender( boolean gender ){
        return (int) subscriptions.stream().filter(subscription -> subscription.getSubscriber().isGender() == gender ).count();
    }

    public int countNotify( boolean notify ){
        return (int) subscriptions.stream().filter(subscription -> subscription.isNotify() == notify ).count();
    }

    public ArrayList<Subscription> getSubscriptions() {
        return (ArrayList<Subscription>) subscriptions.clone();
    }

    @Override
    public String toString() {
        return "Channel{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dateBegin=" + dateBegin +
                ", restrictionAge=" + restrictionAge +
                '}';
    }
}
