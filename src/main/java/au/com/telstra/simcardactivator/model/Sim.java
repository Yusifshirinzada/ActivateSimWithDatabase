package au.com.telstra.simcardactivator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sim {


    String iccid;
    String costumerEmail;

    boolean activate;
}
