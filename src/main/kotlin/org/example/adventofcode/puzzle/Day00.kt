package org.example.adventofcode.puzzle

import org.example.adventofcode.util.FileLoader


fun main() {
    println("Example 1: ${part1("/day00_example.txt")}")
    println("Solution 1: ${part1("/day00.txt")}")
    println("Example 2: ${part2("/day00_example.txt")}")
    println("Solution 2: ${part2("/day00.txt")}")
}

fun part1(filePath: String): Int {
    val stringLines = FileLoader.loadFromFile<String>(filePath)

    var currentSum = 0
    var highestCalorie = 0

    for (line in stringLines) {
        if (line.isEmpty()) {
            if (currentSum > highestCalorie) {
                highestCalorie = currentSum
            }
                currentSum = 0
        } else {
            currentSum += line.toInt()
        }
    }

    if (currentSum > highestCalorie) {
        highestCalorie = currentSum
    }

    return highestCalorie
}

fun part2(filePath: String): Int {
    val stringLines = FileLoader.loadFromFile<String>(filePath)

    var currentSum = 0
    var list = mutableListOf<Int>()

    for (line in stringLines) {
        if (line.isEmpty() && currentSum != 0) {
            list.add(currentSum)
            currentSum = 0
        } else {
            currentSum += line.toInt()
        }
    }

    if (currentSum != 0) {
        list.add(currentSum)
    }

    list.sortDescending()

    println("highest: ${list.elementAt(0)} + 2nd: ${list.elementAt(1)} + 3rd: ${list.elementAt(2)}")
    val answer = list.elementAt(0) + list.elementAt(1) + list.elementAt(2)

    return answer
}

