from src.game import Game


class UI:
    def __init__(self, game):
        self.__game = game

    def board_to_string(self):
        print("\n      0     1     2     3     4     5", end="")
        for row in range(self.__game.board.nr_rows):
            print("\n   +-----+-----+-----+-----+-----+-----+")
            print(row, " |", end="")
            for coll in range(self.__game.board.nr_columns):
                print(" ", self.__game.board.get_symbol_from_board(row, coll), end="  |")
        print("\n   +-----+-----+-----+-----+-----+-----+")

    def split_position(self, position):
        position = position.strip()
        tokens = position.split(",")
        return int(tokens[0]), int(tokens[1])

    def human_vs_computer(self):
        name = input("Your name is? ")
        print("You will play OBSTRUCTION against the computer! Have fun! :D  ")
        human_symbol = input("Choose your symbol: ")
        computer_symbol = game.get_the_other_symbol_for_human_vs_someone(human_symbol)
        is_no_won = False
        human_move = True
        while not is_no_won:
            self.board_to_string()
            if human_move:
                position = input(name + ", make your move: ")
                row, column = self.split_position(position)
                try:
                    self.__game.human_move(human_symbol, row, column)
                except ValueError as ve:
                    print(str(ve))
                if self.__game.is_won():
                    self.board_to_string()
                    print("You won!! Congrats!")
                    is_no_won = True
            else:
                row, column = self.__game.computer_move(computer_symbol)
                print("Computer moved at (" + str(row) + "," + str(column) + ")")
                if self.__game.is_won():
                    self.board_to_string()
                    print("Computer won!! You lost!:(")
                    is_no_won = True
            human_move = not human_move

    def human_vs_human(self):
        player1_name = input("Player1, your name is? ")
        player2_name = input("Player2, your name is? ")
        print(player1_name + ", " + player2_name + ", you will play OBSTRUCTION against each other! Have fun! :D  ")
        player1_symbol = input(player1_name + ", choose your symbol: ")
        player2_symbol = game.get_the_other_symbol_for_human_vs_someone(player1_symbol)

        is_no_won = False
        player1_move = True
        while not is_no_won:
            self.board_to_string()
            if player1_move:
                position1 = input(player1_name + ", make your move: ")
                row, column = self.split_position(position1)
                try:
                    self.__game.human_move(player1_symbol, row, column)
                except ValueError as ve:
                    print(str(ve))
                if self.__game.is_won():
                    self.board_to_string()
                    print(player1_name + ", you won!! Congrats!")
                    is_no_won = True
            else:
                position2 = input(player2_name + ", make your move: ")
                row, column = self.split_position(position2)
                try:
                    self.__game.human_move(player2_symbol, row, column)
                except ValueError as ve:
                    print(str(ve))
                if self.__game.is_won():
                    self.board_to_string()
                    print(player2_name + ", you won!! Congrats!")
                    is_no_won = True
            player1_move = not player1_move

    def lets_play(self):
        print("What OBSTRUCTION version do you want to play?")
        print("1. human vs human.")
        print("2. human vs computer.")
        option = input(">")
        if option == "1":
            self.human_vs_human()
        else:
            self.human_vs_computer()


game = Game()
ui = UI(game)
ui.lets_play()
