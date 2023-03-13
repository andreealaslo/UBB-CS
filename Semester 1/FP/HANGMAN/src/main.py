
from src.Repository.RepoTextFile import RepoTextFile
from src.controller import Controller
from src.ui import UI
from src.validation import SentenceValidation


def start_main():
    repo = RepoTextFile("sentences.txt")
    validation = SentenceValidation()
    ctrl = Controller(repo, validation)
    ui = UI(ctrl)
    ui.start()

start_main()

