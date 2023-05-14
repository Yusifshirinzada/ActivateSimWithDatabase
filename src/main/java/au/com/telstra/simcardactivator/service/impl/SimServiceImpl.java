package au.com.telstra.simcardactivator.service.impl;

import au.com.telstra.simcardactivator.ActivateResponse;

import au.com.telstra.simcardactivator.repository.Record;
import au.com.telstra.simcardactivator.repository.SimRecord;
import au.com.telstra.simcardactivator.service.SimService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


@Service
@RequiredArgsConstructor
public class SimServiceImpl implements SimService {
    private final SimRecord simRecord;
    @Override
    public ActivateResponse activateSim(String iccid, String costumerEmail) {

        try{
            URL url=new URL("http://localhost:8444/actuate");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();


            byte[] body = ("{\"iccid\": \""+iccid+"\"}").getBytes("utf-8");

            connection.setRequestMethod("POST");


            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", Integer.toString(body.length));


            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();

            os.write(body, 0, body.length);
            os.flush();
            os.close();


            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();


            System.out.println(response.toString());
            ObjectMapper objectMapper = new ObjectMapper();
            ActivateResponse activateResponse = objectMapper.readValue(response.toString(), ActivateResponse.class);


            simRecord.save(new Record(iccid,costumerEmail,activateResponse.getSuccess()));

            return activateResponse;

        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

   public Record simCardId(long id){
       return simRecord.findById(id).get();
    }

}
