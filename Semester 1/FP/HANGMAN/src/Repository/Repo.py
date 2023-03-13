class RepoException(Exception):
    pass


class Repo:
    def __init__(self):
        self._sentences = []

    def clean_memory(self):
        self._sentences = []
        return self._sentences

    def get_all_sentences_repo(self):
        return self._sentences

    def verify_if_sentence_already_added(self, sentence_to_be_added):
        for sentence in self.get_all_sentences_repo():
            if sentence_to_be_added.__eq__(sentence):
                return False
        return True

    def add_sentence_repo(self, sentence):
        if self.verify_if_sentence_already_added(sentence):
            self._sentences.append(sentence)
        else:
            raise RepoException("The sentence is already added! There cannot be duplicate sentences!")
        return sentence

    def __len__(self):
        return len(self._sentences)
