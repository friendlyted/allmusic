package pro.friendlyted.ears.music.service

fun String.nearToAny(vararg others: String): Boolean {
    return others.any { nearTo(it) }
}

fun String.nearTo(other: String): Boolean {
    if (other.length <= 5 || length <= 5) {
        return this == other
    }
    val requiredDistance = (this.length / 5)
    if (Math.abs(length - other.length) > requiredDistance) {
        return false
    }
    return distanceTo(other) <= requiredDistance
}

fun String.distanceTo(other: String): Int {
    fun min(vararg numbers: Int): Int {
        return numbers.min()?.or(Integer.MAX_VALUE) ?: Integer.MAX_VALUE
    }

    fun costOfSubstitution(a: Char, b: Char): Int {
        return if (a == b) 0 else 1
    }

    val x = this
    val y = other

    val dp = Array(x.length + 1) { IntArray(y.length + 1) }

    for (i in 0..x.length) {
        for (j in 0..y.length) {
            if (i == 0) {
                dp[i][j] = j
            } else if (j == 0) {
                dp[i][j] = i
            } else {
                dp[i][j] = min(dp[i - 1][j - 1] + costOfSubstitution(x[i - 1], y[j - 1]),
                        dp[i - 1][j] + 1,
                        dp[i][j - 1] + 1)
            }
        }
    }

    return dp[x.length][y.length]
}


