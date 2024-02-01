package com.klab.desafiocartas.domain.entity;

import com.klab.desafiocartas.constant.ValueConstants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CardTest {


    @Test
    @DisplayName("Teste para verificar se a associação com Hand funciona corretamente")
    void testHandAssociation() {
        Hand mockHand = mock(Hand.class);
        Card card = new Card("DIAMONDS", "10");
        card.setHand(mockHand);

        assertEquals(mockHand, card.getHand());
    }

    @Test
    @DisplayName("Teste para verificar a conversão de valores numéricos")
    void testNumericCardValuesForIntValue() {
        String suit = "HEARTS";

        for (int value = 2; value <= 10; value++) {
            Card card = new Card(suit, String.valueOf(value));
            int numericValue = card.getIntValue();
            assertEquals(value, numericValue, "Valor incorreto para o naipe " + suit + " e valor da carta " + value);
        }
    }

    @Test
    @DisplayName("Teste para verificar a conversão de valores de naipe")
    void testFaceCardValuesForIntValue() {
        String suit = "HEARTS";
        String[] faceValues = {"ACE", "JACK", "QUEEN", "KING"};

        for (String faceValue : faceValues) {
            Card card = new Card(suit, faceValue);
            int numericValue = card.getIntValue();
            int expectedValue = switch (faceValue) {
                case "ACE" -> ValueConstants.ACE;
                case "JACK" -> ValueConstants.JACK;
                case "QUEEN" -> ValueConstants.QUEEN;
                case "KING" -> ValueConstants.KING;
                default -> 0; // Valor padrão, pois não esperamos outros valores de face aqui
            };
            assertEquals(expectedValue, numericValue, "Valor incorreto para o naipe " + suit + " e valor da carta " + faceValue);
        }
    }

}
