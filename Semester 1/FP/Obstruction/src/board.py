class Board:
    def __init__(self):
        self.__nr_rows = 6
        self.__nr_columns = 6
        self.__board = [['-' for _ in range(self.__nr_columns)] for _ in range(self.__nr_rows)]

    @property
    def nr_rows(self):
        """
        Function which returns the number of rows of the board.
        :return:
        """
        return self.__nr_rows

    @property
    def nr_columns(self):
        """
        Function which returns the number of columns of the board.
        :return:
        """
        return self.__nr_columns

    def move(self, symbol, row, column):
        """
        Function which put a symbol on the board and also make the neighbours unavailable
        :param symbol: it can be X or O, otherwise it will raise an error
        :param row: positive integer between 0 and 5, otherwise it is considered that the move is outside the board, it will raise an error
        :param column: positive integer between 0 and 5, otherwise it is considered that the move is outside the board, it will raise an error
        :return:
        """
        if symbol not in ['X', 'O']:
            raise ValueError("Invalid symbol!")
        if row not in [0, 1, 2, 3, 4, 5] or column not in [0, 1, 2, 3, 4, 5]:
            raise ValueError("You moved outside the board!")
        if self.get_symbol_from_board(row, column) != '-':
            raise ValueError("Cannot overwrite squares!")
        self.__board[row][column] = symbol
        self.make_neighbours_unavailable(row, column)

    def get_symbol_from_board(self, row, column):
        """
        Function which returns the symbol which is at the position pointed (row, column)
        :param row: the row in which the symbol required is, should be between 0 and 5, otherwise, error
        :param column: the column in which the symbol required is, should be between 0 and 5, otherwise, error
        :return: the symbol
        """
        if row not in [0, 1, 2, 3, 4, 5] or column not in [0, 1, 2, 3, 4, 5]:
            raise ValueError("Outside board")
        return self.__board[row][column]

    def check_if_square_is_valid_to_be_a_neighbour(self, row, column):
        """
        Helpful function for the make_neighbours_unavailable method
        :param row: the row
        :param column: the column
        :return: True if it is a valid neighbour, False otherwise
        """
        if row < 0 or column < 0:
            return False
        if row >= 6 or column >= 6:
            return False
        if self.__board[row][column] != '-':
            return False
        return True

    def make_neighbours_unavailable(self, row, column):
        """
        Function which make the neighbours unavailable
        When a symbol is put on the table, all its neighbours should be marked, such as another player cannot place another symbol on one of those neighbours
        :param row: row of the neighbour
        :param column: column of the neighbour
        :return: It places the symbol "■" on every valid neighbour
        """
        if self.check_if_square_is_valid_to_be_a_neighbour(row - 1, column - 1):
            self.__board[row - 1][column - 1] = "■"
        if self.check_if_square_is_valid_to_be_a_neighbour(row - 1, column):
            self.__board[row - 1][column] = "■"
        if self.check_if_square_is_valid_to_be_a_neighbour(row - 1, column + 1):
            self.__board[row - 1][column + 1] = "■"
        if self.check_if_square_is_valid_to_be_a_neighbour(row, column + 1):
            self.__board[row][column + 1] = "■"
        if self.check_if_square_is_valid_to_be_a_neighbour(row + 1, column + 1):
            self.__board[row + 1][column + 1] = "■"
        if self.check_if_square_is_valid_to_be_a_neighbour(row + 1, column):
            self.__board[row + 1][column] = "■"
        if self.check_if_square_is_valid_to_be_a_neighbour(row + 1, column - 1):
            self.__board[row + 1][column - 1] = "■"
        if self.check_if_square_is_valid_to_be_a_neighbour(row, column - 1):
            self.__board[row][column - 1] = "■"

    def count_the_available_spots(self):
        """
        Function which counts the available spots remained on the board
        A spot is available if it does not have "X", "O" or "■" on it
        :return: the number of available spots remained
        """
        count = 0
        for row in [0, 1, 2, 3, 4, 5]:
            for column in [0, 1, 2, 3, 4, 5]:
                if self.__board[row][column] == '-':
                    count = count + 1
        return count


