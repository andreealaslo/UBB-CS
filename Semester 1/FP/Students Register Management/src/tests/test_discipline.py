import unittest

from src.errors.repo_errors import RepoDisciplineError
from src.errors.validation_errors import ValidDisciplineError
from src.repository.discipline_repo import RepoDiscipline
from src.repository.grade_repo import RepoGrade
from src.repository.student_repo import RepoStudent
from src.services.service import Service
from src.validation.validation_discipline import ValidationDiscipline
from src.validation.validation_grade import ValidationGrade
from src.validation.validation_student import ValidationStudent


class TestDiscipline(unittest.TestCase):
    def setUp(self) -> None:
        print("set up")

    def tearDown(self) -> None:
        print("tear down")

    def test__add_discipline(self):
        validation_s = ValidationStudent()
        validation_d = ValidationDiscipline()
        validation_g = ValidationGrade()
        repo_s = RepoStudent()
        repo_d = RepoDiscipline()
        repo_g = RepoGrade()
        service = Service(repo_s, repo_d, repo_g, validation_s, validation_d, validation_g)
        self.assertEqual(service.size_service_discipline(), 0)
        discipline = service.add_discipline_service(1, "FP")
        self.assertEqual(service.size_service_discipline(), 1)
        self.assertEqual(discipline.discipline_id, 1)
        self.assertEqual(discipline.discipline_name, "FP")
        try:
            service.add_discipline_service(-1, "FP")
            assert False
        except ValidDisciplineError as vs:
            self.assertEqual(str(vs), "Invalid discipline id!")
        self.assertEqual(service.size_service_discipline(), 1)
        try:
            service.add_discipline_service(1, "FP")
            assert False
        except RepoDisciplineError as rs:
            self.assertEqual(str(rs), "Existent discipline!")
        print("test for add discipline is done!")

    def test__remove_student(self):
        validation_s = ValidationStudent()
        validation_d = ValidationDiscipline()
        validation_g = ValidationGrade()
        repo_s = RepoStudent()
        repo_d = RepoDiscipline()
        repo_g = RepoGrade()
        service = Service(repo_s, repo_d, repo_g, validation_s, validation_d, validation_g)
        self.assertEqual(service.size_service_discipline(), 0)
        service.add_discipline_service(1, "OOP")
        self.assertEqual(service.size_service_discipline(), 1)
        service.remove_discipline_service(1)
        self.assertEqual(service.size_service_discipline(), 0)
        try:
            service.remove_discipline_service(1)
            assert False
        except RepoDisciplineError as rs:
            self.assertEqual(str(rs), "Inexistent discipline!")
        print("test for remove discipline is done")

    def test__update_student(self):
        validation_s = ValidationStudent()
        validation_d = ValidationDiscipline()
        validation_g = ValidationGrade()
        repo_s = RepoStudent()
        repo_d = RepoDiscipline()
        repo_g = RepoGrade()
        service = Service(repo_s, repo_d, repo_g, validation_s, validation_d, validation_g)
        discipline = service.add_discipline_service(1, "FP")
        self.assertEqual(service.size_service_discipline(), 1)
        self.assertEqual(discipline.discipline_id, 1)
        self.assertEqual(discipline.discipline_name, "FP")
        service.update_discipline_service(1, "Fundamentals of programming")
        self.assertEqual(discipline.discipline_name, "Fundamentals of programming")
        print("test for update student is done")
