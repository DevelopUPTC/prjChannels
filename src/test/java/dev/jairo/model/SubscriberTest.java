package dev.jairo.model;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class SubscriberTest {
    private Subscriber jose;
    private Subscriber giselle;
    private Subscriber juanes;

    private void setup( ){
        jose = new Subscriber("Jose Antonio","jose.antonio@gmail.com", LocalDate.of(1968, Month.JUNE,25),true);
        giselle = new Subscriber("Giselle Edelmira","gedel@yahoo.com.co",LocalDate.of(1968,Month.SEPTEMBER,02),false);
        juanes = new Subscriber("Juan Esteban","juanes10@gmail.com",LocalDate.of(2004,Month.JULY,25),true);

        jose.addChannel( new Channel("POO","Programación Orientada a Objetos",LocalDate.of(2008,Month.MARCH,23),false),true);
        jose.addChannel( new Channel("Trucos Caseros","Ejemplos de uso de Herramientas",LocalDate.of(2012,Month.OCTOBER,5),true),true);
        jose.addChannel( new Channel("Runnig23","Canal para Runnings",LocalDate.of(2016,Month.DECEMBER,17),true),false);

        giselle.addChannel(new Channel("Lenceria","Moda Femenina",LocalDate.of(2016,Month.JULY,2),true),true);
        giselle.addChannel(new Channel("MusicTK","Canal de Música",LocalDate.of(2012,Month.MAY,23),false),false);

        juanes.addChannel(new Channel("Anime","Canal de Anime",LocalDate.of(2017,Month.OCTOBER,11),false), true);
        juanes.addChannel(new Channel("Gamer","Juegos y más",LocalDate.of(2018,Month.NOVEMBER,30),false), true);
    }

    @Test
    /**
     * Método de caso de prueba para la funcionalidad de calcular la edad
     */
    public void testGetAge(){
        setup();
        assertEquals(52,jose.getAge());
        assertEquals(52,giselle.getAge());
        assertEquals(16,juanes.getAge());
    }

    @Test
    /**
     * Caso de prueba para contar las notificaciones que se han activado a los canales suscritos
     */
    public void testCountNotify(){
        setup();
        assertEquals(2,jose.countNotify(true));
        assertEquals(1,jose.countNotify(false));

        assertEquals(1,giselle.countNotify(false));
        assertEquals(1,giselle.countNotify(true));

        assertEquals(2,juanes.countNotify(true));
        assertEquals(0,juanes.countNotify(false));
    }

}