package com.sample.cricket.controller;

import com.sample.cricket.model.Team;
import com.sample.cricket.repository.CricketRepository;
import com.sample.cricket.service.CricketService;
import com.sample.cricket.serviceImpl.CricketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

@RestController
public class CricketController {

    private final CricketRepository cricketRepository;
    @Autowired
    private CricketService cricketService;

    public CricketController(CricketRepository cricketRepository) {
        this.cricketRepository = cricketRepository;
    }

    @GetMapping("/team")
    public ResponseEntity<List<Team>> getTeams(){
        return ResponseEntity.ok(this.cricketRepository.findAll());
    }

    @PostMapping("/team")
    public ResponseEntity<Team> createTeam(@RequestBody Team team){
        return ResponseEntity.ok(this.cricketRepository.save(team));
    }

    @GetMapping("/team/{id}")
    public ResponseEntity getTeamById(@PathVariable String id){

        Optional<Team> team = this.cricketRepository.findById(id);

        if(team.isPresent()){
            return ResponseEntity.ok(team.get());
        }
        else {
            return ResponseEntity.ok("No team is avaiable for the given id: "+id);
        }
    }
    @DeleteMapping("/team/{id}")
    public ResponseEntity deleteTeamById(@PathVariable String id){

        Optional<Team> team = this.cricketRepository.findById(id);

        if(team.isPresent()){
            this.cricketRepository.deleteById(id);
            return ResponseEntity.ok("Deleted successfully");
        }
        else {
            return ResponseEntity.ok("No team is avaiable for the given id: "+id);
        }
    }
    @GetMapping("/result/{team1}/{team2}")
    public ResponseEntity getResult(@PathVariable String team1, @PathVariable String team2 ){

        int team1Score = cricketService.getScore();
        int team2Score = cricketService.getScore();

        String winner;

        winner = (team1Score>team2Score) ? team1: team2;
        return ResponseEntity.ok("Score of "+team1+" : "+team1Score+"\n" +
                "Score of "+team2+ " : "+team2Score+"\n" +
                "The winner is : "+winner);
    }

    @GetMapping("/greet")
    public String Helloworld(){
        return "Hello world";
    }

}
