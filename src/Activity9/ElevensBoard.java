package Activity9;

import java.util.List;
import java.util.ArrayList;

public class ElevensBoard extends Board
{
    private static final int BOARD_SIZE = 9;
    private static final String[] RANKS = {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
    private static final String[] SUITS = {"spades", "hearts", "diamonds", "clubs"};
    private static final int[] POINT_VALUES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};
    private static final boolean I_AM_DEBUGGING = false;
    public ElevensBoard()
    {
        super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
    }
    @Override
    public boolean isLegal(List<Integer> selectedCards)
    {
        if (selectedCards.size() == 2)
        {
            return containsPairSum11(selectedCards);
        }
        else if (selectedCards.size() == 3)
        {
            return containsJQK(selectedCards);
        }
        return false;
    }
    @Override
    public boolean anotherPlayIsPossible()
    {
        List<Integer> indexes = cardIndexes();
        if (!containsPairSum11(indexes))
        {
            return containsJQK(indexes);
        }
        return true;
    }
    private boolean containsPairSum11(List<Integer> selectedCards)
    {
        if (selectedCards.size() < 2)
        {
            return false;
        }
        for (int i = 0; i < selectedCards.size() - 1; i++) {
            for (int j = i + 1; j < selectedCards.size(); j++) {
                if (cardAt(selectedCards.get(i)).pointValue() + cardAt(selectedCards.get(j)).pointValue() == 11)
                {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean containsJQK(List<Integer> selectedCards)
    {
        boolean hasJack = false;
        boolean hasQueen = false;
        boolean hasKing = false;
        if (selectedCards.size() < 3) {
            return false;
        }
        for (int i = 0; i < selectedCards.size(); i++) {
            if (cardAt(selectedCards.get(i)).rank() == "jack") {
                hasJack = true;
            }
            else if (cardAt(selectedCards.get(i)).rank() == "queen") {
                hasQueen = true;
            }
            else if (cardAt(selectedCards.get(i)).rank() == "king") {
                hasKing = true;
            }
        }
        return (hasJack && hasQueen && hasKing);
    }
}