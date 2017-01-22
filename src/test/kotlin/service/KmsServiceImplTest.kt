package service

import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.*
import persistence.KmsStorage
import persistence.MongoStorageImpl

/**
 * @author Maxim Neverov
 */
class KmsServiceImplTest {
    val mongoStorage : KmsStorage = mock(MongoStorageImpl::class.java)
    val service = KmsServiceImpl(mongoStorage)
    val documentId = "42"
    val expectedDocument = "I'm an expectedDocument document"

    @Test fun getTest() {
        `when`(mongoStorage.get(documentId)).then { expectedDocument }
        val actual = service.get(documentId)
        verify(mongoStorage).get(documentId)
        assertEquals(expectedDocument, actual)
    }

    @Test fun insertTest() {
        `when`(mongoStorage.insert(expectedDocument)).then {  }
        service.insert(expectedDocument)
        verify(mongoStorage).insert(expectedDocument)
    }

    @Test fun deleteTest() {
        val expectedDeletedDocuments = 1L
        `when`(mongoStorage.delete(documentId)).then { expectedDeletedDocuments }
        val actualDeletedDocuments = service.delete(documentId)
        verify(mongoStorage).delete(documentId)
        assertEquals(expectedDeletedDocuments, actualDeletedDocuments)
    }

    @Test fun updateTest() {
        `when`(mongoStorage.update(documentId, expectedDocument)).then {  }
        service.update(documentId, expectedDocument)
        verify(mongoStorage).update(documentId, expectedDocument)
    }
}
