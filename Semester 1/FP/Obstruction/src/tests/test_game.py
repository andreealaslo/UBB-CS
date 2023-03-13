import unittest
from src.game import Game


class TestGame(unittest.TestCase):
    def setUp(self) -> None:
        print("set up")

    def tearDown(self) -> None:
        print("tear down")

    def test_game(self):
        game = Game()
        game.human_move(1, 1)
        self.assertEqual(game.board.get_symbol_from_board(1, 1), 'X')
        self.assertEqual(game.is_won(), False)
        game.human_move(1, 4)
        game.human_move(4, 1)
        game.human_move(4, 4)
        self.assertEqual(game.is_won(), True)
