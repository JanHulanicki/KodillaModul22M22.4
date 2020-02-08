package com.crud.tasks.trello.client;
import com.crud.tasks.config.TrelloConfig;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;
@Component
public class TrelloClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);
    @Autowired
    private TrelloConfig trelloConfig;
    @Autowired
    private RestTemplate restTemplate;
    public List<TrelloBoardDto> getTrelloBoards() {
           URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/members/kodillauser/boards")//trelloConfig.getTrelloUser())
                   .queryParam("key", trelloConfig.getTrelloAppKey())
                   .queryParam("token", trelloConfig.getTrelloToken())
                   .queryParam("fields", "name,id")//.build().encode().toUri();
                   .queryParam("lists","all").build().encode().toUri();
        try {
            TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
                return Arrays.asList(ofNullable(boardsResponse).orElse(new TrelloBoardDto[0]));
            }catch (RestClientException e) {
            LOGGER.error(e.getMessage(),e);
            return new ArrayList<>();
        }
    }
    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto) throws URISyntaxException {
        URI uri = new URI("http://test.com/cards?key=test&token=test&name=Test%20task&desc=Test%20Description&pos=top&idList=test_id");
        return restTemplate.postForObject(uri,null,CreatedTrelloCard.class);
    }
}