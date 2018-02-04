package com.itoolshub.pojo.util

import org.junit.Assert
import org.junit.Test

class UtilsKtTest {

    @Test
    fun testparsePathToPackage() {
        val path = "/Users/niuli/workspace/quding-git/easy-dev-utils/base-pojo/src/main/java/com/itoolshub/pojo"
        Assert.assertEquals(FiledUtils.parsePathToPackage(path),"com.itoolshub.pojo")
    }

}