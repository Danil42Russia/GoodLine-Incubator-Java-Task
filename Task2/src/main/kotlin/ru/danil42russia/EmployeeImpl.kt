package ru.danil42russia

class EmployeeImpl : IEmployee {
    private val list = mutableListOf<Employee>()

    override fun addNewEmp(emp: Employee): Boolean {
        return when {
            list.any { it.name == emp.name } -> false
            else -> {
                list.add(emp)
                true
            }
        }
    }

    override fun listEmpsBySurname(surname: String): List<Employee> {
        return list
            .filter { it.surname == surname }
            .take(10)
    }

    override fun listEmpsByPosition(position: String): List<Employee> {
        return list
            .filter { it.position == position }
            .sortedWith(compareBy<Employee> { it.surname }.thenBy { it.name }.thenBy { it.patronymic })
            .take(10)
    }

    override fun deleteEmp(id: String) {
        list.removeIf { it.id == id }
    }
}