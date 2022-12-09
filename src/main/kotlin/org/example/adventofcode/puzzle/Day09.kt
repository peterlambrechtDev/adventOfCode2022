package org.example.adventofcode.puzzle

import org.example.adventofcode.util.FileLoader


object Day09 {
    fun part1(filePath: String): Int {
        val stringLines = FileLoader.loadFromFile<String>(filePath)

        val head = Knot(0, 0)
        val tail = Knot(0, 0)

        for (line in stringLines) {
            val instructions = line.split(" ")
            val move = instructions[1]
            val dir = instructions[0]
            for (i in 1..move.toInt()) {
                doMove(head, dir)
                //is tail touching?
                adjustKnot(head, tail)
            }

        }

        println(tail.positions)

        return tail.positions.size
    }

    fun part2(filePath: String): Int {
        val stringLines = FileLoader.loadFromFile<String>(filePath)


        val rope = mutableListOf<Knot>()
        for (i in 1..10) {
            rope.add(Knot(0, 0))
        }
        val head = rope.first()
        val tail = rope.last()

        for (line in stringLines) {
            val instructions = line.split(" ")
            val move = instructions[1]
            val dir = instructions[0]
            for (i in 1..move.toInt()) {
                doMove(head, dir)
                for (index in 1..9) {
                    //is currentKnot touching?
                    adjustKnot(rope[index-1], rope[index])
                }
            }
        }


        return tail.positions.size
    }

    private fun adjustKnot(
        previousKnot: Knot,
        currentKnot: Knot
    ) {
        if (!previousKnot.touching(currentKnot)) {
            if (previousKnot.y > currentKnot.y) {
                currentKnot.up()
            } else if (previousKnot.y < currentKnot.y) {
                currentKnot.down()
            }

            if (previousKnot.x > currentKnot.x) {
                currentKnot.right()
            } else if (previousKnot.x < currentKnot.x) {
                currentKnot.left()
            }
            currentKnot.positions.add(Pair(currentKnot.x, currentKnot.y))
        }
    }

    fun doMove(knot: Knot, dir: String) {
        when (dir) {
            "U" -> knot.up()
            "D" -> knot.down()
            "L" -> knot.left()
            else -> knot.right()
        }
    }
}

class Knot {
    var x: Int
    var y: Int
    val positions = mutableSetOf<Pair<Int, Int>>()

    constructor(x: Int, y: Int) {
        this.x = x
        this.y = y
        positions.add(Pair(x, y))
    }

    fun right() {
        x++
    }

    fun left() {
        x--
    }

    fun up() {
        y++
    }

    fun down() {
        y--
    }

    fun touching(knot: Knot): Boolean {
        if (knot.x == x && knot.y == y) {
            return true
        } else if (knot.x == x && (knot.y - y == 1 || knot.y - y == -1)) {
            return true
        } else if (knot.y == y && (knot.x - x == 1 || knot.x - x == -1)) {
            return true
        } else return (knot.y - y == 1 || knot.y - y == -1) && (knot.x - x == 1 || knot.x - x == -1)
    }

    override fun toString(): String {
        return "Knot(x=$x, y=$y)"
    }


}

fun main() {
    val day = "day09"
    val dayObj = Day09

    println("Example 1: ${dayObj.part1("/${day}_example.txt")}")
    println("Solution 1: ${dayObj.part1("/${day}.txt")}")
    println("Example 2: ${dayObj.part2("/${day}_example.txt")}")
    println("Solution 2: ${dayObj.part2("/${day}.txt")}")
}
