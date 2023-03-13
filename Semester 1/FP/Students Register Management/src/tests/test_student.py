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


class TestStudent(unittest.TestCase):

    def setUp(self) -> None:
        print("set up")

    def tearDown(self) -> None:
        print("tear down")

    def test__add_student(self):
        validation_s = ValidationStudent()
        validation_d = ValidationDiscipline()
        validation_g = ValidationGrade()
        repo_s = RepoStudent()
        repo_d = RepoDiscipline()
        repo_g = RepoGrade()
        service = Service(repo_s, repo_d, repo_g, validation_s, validation_d, validation_g)
        self.assertEqual(service.size_service_student(), 0)
        student = service.add_student_service(1, "Ana")
        self.assertEqual(service.size_service_student(), 1)
        self.assertEqual(student.student_id, 1)
        self.assertEqual(student.student_name, "Ana")
        try:
            service.add_student_service(-1, "Ana")
            assert False
        except ValidStudentError as vs:
            self.assertEqual(str(vs), "Invalid student id!")
        self.assertEqual(service.size_service_student(), 1)
        try:
            service.add_student_service(1, "Ana")
            assert False
        except RepoStudentError as rs:
            self.assertEqual(str(rs), "Existent student!")
        print("test for add student is done!")

    def test__remove_student(self):
        validation_s = ValidationStudent()
        validation_d = ValidationDiscipline()
        validation_g = ValidationGrade()
        repo_s = RepoStudent()
        repo_d = RepoDiscipline()
        repo_g = RepoGrade()
        service = Service(repo_s, repo_d, repo_g, validation_s, validation_d, validation_g)
        self.assertEqual(service.size_service_student(), 0)
        service.add_student_service(1, "Ana")
        self.assertEqual(service.size_service_student(), 1)
        service.remove_student_service(1)
        self.assertEqual(service.size_service_student(), 0)
        try:
            service.remove_student_service(1)
            assert False
        except RepoStudentError as rs:
            self.assertEqual(str(rs), "Inexistent student!")
        print("test for remove student is done")

    def test__update_student(self):
        validation_s = ValidationStudent()
        validation_d = ValidationDiscipline()
        validation_g = ValidationGrade()
        repo_s = RepoStudent()
        repo_d = RepoDiscipline()
        repo_g = RepoGrade()
        service = Service(repo_s, repo_d, repo_g, validation_s, validation_d, validation_g)
        student = service.add_student_service(1, "Ana")
        self.assertEqual(service.size_service_student(), 1)
        self.assertEqual(student.student_id, 1)
        self.assertEqual(student.student_name, "Ana")
        service.update_student_service(1, "Ana-Maria")
        self.assertEqual(student.student_name, "Ana-Maria")
        print("test for update student is done")
