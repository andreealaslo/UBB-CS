import unittest

from src.errors.repo_errors import RepoStudentError
from src.errors.validation_errors import ValidStudentError
from src.repository.discipline_repo import RepoDiscipline
from src.repository.grade_repo import RepoGrade
from src.repository.student_repo import RepoStudent
from src.services.service import Service
from src.validation.validation_discipline import ValidationDiscipline
from src.validation.validation_grade import ValidationGrade
from src.validation.validation_student import ValidationStudent


class TestStatistics(unittest.TestCase):
    def setUp(self) -> None:
        print("set up")

    def tearDown(self) -> None:
        print("tear down")

    def test__students_failing_at_one_or_more_discipline(self):
        validation_s = ValidationStudent()
        validation_d = ValidationDiscipline()
        validation_g = ValidationGrade()
        repo_s = RepoStudent()
        repo_d = RepoDiscipline()
        repo_g = RepoGrade()
        service = Service(repo_s, repo_d, repo_g, validation_s, validation_d, validation_g)
        self.assertEqual(service.size_service_student(), 0)
        self.assertEqual(service.size_service_discipline(), 0)
        service.add_student_service(1, "Ana")
        service.add_student_service(2, "Florin")
        service.add_discipline_service(1, "FP")
        service.add_discipline_service(2, "OOP")
        service.add_grade_to_student_service(1, 1, 1)
        service.add_grade_to_student_service(1, 1, 4)  # student 1 is failing
        service.add_grade_to_student_service(1, 2, 10)
        service.add_grade_to_student_service(2, 2, 2)
        new_dictionary = service.students_failing_at_one_or_more_disciplines()
        self.assertEqual(len(new_dictionary), 2)
        print("Test for statistics is done!")
