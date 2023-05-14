package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.ActivateResponse;
import au.com.telstra.simcardactivator.model.Sim;
import au.com.telstra.simcardactivator.repository.Record;
import au.com.telstra.simcardactivator.service.SimService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sim")
@RequiredArgsConstructor
public class SimController {
    private final SimService simService;

    @GetMapping("/SimCardId/{id}")
    Record findSim(@PathVariable long id){
        return simService.simCardId(id);
    }


    @PostMapping(value = "/activateSim")
    ActivateResponse activateSim(@RequestBody Sim sim){
       return simService.activateSim(sim.getIccid(), sim.getCostumerEmail());


    }


}
