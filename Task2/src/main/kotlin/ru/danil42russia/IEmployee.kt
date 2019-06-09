package ru.danil42russia

interface IEmployee {
    fun addNewEmp(emp: Employee): Boolean
    fun listEmpsBySurname(surname: String): List<Employee>
    fun listEmpsByPosition(position: String): List<Employee>
    fun deleteEmp(id: String)
}
