from src.errors.validation_errors import *


class ValidationStudent:

    def validate_student(self, student):
        errors = ""
        if student.student_id < 0:
            errors += "Invalid student id!"
        if student.student_name == "":
            errors += "Invalid student name!"
        if len(errors) > 0:
            raise ValidStudentError(errors)
