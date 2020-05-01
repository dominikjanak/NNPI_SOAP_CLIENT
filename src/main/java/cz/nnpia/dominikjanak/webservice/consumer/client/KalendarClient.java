package cz.nnpia.dominikjanak.webservice.consumer.client;

import https.stag_ws_upce_cz.ws.services.soap.kalendar.wsdl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class KalendarClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(KalendarClient.class);
    private static final ObjectFactory factory = new ObjectFactory();

    public GetAktualniObdobiInfoResponse GetAktualniObdobiInfo() {
        GetAktualniObdobiInfo request = factory.createGetAktualniObdobiInfo();

        JAXBElement<GetAktualniObdobiInfo> element = new ObjectFactory().createGetAktualniObdobiInfo(request);
        JAXBElement<GetAktualniObdobiInfoResponse> response = (JAXBElement<GetAktualniObdobiInfoResponse>) getWebServiceTemplate()
                .marshalSendAndReceive("https://stag-ws.upce.cz/ws/services/soap/kalendar", element);

        return response.getValue();
    }

    public GetHarmonogramRokuResponse GetHarmonogramRoku(String year) {

        GetHarmonogramRoku request = factory.createGetHarmonogramRoku();
        request.setRok(factory.createGetHarmonogramRokuRok(year));

        JAXBElement<GetHarmonogramRoku> element = new ObjectFactory().createGetHarmonogramRoku(request);

        JAXBElement<GetHarmonogramRokuResponse> response = (JAXBElement<GetHarmonogramRokuResponse>) getWebServiceTemplate()
                .marshalSendAndReceive("https://stag-ws.upce.cz/ws/services/soap/kalendar", element);

        return response.getValue();
    }

    public GetHarmonogramRokuICALResponse GetHarmonogramRokuICAL(String year) {
        GetHarmonogramRokuICAL request = factory.createGetHarmonogramRokuICAL();
        request.setRok(factory.createGetHarmonogramRokuICALRok(year));

        JAXBElement<GetHarmonogramRokuICAL> element = new ObjectFactory().createGetHarmonogramRokuICAL(request);
        JAXBElement<GetHarmonogramRokuICALResponse> response = (JAXBElement<GetHarmonogramRokuICALResponse>) getWebServiceTemplate()
                .marshalSendAndReceive("https://stag-ws.upce.cz/ws/services/soap/kalendar", element);

        return response.getValue();
    }

    public GetKalendarRokuResponse GetKalendarRoku(String year, String datum) throws DatatypeConfigurationException {
        GetKalendarRoku request = factory.createGetKalendarRoku();

        if(year != null && !year.isEmpty()){
            request.setRok(factory.createGetKalendarRokuRok(year));
        }

        XMLGregorianCalendar gregorianDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(datum);
        request.setDatum(factory.createGetKalendarRokuDatum(gregorianDate));

        JAXBElement<GetKalendarRoku> element = new ObjectFactory().createGetKalendarRoku(request);
        JAXBElement<GetKalendarRokuResponse> response = (JAXBElement<GetKalendarRokuResponse>) getWebServiceTemplate()
                .marshalSendAndReceive("https://stag-ws.upce.cz/ws/services/soap/kalendar", element);

        return response.getValue();
    }
}
