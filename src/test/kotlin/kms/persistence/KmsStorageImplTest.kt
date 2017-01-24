package kms.persistence

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author Maxim Neverov
 */
@RunWith(SpringRunner::class)
@SpringBootTest
@ActiveProfiles("test")
class MongoStorageImplTest {

    @Autowired lateinit var ms: MongoStorageImpl
    private val expected = """{ "_id" : "42", "msg" : "hello" }"""

    @Before fun init() {
        val del = ms.delete("42")
        println("Docs deleted: $del")
        ms.insert(expected)
    }

    @Test fun insertSuccess() {
        val actual = ms.get("42")
        JSONAssert.assertEquals(expected, actual, true)
    }

    @Test fun updateSuccess() {
        val updated = """{ "msg" : "updated" }"""
        ms.update("42", updated)
        val updatedDoc = ms.get("42")
        JSONAssert.assertEquals(updated, updatedDoc, false)
    }

}
