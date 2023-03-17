package com.example.spotify_sherlin;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@RestController
@RequestMapping("/SpotifyApplicationSherlin")
public class APIController {
    @GetMapping
    public String index(){
        return "App has started";
    }
    @GetMapping("/search/{keyword}")
    public ResponseEntity search(@PathVariable String keyword) throws URISyntaxException, IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://spotify81.p.rapidapi.com/search?q="+keyword+"&type=multi&offset=0&limit=10&numberOfTopResults=5"))
                .header("X-RapidAPI-Key", "d1869f472amsh706c8dbcf4cf939p147e3bjsn048c5e14b83e")
                .header("X-RapidAPI-Host", "spotify81.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return ResponseEntity.status(HttpStatus.OK)
                .body(response.body());

    }
    @GetMapping("/albums/{id}")
    public ResponseEntity GetbyId(@PathVariable String id) throws URISyntaxException, IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://spotify81.p.rapidapi.com/album_tracks?id="+id))
                .header("X-RapidAPI-Key", "d1869f472amsh706c8dbcf4cf939p147e3bjsn048c5e14b83e")
                .header("X-RapidAPI-Host", "spotify81.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return ResponseEntity.status(HttpStatus.OK)
                .body(response.body());
    }
}