package org.example.adventofcode.puzzle

import org.example.adventofcode.util.FileLoader

private const val DELIMITER = " "

private val part1Map = mutableMapOf(Pair("A", 1), Pair("B", 2), Pair("C", 3), Pair("X", 1), Pair("Y", 2), Pair("Z", 3))

private val part2Map = mutableMapOf(
    Pair(1, mutableMapOf(Pair("Z", 2), Pair("Y", 1), Pair("X", 3))),
    Pair(2, mutableMapOf(Pair("Z", 3), Pair("Y", 2), Pair("X", 1))),
    Pair(3, mutableMapOf(Pair("Z", 1), Pair("Y", 3), Pair("X", 2)))
)

object Day02 {
    fun part1(filePath: String): Int {
        val matches = FileLoader.loadFromFile<String>(filePath)

        var score = 0

        for (match in matches) {
            if (match.isNotEmpty()) {
                val shapes = match.split(DELIMITER)

                val opponentShape: Int = part1Map.get(shapes[0])!!
                val yourShape: Int = part1Map.get(shapes[1])!!

                var matchResult = 0

                if ((opponentShape == 3 && yourShape == 1) || (yourShape - opponentShape == 1)) {
                    matchResult = 6
                } else if (opponentShape == yourShape) {
                    matchResult = 3
                }

                score += yourShape + matchResult
            }
        }

        return score
    }

    fun part2(filePath: String): Int {
        val matches = FileLoader.loadFromFile<String>(filePath)
        var score = 0
        for (match in matches) {
            if (match.isNotEmpty()) {
                val shapes = match.split(DELIMITER)

                val opponentShape = part2Map.get(part1Map.get(shapes[0]))

                val desiredResult = shapes[1]
                val yourShape = opponentShape?.get(desiredResult)

                val matchResult: Int = when (desiredResult) {
                    "X" -> {
                        0
                    }
                    "Y" -> {
                        3
                    }
                    else -> {
                        6
                    }
                }

                score += yourShape!! + matchResult
            }
        }

        return score
    }
}

fun main() {
    var day = "day02"

    println("Example 1: ${Day02.part1("/${day}_example.txt")}")
    println("Solution 1: ${Day02.part1("/${day}.txt")}")
    println("Example 2: ${Day02.part2("/${day}_example.txt")}")
    println("Solution 2: ${Day02.part2("/${day}.txt")}")
}