package com.kaiba.hiltrealm.data

import android.app.Application
import com.kaiba.hiltrealm.models.Item
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRealmConfiguration(application: Application): Realm {
        var conf =  RealmConfiguration.Builder(
            schema = setOf(Item::class)
        )
            .build()
        return Realm.open(conf)
    }
    @Provides
    @Singleton
    fun provideRealm(realm: Realm): AppModuleRepo {
        return AppModuleRepo(realm = realm)
    }
}