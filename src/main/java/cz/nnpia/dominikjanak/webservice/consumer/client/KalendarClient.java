package cz.nnpia.dominikjanak.webservice.consumer.client;

import https.stag_ws_upce_cz.ws.services.soap.kalendar.wsdl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.bind.JAXBElement;


public class KalendarClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(KalendarClient.class);

    public GetHarmonogramRokuResponse GetHarmonogramRoku() {

        ObjectFactory factory = new ObjectFactory();

        GetHarmonogramRoku request = factory.createGetHarmonogramRoku();

        JAXBElement<GetHarmonogramRoku> element = new ObjectFactory().createGetHarmonogramRoku(request);

        JAXBElement<GetHarmonogramRokuResponse> response = (JAXBElement<GetHarmonogramRokuResponse>) getWebServiceTemplate()
                .marshalSendAndReceive("https://stag-ws.upce.cz/ws/services/soap/kalendar", element);

        return response.getValue();
    }

}
