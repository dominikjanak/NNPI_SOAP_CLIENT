package cz.nnpia.dominikjanak.webservice.consumer.config;

import cz.nnpia.dominikjanak.webservice.consumer.client.KalendarClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("https.stag_ws_upce_cz.ws.services.soap.kalendar.wsdl");
        return marshaller;
    }

    @Bean
    public KalendarClient kalendarClient(Jaxb2Marshaller marshaller) {
        KalendarClient client = new KalendarClient();
        client.setDefaultUri("https://stag-ws.upce.cz/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
