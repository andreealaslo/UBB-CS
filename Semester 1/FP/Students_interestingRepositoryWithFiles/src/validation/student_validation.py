from src.errors.validation_errors import ValidError


class StudentValidation:

    def __init__(self):
        pass

    @staticmethod
    def validate(student):
        errors = ""
        if student.get_id_student() < 0:
            errors += "Invalid student id!"
        if student.get_name_student() == "":
            errors += "Invalid name!"
        if student.get_group() < 0:
            errors += "Invalid student group!"
        if len(errors) > 0:
            raise ValidError(errors)
