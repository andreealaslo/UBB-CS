from src.errors.repo_errors import RepoError
from src.errors.validation_errors import ValidError
from src.repositories.repo_in_memory import RepoMemory
from src.services.service import Service
from src.validation.student_validation import StudentValidation


class Tests:

    def run_all(self):
        self.test_add_student_memory()

    def test_add_student_memory(self):
        validation = StudentValidation()
        repo = RepoMemory()
        service = Service(validation, repo)
        assert service.size_service() == 0
        id = 1
        name = "Ana"
        group = 921
        service.add_student_service(id, name, group)
        assert service.size_service() == 1
        try:
            id = 1
            name = "Mira"
            group = 923
            service.add_student_service(id, name, group)
            assert False
        except RepoError as ve:
            assert str(ve) == "Existent student!"
        try:
            id = -1
            name = "Ana"
            group = 923
            service.add_student_service(id, name, group)
            assert False
        except ValidError as re:
            assert str(re) == "Invalid student id!"
        try:
            id = 1
            name = ""
            group = 923
            service.add_student_service(id, name, group)
            assert False
        except ValidError as re:
            assert str(re) == "Invalid name!"
        try:
            id = 1
            name = "ana"
            group = -923
            service.add_student_service(id, name, group)
            assert False
        except ValidError as re:
            assert str(re) == "Invalid student group!"
        assert service.size_service() == 1
        print("test for add is done!")
