class Sentence:
    def __init__(self, string):
        self.__sentence = string

    @property
    def sentence(self):
        return self.__sentence

    def set_sentence(self, new_sentence):
        self.__sentence = new_sentence

    def __eq__(self, other):
        return self.sentence == other.sentence

    def __str__(self):
        return f"{self.__sentence}"
