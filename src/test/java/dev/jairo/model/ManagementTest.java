package dev.jairo.model;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class ManagementTest {

    private Management mng ;

    private void setup(){
        mng = new Management();
        mng.addSubscriber(new Subscriber("Jose Antonio","jose.antonio@gmail.com", LocalDate.of(1968, Month.JUNE,25),true));
        mng.addSubscriber(new Subscriber("Giselle Edelmira","gedel@yahoo.com.co",LocalDate.of(1968,Month.SEPTEMBER,02),false));
        mng.addSubscriber(new Subscriber("Juan Esteban","juanes10@gmail.com",LocalDate.of(2004,Month.JULY,25),true));

        mng.addChannel( new Channel("Anime","Canal de Anime", LocalDate.of(2017, Month.APRIL,10),true));
        mng.addChannel( new Channel("IoT","Channel of IoT", LocalDate.of(2021, Month.JANUARY,21),false));
        mng.addChannel( new Channel("POO","Programación Orientada a Objetos", LocalDate.of(2012, Month.FEBRUARY,21),true));

        mng.addSubscription(mng.findChannel("POO"),mng.findSubscriber("jose.antonio@gmail.com"),true);
        mng.addSubscription(mng.findChannel("IoT"),mng.findSubscriber("jose.antonio@gmail.com"),true);

        mng.addSubscription(mng.findChannel("Anime"),mng.findSubscriber("juanes10@gmail.com"),true);
        mng.addSubscription(mng.findChannel("IoT"),mng.findSubscriber("juanes10@gmail.com"),false);

        mng.addSubscription(mng.findChannel("Anime"),mng.findSubscriber("gedel@yahoo.com.co"),false);
    }

    @Test
    public void findChannel() {
        Management mng = new Management();
        assertNull(  mng.findChannel("Lkito"));
        mng.addChannel( new Channel("Anime","Canal de Anime", LocalDate.of(2017, Month.APRIL,10),false));
        assertNotNull(  mng.findChannel("Anime"));
        assertEquals(1, mng.getChannels().size());
    }

    @Test
    public void addChannel() {
        Management mng = new Management();
        mng.addChannel( new Channel("Anime","Canal de Anime", LocalDate.of(2017, Month.APRIL,10),true));
        mng.addChannel( new Channel("IoT","Channel of IoT", LocalDate.of(2021, Month.JANUARY,21),false));
        mng.addChannel( new Channel("POO","Programación Orientada a Objetos", LocalDate.of(2012, Month.FEBRUARY,21),true));
        assertEquals(3, mng.getChannels().size());
    }

    @Test
    public void findSubscriber() {
        Management mng = new Management();
        assertNull(mng.findSubscriber("fierro@colombia.com"));
        mng.addSubscriber( new Subscriber("Jose Antonio","jose.antonio@gmail.com", LocalDate.of(1968, Month.JUNE,25),true));
        mng.addSubscriber( new Subscriber("Juan Esteban","juanes10@gmail.com",LocalDate.of(2004,Month.JULY,25),true));
        assertNotNull(mng.findSubscriber("juanes10@gmail.com") );
        assertEquals("Juan Esteban",mng.findSubscriber("juanes10@gmail.com").getName());
        assertEquals(2, mng.getSubscribers().size());
    }

    @Test
    public void addSubscriber() {
        Management mng = new Management();
        mng.addSubscriber(new Subscriber("Jose Antonio","jose.antonio@gmail.com", LocalDate.of(1968, Month.JUNE,25),true));
        mng.addSubscriber(new Subscriber("Giselle Edelmira","gedel@yahoo.com.co",LocalDate.of(1968,Month.SEPTEMBER,02),false));
        mng.addSubscriber(new Subscriber("Juan Esteban","juanes10@gmail.com",LocalDate.of(2004,Month.JULY,25),true));
        assertEquals(3, mng.getSubscribers().size());
    }

    @Test
    public void testAddSubscription(){
        setup();
        assertEquals(1,mng.getSubscribers( mng.findChannel("POO")).size());
        assertEquals(2,mng.getSubscribers( mng.findChannel("Anime")).size());
        assertEquals(2,mng.getSubscribers( mng.findChannel("IoT")).size());

        assertEquals(2,mng.getChannels(mng.findSubscriber("jose.antonio@gmail.com")).size());
        assertEquals(2,mng.getChannels(mng.findSubscriber("juanes10@gmail.com")).size());
        assertEquals(1,mng.getChannels(mng.findSubscriber("gedel@yahoo.com.co")).size());
    }

    @Test
    /**
     * Caso de Prueba para revisar los Subscriptores de un Canal
     */
    public void testGetSubscribers(){
        setup();
        //El Canal de POO tiene un Subscriptor - Jose Antonio
        assertEquals(1, mng.getSubscribers( mng.findChannel("POO")).size());
        assertEquals("jose.antonio@gmail.com",mng.getSubscribers(mng.findChannel("POO")).get(0).getEmail());

        //El Canal de IoT tiene dos subscriptores, en la posición 0 (Jose Antonio), 1 (Juan Esteban)
        assertEquals(2, mng.getSubscribers( mng.findChannel("IoT")).size());
        assertEquals("jose.antonio@gmail.com",mng.getSubscribers(mng.findChannel("IoT")).get(0).getEmail());
        assertEquals("juanes10@gmail.com",mng.getSubscribers(mng.findChannel("IoT")).get(1).getEmail());

        //El Canal de Anime tiene dos subscriptores, Juan Esteban (0), Giselle Edelmira (1)
        assertEquals(2, mng.getSubscribers( mng.findChannel("Anime")).size());
        assertEquals("Juan Esteban",mng.getSubscribers(mng.findChannel("Anime")).get(0).getName());
        assertEquals("Giselle Edelmira",mng.getSubscribers(mng.findChannel("Anime")).get(1).getName());
    }

    @Test
    /**
     * Caso de Prueba para verificar los Canales de los Usuarios (a los que tienen subscripcion)
     */
    public void testGetChannels(){
        setup();
        //Jose Antonio tiene dos Subscripciones a POO (0) e IOT(1)
        assertEquals(2, mng.getChannels(mng.findSubscriber("jose.antonio@gmail.com")).size());
        assertEquals("POO",mng.getChannels(mng.findSubscriber("jose.antonio@gmail.com")).get(0).getTitle());
        assertEquals("IoT",mng.getChannels(mng.findSubscriber("jose.antonio@gmail.com")).get(1).getTitle());

        //Juan Estreban tiene Subscripciones Anime (0) e IoT (1)
        assertEquals(2, mng.getChannels(mng.findSubscriber("juanes10@gmail.com")).size());
        assertEquals("Anime",mng.getChannels(mng.findSubscriber("juanes10@gmail.com")).get(0).getTitle());
        assertEquals("IoT",mng.getChannels(mng.findSubscriber("juanes10@gmail.com")).get(1).getTitle());

        //Giselle tiene una Subscripción - Anime
        assertEquals(1, mng.getChannels(mng.findSubscriber("gedel@yahoo.com.co")).size());
        assertEquals("Anime",mng.getChannels(mng.findSubscriber("gedel@yahoo.com.co")).get(0).getTitle());
    }

    @Test
    /**
     * Caso de Prueba para verificar las subscipciones que tienen o no notificaciones para un canal
     * POO 1 Subscripción con Notificación
     * IoT 1 Subscripción con Notificación, 1 Subscripción sin Notificación
     * Anime 1 Subscripción con Notificación, 1 Subscripción sin Notificación
     */
    public void testCountNotifyChannels(){
        setup();
        assertEquals(1,mng.countNotify(mng.findChannel("POO"), true));
        assertEquals(0,mng.countNotify(mng.findChannel("POO"), false));

        assertEquals(1,mng.countNotify(mng.findChannel("IoT"), true));
        assertEquals(1,mng.countNotify(mng.findChannel("IoT"), false));

        assertEquals(1,mng.countNotify(mng.findChannel("Anime"), true));
        assertEquals(1,mng.countNotify(mng.findChannel("Anime"), false));
    }

    @Test
    /**
     * Caso de Prueba para verificar las subscipciones que tienen o no notificaciones para un subscriptor
     * Jose Antonio tiene dos Subscripciones con Notificaciones
     * Juan Esteban tiene una Subscripción con Notificación y una sin Notificación
     * Giselle Edelmira tiene una Subscripción sin Notificación
     */
    public void testCountNotifySubscribers(){
        setup();
        assertEquals(2, mng.countNotify(mng.findSubscriber("jose.antonio@gmail.com"),true));
        assertEquals(0, mng.countNotify(mng.findSubscriber("jose.antonio@gmail.com"),false));

        assertEquals(1, mng.countNotify(mng.findSubscriber("juanes10@gmail.com"),true));
        assertEquals(1, mng.countNotify(mng.findSubscriber("juanes10@gmail.com"),false));

        assertEquals(1, mng.countNotify(mng.findSubscriber("gedel@yahoo.com.co"),false));
        assertEquals(0, mng.countNotify(mng.findSubscriber("gedel@yahoo.com.co"),true));
    }

    @Test
    /**
     * Caso de Prueba para verificar la cuenta de género por canal
     * El Canal de POO tiene un usuario de género Maculino
     * El Canal de IoT tiene dos usuarios de género Maculino
     * El Canal de Anime tiene un usuario de género Maculino y uno Femenino
     */
    public void testCountGender(){
        setup();
        assertEquals(1, mng.countGender(mng.findChannel("POO"),true));
        assertEquals(0, mng.countGender(mng.findChannel("POO"),false));

        assertEquals(2, mng.countGender(mng.findChannel("IoT"),true));
        assertEquals(0, mng.countGender(mng.findChannel("IoT"),false));

        assertEquals(1, mng.countGender(mng.findChannel("Anime"),false));
        assertEquals(1, mng.countGender(mng.findChannel("Anime"),true));
    }
}