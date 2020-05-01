package cz.nnpia.dominikjanak.webservice.consumer;

import cz.nnpia.dominikjanak.webservice.consumer.client.KalendarClient;
import https.stag_ws_upce_cz.ws.services.soap.kalendar.wsdl.GetAktualniObdobiInfoResponse;
import https.stag_ws_upce_cz.ws.services.soap.kalendar.wsdl.GetHarmonogramRokuResponse;
import https.stag_ws_upce_cz.ws.services.soap.kalendar.wsdl.HarmonogramItemType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ConsumingWebServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumingWebServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner lookup(KalendarClient kalendarClient) {
		return args -> {
			GetHarmonogramRokuResponse response = kalendarClient.GetHarmonogramRoku();
			List<HarmonogramItemType> data = response.getHarmonogramRoku().getHarmonogramItem();

			for (HarmonogramItemType item : data) {
				System.out.println(item.getDatumOd() + " -" + item.getDatumDo() + ": "+ item.getPopis());
			}
		};
	}
}
