from src.repository.discipline_repo import RepoDiscipline
from src.repository.grade_repo import RepoGrade
from src.repository.student_repo import RepoStudent
from src.services.service import Service
from src.ui.ui import UI
from src.validation.validation_discipline import ValidationDiscipline
from src.validation.validation_grade import ValidationGrade
from src.validation.validation_student import ValidationStudent


def main():
    validation_s = ValidationStudent()
    validation_d = ValidationDiscipline()
    validation_g = ValidationGrade()
    repo_s = RepoStudent()
    repo_d = RepoDiscipline()
    repo_g = RepoGrade()
    service = Service(repo_s, repo_d, repo_g, validation_s, validation_d, validation_g)
    service.generate_students()
    service.generate_disciplines()
    service.generate_grades()
    ui = UI(service)
    ui.start()


main()
