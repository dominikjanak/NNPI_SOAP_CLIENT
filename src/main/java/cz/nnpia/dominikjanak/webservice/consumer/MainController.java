package cz.nnpia.dominikjanak.webservice.consumer;

import cz.nnpia.dominikjanak.webservice.consumer.client.KalendarClient;
import https.stag_ws_upce_cz.ws.services.soap.kalendar.wsdl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.datatype.DatatypeConfigurationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private KalendarClient kalendarClient;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/aktualni-obdobi")
    public String aktualni(Model model) {

        GetAktualniObdobiInfoResponse response = kalendarClient.GetAktualniObdobiInfo();

        ObdobiInfoType obdobi = response.getObdobiInfo();
        model.addAttribute("obdobi", obdobi);
        return "aktualni-obdobi";
    }

    @GetMapping("/harmonogram")
    public String harmonogram(Model model, @RequestParam(value="rok", defaultValue="2019") String year) {

        GetHarmonogramRokuResponse response = kalendarClient.GetHarmonogramRoku(year);
        List<HarmonogramItemType> data = response.getHarmonogramRoku().getHarmonogramItem();
        model.addAttribute("data", data);
        return "harmonogram-roku";
    }

    @GetMapping("/harmonogramICAL")
    public String harmonogramICAL(Model model, @RequestParam(value="rok", defaultValue="2019") String year) {

        GetHarmonogramRokuICALResponse response = kalendarClient.GetHarmonogramRokuICAL(year);
        byte[] data = response.getHarmonogramRoku();
        String sData = new String(data);

        model.addAttribute("ical", sData);
        return "harmonogram-ical";
    }

    @GetMapping("/kalendar")
    public String kalendar(Model model, String rok, @RequestParam(value="datum") String date) throws DatatypeConfigurationException {

        GetKalendarRokuResponse response = kalendarClient.GetKalendarRoku(rok, date);

        List<KalendarItemType> data = response.getKalendarRoku().getKalendarItem();
        model.addAttribute("data", data);
        return "kalendar";
    }
}
