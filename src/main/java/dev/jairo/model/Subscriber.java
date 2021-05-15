package dev.jairo.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Subscriber {
    private String name;
    private String email;
    private LocalDate dateBirthday;
    private boolean gender;
    private ArrayList<Subscription> subscriptions;

    public Subscriber(String name, String email, LocalDate dateBirthday, boolean gender) {
        this.name = name;
        this.email = email;
        this.dateBirthday = dateBirthday;
        this.gender = gender;
        subscriptions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateBirthday() {
        return dateBirthday;
    }

    public void setDateBirthday(LocalDate dateBirthday) {
        this.dateBirthday = dateBirthday;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void addChannel(  Channel channel, boolean notify ){
        subscriptions.add( new Subscription(channel,this,LocalDate.now(), notify ) );
    }

    public int getAge(){
        return Period.between( dateBirthday,LocalDate.now() ).getYears();
    }

    public int countNotify( boolean notify ){

        return (int) subscriptions.stream().filter(subscription -> subscription.isNotify() == notify ).count();
    }

    public ArrayList<Subscription> getSubscriptions() {
        return (ArrayList<Subscription>) subscriptions.clone();
    }

    @Override
    public String toString() {
        return "Subscriber{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dateBirthday=" + dateBirthday +
                ", gender=" + gender +
                '}';
    }
}
