package dev.jairo.model;

import dev.jairo.controll.Controller;
import org.junit.Test;

import static org.junit.Assert.*;

public class ControllerTest {
    private Controller ctr;

    private void setup(){
        ctr = new Controller( );
        ctr.addChannel("POO","Programación Orientada a Objetos","2015-02-17","F");
        ctr.addChannel("IoT","Internet de las Cosas","2017-10-03","F");
        ctr.addChannel("Anime","Canal de anime","2018-11-27","T");
        ctr.addChannel("Gun","Canal de Armas","2021-04-02","T");

        ctr.addSubscriber("Jose Antonio","jose.antonio@gmail.com","1979-05-01","T");
        ctr.addSubscriber("Juan Esteban","juanes10@gmail.com","2001-10-23","T");
        ctr.addSubscriber("Giselle Edelmira","giedel@yahoo.es","1993/07/21","F");

        ctr.addSubscription("POO","jose.antonio@gmail.com",true);
        ctr.addSubscription("Gun","jose.antonio@gmail.com",false);
        ctr.addSubscription("IoT","jose.antonio@gmail.com",false);

        ctr.addSubscription("IoT","juanes10@gmail.com",true);
        ctr.addSubscription("Anime","juanes10@gmail.com",false);

        ctr.addSubscription("Anime","giedel@yahoo.es",true);
    }

    @Test
    public void findChannel() {
        ctr = new Controller( );
        assertNull( ctr.findChannel("KTK"));
        assertTrue( ctr.addChannel( "IoT","Internet de las Cosas","2017/06/26","T"));
        assertNotNull(ctr.findChannel("IoT"));
        assertEquals("IoT",ctr.findChannel("IoT")[0]);
    }

    @Test
    public void findSubscriptor() {
        ctr = new Controller( );
        assertNull( ctr.findSubscriptor("jose.antonio@gmail.com"));
        assertTrue( ctr.addSubscriber("Jose Antonio","jose.antonio@gmail.com","1979/05/01","T"));
        assertNotNull( ctr.findSubscriptor("jose.antonio@gmail.com"));
        assertEquals("Jose Antonio",ctr.findSubscriptor("jose.antonio@gmail.com")[0]);
    }

    @Test
    public void addChannel() {
        ctr = new Controller( );
        assertTrue( ctr.addChannel( "IoT","Internet de las Cosas","2017/06/26","F"));
        assertFalse( ctr.addChannel( "IoT","Internet de las Cosas","2017/06/26","T"));
        assertTrue( ctr.addChannel( "Anime","Canal de Animes","2016-01-12","F"));
        assertEquals("Anime",ctr.findChannel("Anime")[0]);
    }

    @Test
    public void addSubscriber() {
        ctr = new Controller( );
        assertTrue( ctr.addSubscriber("Jose Antonio","jose.antonio@gmail.com","1979/05/01","T"));
        assertFalse( ctr.addSubscriber("Jose Antonio","jose.antonio@gmail.com","1979/05/01","T"));
        assertTrue( ctr.addSubscriber("Giselle Edelmira","gedel@yahoo.com.co","2000/12/23","F"));
        assertEquals("jose.antonio@gmail.com",ctr.findSubscriptor("jose.antonio@gmail.com")[1]);
    }

    @Test
    public void addSubscription() {
        setup( );
        assertEquals( Controller.SUBSCRIPTOR_NOT_FOUND,ctr.addSubscription("POO","kikelon@hotmail.com",false));
        assertEquals( Controller.CHANNEL_NOT_FOUND,ctr.addSubscription("TKT","jose.antonio@gmail.com", false));
        assertTrue( ctr.addSubscriber("Julio Enrique","kikelon@hotmail.com","2010/06/12","T"));
        assertEquals( Controller.SUCCESS, ctr.addSubscription("Gun","juanes10@gmail.com",true));
        assertEquals(Controller.RESTRICT_AGE, ctr.addSubscription("Gun","kikelon@hotmail.com",true));
    }

    @Test
    public void testGetChannels( ){
        setup();
        Object[][] data = ctr.getChannels();
        assertEquals("POO",data[0][0]);
        assertEquals("IoT",data[1][0]);
        assertEquals("Anime",data[2][0]);
        assertEquals("Gun",data[3][0]);
        assertEquals("T",data[3][3]);
    }

    @Test
    public void testGetSubscribers(){
        setup();
        Object[][] data = ctr.getSubscribers();
        assertEquals("jose.antonio@gmail.com",data[0][1]);
        assertEquals("2001-10-23",data[1][2]);
        assertEquals("1993-07-21",data[2][2]);
    }

    @Test
    public void countNotifyChannel() {
        setup();
        assertEquals(1, ctr.countNotifyChannel("POO",true));
        assertEquals(0, ctr.countNotifyChannel("POO",false));
        assertEquals(1, ctr.countNotifyChannel("IoT",true));
        assertEquals(1, ctr.countNotifyChannel("IoT",false));
        assertEquals(1, ctr.countNotifyChannel("Gun",false));
        assertEquals(0, ctr.countNotifyChannel("Gun",true));
        assertEquals(1, ctr.countNotifyChannel("Anime",true));
        assertEquals(1, ctr.countNotifyChannel("Anime",false));
        assertEquals(-1, ctr.countNotifyChannel("Technology",false));
    }

    @Test
    public void countNotifySubscriber() {
        setup();
        assertEquals(1, ctr.countNotifySubscriber("jose.antonio@gmail.com",true));
        assertEquals(2, ctr.countNotifySubscriber("jose.antonio@gmail.com",false));
        assertEquals(1, ctr.countNotifySubscriber("juanes10@gmail.com",false));
        assertEquals(1, ctr.countNotifySubscriber("juanes10@gmail.com",true));
        assertEquals(1, ctr.countNotifySubscriber("giedel@yahoo.es",true));
        assertEquals(0, ctr.countNotifySubscriber("giedel@yahoo.es",false));
    }

    @Test
    public void testGetSubscriptionsChannel(){
        setup();
        Object[][] data = ctr.getSubscriptionsChannel("POO");
        assertEquals(1, data.length);
        assertEquals("jose.antonio@gmail.com",data[0][1]);
        assertEquals("POO",data[0][0]);

        data = ctr.getSubscriptionsChannel("IoT");
        assertEquals(2, data.length);
        assertEquals("jose.antonio@gmail.com",data[0][1]);
        assertEquals("juanes10@gmail.com",data[1][1]);

        data = ctr.getSubscriptionsChannel("Anime");
        assertEquals(2, data.length);
        assertEquals("juanes10@gmail.com",data[0][1]);
        assertEquals("giedel@yahoo.es",data[1][1]);

        assertNull( ctr.getSubscriptionsChannel("Technology"));
    }

    @Test
    public void testGetSubscriptionsSubscriber( ){
        setup();
        Object[][] data = ctr.getSubscriptionsSubscriptor("jose.antonio@gmail.com");
        assertEquals(3, data.length);
        assertEquals("F", data[2][3]);

        data = ctr.getSubscriptionsSubscriptor("juanes10@gmail.com");
        assertEquals(2, data.length);

        data = ctr.getSubscriptionsSubscriptor("giedel@yahoo.es");
        assertEquals(1, data.length);

        assertNull( ctr.getSubscriptionsSubscriptor("noexiste@gmail.com"));
    }
}