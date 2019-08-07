package dev.oscar.ruiz.cvapp.di.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dev.oscar.ruiz.cvapp.db.AppDatabase
import dev.oscar.ruiz.cvapp.di.scope.AppScope

@Module
class DbModule {

    /**
     * Provide an AppDatabase Instance
     */
    @Provides
    @AppScope
    fun provideDb(app: Application): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, "cv_app.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}