from src.repositories.repo_binary_file import RepoBinaryFile
from src.repositories.repo_in_memory import RepoMemory
from src.repositories.repo_text_file import RepoTextFile
from src.services.service import Service
from src.tests.tests import Tests
from src.ui.UI import UI
from src.validation.student_validation import StudentValidation


def main():
    test = Tests()
    test.run_all()
    validation_student = StudentValidation()
    repo = input("Choose the type of the repository: memory/text file/binary file: ")
    if repo == "memory":
        repo = RepoMemory()
    elif repo == "text file":
        repo = RepoTextFile("students.txt")
    elif repo == "binary file":
        repo = RepoBinaryFile("students.bin")
    service = Service(validation_student, repo)
    service.generate_students()
    ui = UI(service)
    ui.run()


main()
