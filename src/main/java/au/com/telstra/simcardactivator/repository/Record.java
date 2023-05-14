package au.com.telstra.simcardactivator.repository;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Table
@RequiredArgsConstructor
public class Record{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String iccid;
    private String costumerEmail;
    private boolean activate;

    public Record(String iccid, String costumerEmail, boolean activate) {
        this.iccid = iccid;
        this.costumerEmail = costumerEmail;
        this.activate = activate;
    }
}
