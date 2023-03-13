import unittest
from src.board import Board


class TestBoard(unittest.TestCase):
    def setUp(self) -> None:
        print("set up")

    def tearDown(self) -> None:
        print("tear down")

    def test_getters_for_row_and_column(self):
        board = Board()
        self.assertEqual(board.nr_rows, 6)
        self.assertEqual(board.nr_columns, 6)

    def test_move(self):
        board = Board()
        board.move('X', 1, 1)
        try:
            board.move('a', 2, 3)
            assert False
        except ValueError as ve:
            self.assertEqual(str(ve), "Invalid symbol!")
        try:
            board.move('X', 10, 10)
            assert False
        except ValueError as ve:
            self.assertEqual(str(ve), "You moved outside the board!")
        try:
            board.move('O', 1, 1)
            assert False
        except ValueError as ve:
            self.assertEqual(str(ve), "Cannot overwrite squares!")

    def test_get_symbol_from_board(self):
        board = Board()
        board.move('X', 1, 1)
        self.assertEqual(board.get_symbol_from_board(1, 1), 'X')
        self.assertEqual(board.get_symbol_from_board(0, 0), "■")

    def test_check_if_square_is_valid_to_be_a_neighbour(self):
        board = Board()
        board.move('X', 1, 1)
        self.assertEqual(board.check_if_square_is_valid_to_be_a_neighbour(0, 0),
                         False)  # because it was already marked with "■" after the call of function move
        self.assertEqual(board.check_if_square_is_valid_to_be_a_neighbour(7, 7), False)

    def test_count_the_available_spots(self):
        board = Board()
        board.move('X', 0, 0)
        self.assertEqual(board.count_the_available_spots(), 32)
        board.move('O', 3, 3)
        self.assertEqual(board.count_the_available_spots(), 23)
