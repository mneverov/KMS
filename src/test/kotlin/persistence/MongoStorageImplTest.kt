package persistence

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Assert.assertEquals
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test

/**
 * @author Max Neverov
 * @since
 */
class MongoStorageImplTest {

    private val ms: MongoStorage = MongoStorageImpl()
    private val expected = """{ "_id" : "42", "msg" : "hello" }"""
    private val gson = Gson()
    private val type = object : TypeToken<Map<String, Any>>() {}.type


    @Before fun init() {
        val del = ms.delete("42")
        println("Docs deleted: $del")
        ms.insert(expected)
    }

    @Test fun insertSuccess() {
        val actual = ms.get("42")
        if (actual != null) {
            val map = gson.fromJson<Map<String, Any>>(actual, type)
            assertEquals("hello", map["msg"]?.toString())
            return
        } else {
            fail("Doc: $expected should be found!")
        }
    }

    @Test fun updateSuccess() {
        val updated = """{ "msg" : "updated" }"""
        ms.update("42", updated)
        val updatedDoc = ms.get("42")
        if (updatedDoc != null) {
            val map = gson.fromJson<Map<String, Any>>(updatedDoc, type)
            assertEquals("updated", map["msg"]?.toString())
        } else {
            fail("Doc (_id = 42) was not found")
        }

    }

}
