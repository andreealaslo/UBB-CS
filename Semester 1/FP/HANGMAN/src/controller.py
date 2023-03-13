from src.domain import Sentence

import random


class Controller:
    def __init__(self, repo, validator):
        self.__repo = repo
        self.__validator = validator

    def add_sentence_controller(self, string):
        string = string.strip()
        self.__validator.validate_sentence(string)
        sentence = Sentence(string)
        self.__repo.add_sentence_repo(sentence)

    def select_random_sentence(self):
        sentences = self.__repo.get_all_sentences_repo()
        random_sentence = random.choice(sentences)
        return random_sentence

    def encode_sentence(self):
        encoded_sentence = ""
        sentence_to_be_encoded = self.select_random_sentence()
        tokens = str(sentence_to_be_encoded).split()
        letters_still_shown = []
        for position in range(len(tokens)):
            first_letter = tokens[position][0]
            last_letter = tokens[position][len(tokens[position]) - 1]
            if first_letter not in letters_still_shown:
                letters_still_shown.append(first_letter)
            if last_letter not in letters_still_shown:
                letters_still_shown.append(last_letter)
            tokens[position] = tokens[position].strip()
            encoded_word = ""
            encoded_word += first_letter
            for letter in range(1, len(tokens[position]) - 1):
                if tokens[position][letter] not in letters_still_shown:
                    encoded_word += "_"
                else:
                    encoded_word += tokens[position][letter]
            encoded_word += last_letter
            encoded_sentence += encoded_word + " "
        encoded_sentence = encoded_sentence.strip()
        return str(sentence_to_be_encoded), encoded_sentence



    def get_all_sentences_controller(self):
        return self.__repo.get_all_sentences()
