package org.example.adventofcode.puzzle

import org.example.adventofcode.util.FileLoader

class Group {
    private val elves : List<String>

    constructor(elves: List<String>) {
        this.elves = elves
    }

    fun intersect(): String {
        var intersectSet = emptySet<Char>()
        for (elf in elves) {
            if (intersectSet.isEmpty()) {
                intersectSet = elf.toSet()
            } else {
                intersectSet = intersectSet.intersect(elf.toSet())
            }
        }
        return intersectSet.toList().first().toString()
    }
}

object Day03 {
    fun part1(filePath: String): Int {
        val stringLines = FileLoader.loadFromFile<String>(filePath)
        var priorities = priorities()
        println(priorities)

        var sum = 0

        for (line in stringLines) {
            val trimmedLine = line.trim()
            if (trimmedLine.length > 0) {
                val halfwayPoint = (trimmedLine.length / 2) - 1
                val rs1 = trimmedLine.slice(0..halfwayPoint).toSet()
                val rs2 = trimmedLine.slice(halfwayPoint + 1..trimmedLine.length - 1).toSet()

                val intersect = rs1.intersect(rs2)

                sum += priorities[intersect.toList()[0].toString()]!!.toInt()
            }
        }

        return sum
    }

    fun part2(filePath: String): Int {
        val stringLines = FileLoader.loadFromFile<String>(filePath)
        var priorities = priorities()

        val chunked = stringLines.chunked(3)
        val groups = mutableListOf<Group>()

        chunked.forEach { groups.add(Group(it)) }

        var sum = 0
        groups.forEach { sum += priorities.get(it.intersect())!!.toInt() }

        return sum
    }

    fun priorities(): Map<String, Int> {
        var c = 'a'
        var priority = 1
        var map = mutableMapOf<String, Int>()
        while (c <= 'z') {
            map.put(c.toString(), priority)
            priority++
            ++c
        }

        c = 'A'
        while (c <= 'Z') {
            map.put(c.toString(), priority)
            priority++
            ++c
        }

        return map
    }
}

fun main() {
    var day = "day03"

    println("Example 1: ${Day03.part1("/${day}_example.txt")}")
    println("Solution 1: ${Day03.part1("/${day}.txt")}")
    println("Example 2: ${Day03.part2("/${day}_example.txt")}")
    println("Solution 2: ${Day03.part2("/${day}.txt")}")
}
