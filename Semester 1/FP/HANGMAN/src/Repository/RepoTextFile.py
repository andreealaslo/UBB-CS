from src.Repository.Repo import Repo
from src.domain import Sentence


class RepoTextFile(Repo):
    def __init__(self, file_path):
        self.__file_path = file_path
        super().__init__()
        self.clean_memory()
        self._read_data_from_file()

    def _write_data_to_file(self):
        with open(self.__file_path, "wt") as f:
            for sentence in self.get_all_sentences_repo():
                f.write(str(sentence) + "\n")
            f.close()

    def _read_data_from_file(self):
        self._sentences = self.clean_memory()
        with open(self.__file_path, "rt") as f:
            lines = f.readlines()
            f.close()
            for line in lines:
                if line != "":
                    string = line.strip()
                    sentence = Sentence(string)
                    self._sentences.append(sentence)

    def add_sentence_repo(self, sentence):
        self._read_data_from_file()
        sentence = super().add_sentence_repo(sentence)
        self._write_data_to_file()
        return sentence

    def get_all_sentences_repo(self):
        return super().get_all_sentences_repo()

    def __len__(self):
        return super().__len__()



