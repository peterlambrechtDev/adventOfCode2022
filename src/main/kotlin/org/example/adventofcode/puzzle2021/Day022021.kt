package org.example.adventofcode.puzzle2021

import org.example.adventofcode.util.FileLoader

object Day022021 {
    fun part1(filePath: String): Int {
        val stringLines = FileLoader.loadFromFile<String>(filePath)

        var horizontal = 0
        var vertical = 0

        for (line in stringLines) {
            if (line.isNotEmpty()) {
                val directions = line.split(" ")

                val direction = directions[0]
                val amount = directions[1]
                if (direction == "forward") {
                    horizontal += amount.toInt()
                }
                if (direction == "up") {
                    vertical -= amount.toInt()
                }
                if (direction == "down") {
                    vertical += amount.toInt()
                }
            }
        }

        return horizontal * vertical
    }

    fun part2(filePath: String): Int {
        val stringLines = FileLoader.loadFromFile<String>(filePath)

        var horizontal = 0
        var vertical = 0
        var aim = 0

        for (line in stringLines) {
            if (line.isNotEmpty()) {
                val directions = line.split(" ")

                val direction = directions[0]
                val amount = directions[1]
                val intAmount = amount.toInt()
                if (direction == "forward") {
                    horizontal += intAmount
                    vertical += intAmount * aim

                }
                if (direction == "up") {
                    aim -= intAmount
                }
                if (direction == "down") {
                    aim += intAmount
                }
            }
        }

        return horizontal * vertical

        return 0
    }

}

fun main() {
    var day = "day022021"

    println("Example 1: ${Day022021.part1("/${day}_example.txt")}")
    println("Solution 1: ${Day022021.part1("/${day}.txt")}")
    println("Example 2: ${Day022021.part2("/${day}_example.txt")}")
    println("Solution 2: ${Day022021.part2("/${day}.txt")}")
}
