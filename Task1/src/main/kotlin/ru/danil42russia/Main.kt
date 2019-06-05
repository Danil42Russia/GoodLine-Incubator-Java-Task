package ru.danil42russia

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        val arr = readLine()!!.split(' ').toTypedArray()
        logic(arr)
    } else {
        logic(args)
    }
}

private fun logic(args: Array<String>) {
    val size = sizeWord(args)
    val list = top10(args)

    println("В тексте $size слов")
    println("TOP10: ")
    list.forEach { println("${it.second} - ${it.first}") }
}

fun sizeWord(args: Array<String>): Int {
    return args.size
}

fun top10(args: Array<String>): List<Pair<Int, String>> {
    val list = mutableListOf<Pair<Int, String>>()
    args
        .groupBy { it }
        .toList()
        .sortedWith(compareByDescending<Pair<String, List<String>>> { it.second.size }.thenBy { it.first })
        .take(10)
        .forEach { list.add(Pair(it.second.size, it.first)) }
    return list.toList()
}