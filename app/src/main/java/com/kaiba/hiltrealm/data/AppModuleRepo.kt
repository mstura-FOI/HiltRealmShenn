package com.kaiba.hiltrealm.data

import com.kaiba.hiltrealm.models.Item
import dagger.hilt.android.HiltAndroidApp
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppModuleRepo(private val realm: Realm) {
    fun getData(): RealmResults<Item> {
        return realm.query<Item>().find()
    }

    suspend fun addData(text: String) {
        realm.writeBlocking {
            // Create a new instance of your Realm model class
            val newData = Item().apply {
                this.name = text

            }
            copyToRealm(newData)
            realm.query<Item>().find()
        }

    }
    suspend fun deleteData() {

        realm.write {
            var item = query<Item>().find().firstOrNull()

            if (item != null) {
                delete(item)
            }

        }

    }
}