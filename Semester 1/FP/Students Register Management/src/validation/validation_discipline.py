from src.errors.validation_errors import ValidDisciplineError


class ValidationDiscipline:
    def validate_discipline(self, discipline):
        errors = ""
        if discipline.discipline_id < 0:
            errors += "Invalid discipline id!"
        if discipline.discipline_name == "":
            errors += "Invalid discipline name!"
        if len(errors) > 0:
            raise ValidDisciplineError(errors)
