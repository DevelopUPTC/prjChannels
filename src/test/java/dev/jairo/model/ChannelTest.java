package dev.jairo.model;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class ChannelTest {
    private Channel channel1;
    private Channel channel2;

    private void setup(){
        channel1 = new Channel("Programacion POO","Canal de POO", LocalDate.of(2020, Month.APRIL,5), false);
        channel2 = new Channel("Videos Asombrosos","Videos de contenido censurado", LocalDate.of(2007, Month.JUNE,25), true);
        channel1.addSubscriber(new Subscriber("Jose Antonio","jose.antonio@gmail.com",LocalDate.of(1968,Month.JUNE,25),true),true );
        channel2.addSubscriber(new Subscriber("Giselle Edelmira","gedel@yahoo.com.co",LocalDate.of(1968,Month.SEPTEMBER,02),false),true );
        channel1.addSubscriber(new Subscriber("Carlos Contreras","contreras.carlos@gmail.com",LocalDate.of(1996,Month.NOVEMBER,29),true),true );
        channel1.addSubscriber(new Subscriber("Juan Esteban","juanes10@gmail.com",LocalDate.of(2004,Month.JULY,25),true),false );
        channel2.addSubscriber(new Subscriber("Carlos Sebastian","sebas.carlos@hotmail.com",LocalDate.of(2007,Month.MARCH,12),true),true );
        channel1.addSubscriber(new Subscriber("Luisa Paula","pau.luisa@gmail.com",LocalDate.of(1995,Month.JANUARY,10),false),true );
    }

    @Test
    /**
     * Método del caso de prueba que verifica el método que calcula la edad del Canal
     */
    public void testGetAge(){
        setup();
        assertEquals(1, channel1.getAge());
        assertEquals(13, channel2.getAge());
    }

    @Test
    /**
     * Método del caso de prueba que verifica el método que cuenta los suscriptores por género
     */
    public void testCountGender(){
        setup();
        assertEquals(1, channel1.countGender( false ));
        assertEquals(3, channel1.countGender( true ));
        assertEquals(1, channel2.countGender( false ));
        assertEquals(1, channel2.countGender( true ));
    }

    @Test
    /**
     * Método del caso de prueba que verifica el método que cuenta los suscriptores que tienen activas/desactivadas
     * las notificaciones
     */
    public void testCountNotify(){
        setup();
        assertEquals(1, channel1.countNotify( false ));
        assertEquals(3, channel1.countNotify( true ));
        assertEquals(0, channel2.countNotify( false ));
        assertEquals(2, channel2.countNotify( true ));
    }
}