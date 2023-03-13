from random import choice
from src.board import Board


class Game:
    def __init__(self):
        self.__board = Board()

    @property
    def board(self):
        """
        Function which returns the board
        :return:
        """
        return self.__board

    def human_move(self, symbol, row, column):
        """
        Function for the human move
        :param symbol: The symbol human chooses
        :param row: the row on which the symbol will be
        :param column: the column in which the symbol will be
        :return: The symbol is on the board at position (row, column)
        """
        self.__board.move(symbol, row, column)

    def computer_move(self, symbol):
        """
        Function for the computer move, it makes a random move on one spot available
        :return:
        """
        free_spots = []
        for row in [0, 1, 2, 3, 4, 5]:
            for column in [0, 1, 2, 3, 4, 5]:
                if self.__board.get_symbol_from_board(row, column) == '-':
                    free_spots.append((row, column))
        position = choice(free_spots)
        self.__board.move(symbol, position[0], position[1])
        return position[0], position[1]

    def get_the_other_symbol_for_human_vs_someone(self, symbol):
        """
        Function which assigned the human/computer the alternative symbol, after the human's choose
        :param symbol: the symbol chosen by human (or chosen by player1)
        :return: if symbol is "X", it returns "O", and the other way around
        """
        if symbol == 'X':
            return 'O'
        return 'X'

    def is_won(self):
        """
        Function which verifies if the game is won.
        The game is won when there are no available spots left, and the player which made the last move before all spots are taken wins
        :return: True- if the game was won; False-otherwise
        """
        if self.board.count_the_available_spots() == 0:
            return True
        return False
