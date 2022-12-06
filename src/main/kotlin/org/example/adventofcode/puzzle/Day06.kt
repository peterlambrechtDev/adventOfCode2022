package org.example.adventofcode.puzzle

import org.example.adventofcode.util.FileLoader

object Day06 {
    fun part1(filePath: String): Int {
        val stringLines = FileLoader.loadFromFile<String>(filePath)

        return findMarkerAndReturnPosition(stringLines[0], 4)
    }

    fun part2(filePath: String): Int {
        val stringLines = FileLoader.loadFromFile<String>(filePath)

        return findMarkerAndReturnPosition(stringLines[0], 14)

    }

    private fun findMarkerAndReturnPosition(stream: String, markerSize: Int) : Int{
        var marker = ""
        var index = 0
        stream.toList().forEach {
            index++

            marker += it

            if (marker.length == markerSize + 1) {
                marker = marker.removeRange(0, 1)
            }

            if (marker.toSet().size == markerSize) {
                println("marker is $marker")
                return index
            }
        }
        return 0
    }
}

fun main() {
    val day = "day06"
    val dayObj = Day06

    println("Example 1: ${dayObj.part1("/${day}_example.txt")}")
    println("Solution 1: ${dayObj.part1("/${day}.txt")}")
    println("Example 2: ${dayObj.part2("/${day}_example.txt")}")
    println("Solution 2: ${dayObj.part2("/${day}.txt")}")
}
