from src.Repository.Repo import RepoException
from src.domain import Sentence
from src.validation import ValidException


class UI:
    def __init__(self, controller):
        self.__controller = controller

    @staticmethod
    def print_menu():
        print("1. Add a sentence.")
        print("2. Play Hangman :)")
        print("X. Exit.")

    def add_a_sentence_ui(self):
        string = input("Your sentence is: ")
        self.__controller.add_sentence_controller(string)
        print("Sentence added succesfully!")

    def play_hangman_ui(self):
        original_sentence, encoded_for_start_sentence = self.__controller.encode_sentence()
        print(encoded_for_start_sentence)
        possible_outcomes = ['', 'h', 'ha', 'han', 'hang', 'hangm', 'hangma', 'hangman']
        counter = 0
        human_not_won = True
        obj_encoded = Sentence(encoded_for_start_sentence)
        while human_not_won:
            letter = input("Guess a letter: ")
            encoded_for_start_sentence = obj_encoded.sentence
            if letter not in original_sentence:
                if counter == 6:
                    print(possible_outcomes[counter + 1])
                    print("You lost! :(")
                    human_not_won = False
                else:
                    counter = counter + 1
                    print("Bad guess :( . Your sentence is still: " + str(obj_encoded) + " - " + possible_outcomes[
                        counter])
            else:
                vector_of_positions_of_letter_to_be_shown = []
                for position_of_letter in range(len(original_sentence)):
                    if letter == original_sentence[position_of_letter]:
                        vector_of_positions_of_letter_to_be_shown.append(position_of_letter)
                encoded_after_another_letter_found = ""
                for position_in_enc in range(len(encoded_for_start_sentence)):
                    if position_in_enc in vector_of_positions_of_letter_to_be_shown:
                        encoded_after_another_letter_found += letter
                    else:
                        encoded_after_another_letter_found += encoded_for_start_sentence[position_in_enc]
                strip_encoded_after_another_letter_found = encoded_after_another_letter_found.strip()
                obj_encoded.set_sentence(strip_encoded_after_another_letter_found)
                if original_sentence == str(obj_encoded):
                    print(str(obj_encoded))
                    print("YOU WON! :)")
                    human_not_won = False
                else:
                    print("Good guess :) . Your sentence is : " + str(obj_encoded) + " - " + possible_outcomes[counter])

    def start(self):
        self.print_menu()
        game_ended = False
        while not game_ended:
            option = input(">")
            try:
                if option == "1":
                    self.add_a_sentence_ui()
                elif option == "2":
                    print("Lets play hangman :)")
                    self.play_hangman_ui()
                    break
                elif option == "X":
                    break
                else:
                    print("Bad command")
            except RepoException as re:
                print(f"{re}")
            except ValidException as re:
                print(f"{re}")
