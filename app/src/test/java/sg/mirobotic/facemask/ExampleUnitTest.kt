package sg.mirobotic.facemask

import org.junit.Test

import org.junit.Assert.*
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    fun test(data: String): Boolean {
        var isValid = false

        if (data.length < 2) {
            isValid = false
        }else {

            val brackets = Stack<Char>()
            brackets.push(data[0])

            for (i in data.indices) {

                val char = data[i]
                if (i == 0) {
                    continue
                }

                if (brackets.isEmpty()) {
                    brackets.push(char)
                    continue
                }

                when(brackets.peek()) {

                    '(' -> {
                        if (char == ')') {
                            brackets.pop()
                        }else {
                            brackets.push(char)
                        }

                    }
                    '{' -> {
                        if (char == '}') {
                            brackets.pop()
                        }else {
                            brackets.push(char)
                        }

                    }
                    '[' -> {
                        if (char == ']') {
                            brackets.pop()
                        }else {
                            brackets.push(char)
                        }


                    }

                }

            }

            if (brackets.isEmpty()) {
                isValid = true
            }

        }

        return isValid
    }


    @Test
    fun addition_isCorrect() {

        val t1 = test("{([])}")

        println("T1 >> $t1")
        val t2 = test("{}()[]")

        println("T2 >> $t2")

        val t3 = test("[{}]")

        println("T3 >> $t3")

        val t4 = test("{)")

        println("T4 >> $t4")

        val t5 = test("{{{}}[]}")

        println("T5 >> $t5")

        val t6 = test("({{}}[]}")

        println("T6 >> $t6")

        assertEquals(true, t1)
        assertEquals(true, t2)
        assertEquals(true, t3)
        assertEquals(false, t4)
        assertEquals(true, t5)
        assertEquals(false, t6)

    }
}