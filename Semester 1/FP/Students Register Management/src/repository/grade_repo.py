class RepoGrade:
    def __init__(self):
        self.__grades = {}

    def add_discipline_to_dictionary(self, id_discipline):
        ok = 0
        if id_discipline in self.__grades:
            ok = 1
        if ok == 0:
            self.__grades[id_discipline] = {}

    def add_student_to_dictionary_of_discipline(self, discipline_id, id_student):
        ok = 0
        for discipline in self.__grades.keys():
            if discipline == discipline_id:
                for student in self.__grades[discipline_id]:
                    if student == id_student:
                        ok = 1
        if ok == 0:
            self.__grades[discipline_id][id_student] = []

    def add_grade_to_student_repo(self, grade):
        discipline_id = grade.discipline_id
        self.add_discipline_to_dictionary(discipline_id)
        student_id = grade.student_id
        self.add_student_to_dictionary_of_discipline(discipline_id, student_id)
        grade_value = grade.grade_value
        self.__grades[discipline_id][student_id].append(grade_value)

    def get_all_disciplines_repo(self):
        return self.__grades.keys()

    def get_students_as_keys_repo(self, discipline_id):
        return self.__grades[discipline_id].keys()

    def get_all_grades_per_discipline_repo(self, discipline_id, student_id):
        return self.__grades[discipline_id][student_id]

    def remove_student_grade_repo(self, student_id):
        for discipline in self.get_all_disciplines_repo():
            if student_id in self.get_students_as_keys_repo(discipline):
                self.__grades[discipline][student_id].clear()

    def remove_discipline_from_grades(self, discipline_id):
        if discipline_id in self.get_all_disciplines_repo():
            del self.__grades[discipline_id]

    def size_grade_repo(self):
        return len(self.__grades)
