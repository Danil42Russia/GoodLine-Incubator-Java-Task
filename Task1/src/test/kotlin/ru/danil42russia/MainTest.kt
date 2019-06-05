package ru.danil42russia

import org.spekframework.spek2.Spek
import kotlin.test.assertEquals

@Suppress("unused")
object MainTest : Spek({
    group("size words test") {
        test("test size 1") {
            val args = "the quick brown fox jumps over the lazy dog"
            val code = sizeWord(args.toArray())

            assertEquals(9, code)
        }

        test("test size 2") {
            val args = "the quick brown fox jumps over the lazy dog and feels as if he were in the seventh heaven of typography together with Hermann Zapf the most famous artist of the typography"
            val code = sizeWord(args.toArray())

            assertEquals(32, code)
        }
    }

    group("top10 words test") {
        test("test top10 1") {
            val args = "the quick brown fox jumps over the lazy dog"
            val code = top10(args.toArray())

            val list = mutableListOf<Pair<Int, String>>()
            list.add(Pair(2, "the"))
            list.add(Pair(1, "brown"))
            list.add(Pair(1, "dog"))
            list.add(Pair(1, "fox"))
            list.add(Pair(1, "jumps"))
            list.add(Pair(1, "lazy"))
            list.add(Pair(1, "over"))
            list.add(Pair(1, "quick"))

            assertEquals(list.toList(), code)
        }

        test("test top10 2") {
            val args = "the quick brown fox jumps over the lazy dog and feels as if he were in the seventh heaven of typography together with Hermann Zapf the most famous artist of the typography"
            val code = top10(args.toArray())

            val list = mutableListOf<Pair<Int, String>>()
            list.add(Pair(5, "the"))
            list.add(Pair(2, "of"))
            list.add(Pair(2, "typography"))
            list.add(Pair(1, "Hermann"))
            list.add(Pair(1, "Zapf"))
            list.add(Pair(1, "and"))
            list.add(Pair(1, "artist"))
            list.add(Pair(1, "as"))
            list.add(Pair(1, "brown"))
            list.add(Pair(1, "dog"))

            assertEquals(list.toList(), code)
        }
    }
})

private fun String.toArray(): Array<String> {
    return this.split(' ').toTypedArray()
}