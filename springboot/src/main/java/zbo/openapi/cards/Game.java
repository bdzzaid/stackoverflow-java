package zbo.openapi.cards;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.*;

@Schema(description = "Project entity")
public class Game
{

    @Schema(description = "Game id", example = "62357d7f-9289-43f6-a9fb-8695de5d69db")
    private String id;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Game start date", example = "2019-12-27 13:15:18")
    private LocalDateTime startDate;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Card is game")
    private Stack<Card> cardGame;

    public Game() {
        this.id =   UUID.randomUUID() .toString();
        this.startDate = LocalDateTime.now();
        this.cardGame = new Stack<>();
        this.cardGame.addAll(CardPackage.PACKAGE_52);
        Collections.shuffle(cardGame);
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public LocalDateTime getStartDate()
    {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate)
    {
        this.startDate = startDate;
    }

    public Stack<Card> getCardGame()
    {
        return cardGame;
    }

    public void setCardGame(Stack<Card> cardGame)
    {
        this.cardGame = cardGame;
    }
}
