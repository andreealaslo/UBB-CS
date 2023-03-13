class ValidException(Exception):
    pass


class SentenceValidation:
    def __int__(self):
        pass

    def validate_sentence(self, sentence):
        errors = ""
        sentence = sentence.strip()
        tokens = sentence.split()
        if len(tokens) == 1:
            if len(tokens[0]) < 3:
                errors += "Not enough letters the sentence's words. Words must have at least 3 letters!"
        else:
            for position in range(len(tokens)):
                if len(tokens[position]) < 3:
                    errors += "Not enough letters the sentence's words. Words must have at least 3 letters!"
                    break
        if len(errors) > 0:
            raise ValidException(errors)
