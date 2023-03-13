from src.errors.validation_errors import ValidGradeError


class ValidationGrade:

    def validate_grade(self, grade):
        errors = ""
        if grade.discipline_id < 0:
            errors += "Invalid discipline id!"
        if grade.student_id < 0:
            errors += "Invalid student id!"
        if grade.grade_value < 0 or grade.grade_value > 10:
            errors += "Invalid grade!"
        if len(errors) > 0:
            raise ValidGradeError(errors)
