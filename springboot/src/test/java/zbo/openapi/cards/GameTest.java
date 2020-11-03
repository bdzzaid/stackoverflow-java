package zbo.openapi.cards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest
{
    @Test
    void getCardGame()
    {
        Game game = new Game();
        game.getCardGame().forEach(card -> {
            System.out.println(card.toString());
        });
    }
}
