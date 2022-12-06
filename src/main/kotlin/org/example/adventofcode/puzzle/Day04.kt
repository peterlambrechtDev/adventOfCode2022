package org.example.adventofcode.puzzle

import org.example.adventofcode.util.FileLoader


class Range {
    private val beginning: Int
    private val ending: Int

    constructor(beginning: Int, ending: Int) {
        this.beginning = beginning
        this.ending = ending
    }

    fun fullyOverlap(range: Range): Boolean {
        return (beginning <= range.beginning && ending >= range.ending)
    }

    fun partialOverlap(range: Range): Boolean {
        return (range.beginning in beginning..ending || range.ending in beginning..ending)
    }
}

object Day04 {
    fun part1(filePath: String): Int {
        val stringLines = FileLoader.loadFromFile<String>(filePath)

        val pairs = createPairs(stringLines)
        var count = 0
        pairs.forEach { if (it.first.fullyOverlap(it.second) || it.second.fullyOverlap(it.first)) count++ }

        return count
    }

    private fun createPairs(
        stringLines: List<String>
    ): List<Pair<Range, Range>> {
        val pairs = mutableListOf<Pair<Range, Range>>()
        for (line in stringLines) {
            val assignments = line.trim().split(",")
            val firstAssignment = assignments[0].split("-")
            val secondAssignment = assignments[1].split("-")
            pairs.add(
                Pair(
                    Range(firstAssignment.first().toInt(), firstAssignment.last().toInt()),
                    Range(secondAssignment.first().toInt(), secondAssignment.last().toInt())
                )
            )
        }
        return pairs
    }

    fun part2(filePath: String): Int {
        val stringLines = FileLoader.loadFromFile<String>(filePath)

        val pairs = createPairs(stringLines)
        var count = 0
        pairs.forEach { if (it.first.partialOverlap(it.second) || it.second.partialOverlap(it.first)) count++ }

        return count
    }
}

fun main() {
    var day = "day04"
    val dayObj = Day04

    println("Example 1: ${dayObj.part1("/${day}_example.txt")}")
    println("Solution 1: ${dayObj.part1("/${day}.txt")}")
    println("Example 2: ${dayObj.part2("/${day}_example.txt")}")
    println("Solution 2: ${dayObj.part2("/${day}.txt")}")
}

