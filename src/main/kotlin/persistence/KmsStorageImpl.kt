package persistence

import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters.eq
import org.bson.Document

/**
 * @author Max Neverov
 */
class MongoStorageImpl(mongoURI: String = "mongodb://localhost:27017") : KmsStorage {

    val databaseName = "KotlinTest"
    val collectionName = "docs"
    val client: MongoClient = MongoClient(MongoClientURI(mongoURI))
    val db: MongoDatabase = client.getDatabase(databaseName)
    val docs: MongoCollection<Document> = db.getCollection(collectionName)

    override fun get(id: String): String? {
        return docs.find(eq("_id", id))?.first()?.toJson()
    }

    override fun insert(doc: String) {
        docs.insertOne(Document.parse(doc))
    }

    override fun delete(id: String): Long {
        return db.getCollection(collectionName).deleteOne(eq("_id", id)).deletedCount
    }

    override fun update(id: String, doc: String) {
        db.getCollection(collectionName).updateOne(eq("_id", id), Document("\$set", Document.parse(doc)))
    }
}
