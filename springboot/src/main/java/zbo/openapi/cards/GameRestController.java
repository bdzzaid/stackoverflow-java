package zbo.openapi.cards;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@Tag(name = "Card")
public class GameRestController
{

    @GetMapping("/new")
    @Operation(summary = "Get the next card", description = "Return a new game")
    public Game newGame() {
        return new Game();
    }

    @GetMapping("/{id}/deal")
    @Operation(summary = "Get the next card for a game", description = "Return a random next card")
    public Card dealNextCard(@Parameter(description = "The game id", required = true, example = "62357d7f-9289-43f6-a9fb-8695de5d69db")
                                 @PathVariable final String gameId) {
        Card next = new Card(2, Card.Shape.PIQUE);
        return next;
    }

    @PostMapping("/{id}/change")
    @Operation(summary = "Get the next card for a game", description = "Return a random next card", hidden = true)
    public Card changeCard(@Parameter(description = "The game id", required = true, example = "62357d7f-9289-43f6-a9fb-8695de5d69db")
                             @PathVariable final String gameId, @RequestBody Card previous) {
        return dealNextCard(gameId);
    }

    @GetMapping("/{id}/changecard")
    @Operation(summary = "Remove the last done and give a new one", description = "Return a random next card", deprecated = true)
    public Card changeCardDeprecated(@Parameter(description = "The game id", required = true, example = "62357d7f-9289-43f6-a9fb-8695de5d69db")
                       @PathVariable final String gameId) {
      return changeCard(gameId, null);
    }

    @GetMapping("/{id}/changelastcard")
    @Operation(summary = "Remove the last done and give a new one", description = "Return a random next card" )
    @Deprecated
    public Card changeCardDeprecatedEndpoint(@Parameter(description = "The game id", required = true, example = "62357d7f-9289-43f6-a9fb-8695de5d69db")
                                     @PathVariable final String gameId) {
        return changeCard(gameId, null);
    }
}
